package com.benwunet.mws.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.benwunet.mws.model.base.*;
import com.benwunet.mws.model.base.constants.CredentialType;
import com.benwunet.mws.user.config.WechatConfig;
import com.benwunet.mws.user.dao.PubSysUsercredentialsDao;
import com.benwunet.mws.user.dao.PubSysWechatuserDao;
import com.benwunet.mws.user.service.PubSysUserService;
import com.benwunet.mws.user.service.PubSysWechatuserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 微信用户信息实现类
 * @author xiangkaihong
 * @date 2019/5/3 10:43
 */
@Slf4j
@Service
public class PubSysWechatuserServiceImpl implements PubSysWechatuserService {

    @Autowired
    private WechatConfig wechatConfig;

    private static final String WECHAT_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";
    private static final String STATE_WECHAT = "state_wechat";

    /**
     * 获取微信对象
     * @author xiangkaihong
     * @date 2019/5/3 10:47
     */
    private WechatInfo getWechatInfo(String app) {
        WechatInfo wechatInfo = wechatConfig.getInfos().get(app);
        if (wechatInfo == null) {
            throw new IllegalArgumentException("没有找到，" + app);
        }

        return wechatInfo;
    }

    /**
     * 获取微信授权url
     * @author xiangkaihong
     * @date 2019/5/3 10:52
     */
    @Override
    public String getWechatuserAuthorizeUrl(String app, HttpServletRequest request, String toUrl)
            throws UnsupportedEncodingException {
        log.info("引导到授权页:{},{}", app, toUrl);
        WechatInfo wechatInfo = getWechatInfo(app);

        /** 网关域名(外网)加路由到用户系统的规则 https://xxx.xxx.xxx/api-u */
        String domain = wechatConfig.getDomain();
        StringBuilder redirectUri = new StringBuilder(domain + "/wechat/" + app + "/back");
        if (StringUtils.isNoneBlank(toUrl)) {
            toUrl = URLEncoder.encode(toUrl, "utf-8");
            redirectUri.append("?toUrl=").append(toUrl);
        }
        String redirect_uri = URLEncoder.encode(redirectUri.toString(), "utf-8");

        /**生成一个随机串，微信再跳回来的时候，会原封不动给我们带过来，到时候做一下校验*/
        String state = UUID.randomUUID().toString();
        request.getSession().setAttribute(STATE_WECHAT, state);

        return String.format(WECHAT_AUTHORIZE_URL, wechatInfo.getAppid(), redirect_uri, state);
    }

    /**
     * 获取微信个人用户信息
     * @author xiangkaihong
     * @date 2019/5/3 10:55
     */
    @Transactional
    @Override
    public PubSysWechatuser getPubSysWechatuser(String app, HttpServletRequest request, String code, String state) {
        log.info("code:{}, state:{}", code, state);
        checkStateLegal(state, request);

        WechatAccess wechatAccess = getWechatAccess(app, code);
        PubSysWechatuser pubSysWechatuser = pubSysWechatuserDao.findByOpenid(wechatAccess.getOpenid());

        if (pubSysWechatuser == null) {
            pubSysWechatuser = savePubSysWechatuser(app, wechatAccess);
        } else {
            updatePubSysWechatuser(wechatAccess, pubSysWechatuser);
        }

        return pubSysWechatuser;
    }

    /**
     * 保存微信个人用户信息
     * @author xiangkaihong
     * @date 2019/5/3 10:55
     */
    @Autowired
    private PubSysWechatuserDao pubSysWechatuserDao;

    @Autowired
    private PubSysUsercredentialsDao pubSysUsercredentialsDao;

    private PubSysWechatuser savePubSysWechatuser(String app, WechatAccess wechatAccess) {
        PubSysWechatuser pubSysWechatuser = getPubSysWechatuser(wechatAccess);

        /**多公众号支持*/
        String unionId = pubSysWechatuser.getUnionId();
        if (StringUtils.isNoneBlank(unionId)) {
            /**根据unionId查询，看是否有同源公众号已绑定用户*/
            Set<PubSysWechatuser> set = pubSysWechatuserDao.findByUnionid(unionId);
            if (!CollectionUtils.isEmpty(set)) {
                PubSysWechatuser pubSysWechatuser01 = set.parallelStream().filter(w -> w.getUserId() != null).findFirst().orElse(null);
                if (pubSysWechatuser01 != null) {
                    pubSysWechatuser.setUserId(pubSysWechatuser01.getUserId());
                    log.info("具有相同的unionId,视为同一用户：{}", pubSysWechatuser01);

                    /**将新公众号的openId也存入登陆凭证表*/
                    PubSysUsercredentials pubSysUsercredentials01 = new PubSysUsercredentials();
                    pubSysUsercredentials01.setUserId(pubSysWechatuser01.getUserId());
                    pubSysUsercredentials01.setUserName(pubSysWechatuser.getOpenId());
                    pubSysUsercredentials01.setCreType(CredentialType.USERNAME.name());
                    pubSysUsercredentials01.setOperatorId("000000");
                    pubSysUsercredentials01.setOperatorName("超级管理员");

                    pubSysUsercredentialsDao.saves(pubSysUsercredentials01);
                }
            }
        }

        pubSysWechatuser.setApp(app);
        pubSysWechatuser.setGmtCreate(new Date());
        pubSysWechatuser.setGmtModified(pubSysWechatuser.getGmtCreate());

        pubSysWechatuserDao.saves(pubSysWechatuser);
        log.info("保存微信个人用户信息:{}", pubSysWechatuser);

        return pubSysWechatuser;
    }

    /**
     * 异步更新微信个人用户信息方法
     * @author xiangkaihong
     * @date 2019/5/3 11:05
     * @param wechatAccess
     * @param wechatUserInfo
     */
    @Autowired
    private TaskExecutor taskExecutor;

    private void updatePubSysWechatuser(WechatAccess wechatAccess, PubSysWechatuser pubSysWechatuser) {
        taskExecutor.execute(() -> {
            PubSysWechatuser pubSysWechatuser01 = getPubSysWechatuser(wechatAccess);
            BeanUtils.copyProperties(pubSysWechatuser01, pubSysWechatuser, "id", "userId");
            pubSysWechatuser.setGmtModified(new Date());
            pubSysWechatuserDao.updates(pubSysWechatuser);

            log.info("更新微信个人用户信息:{}", pubSysWechatuser);
        });
    }

    /**
     * 校验state是否合法
     * @author xiangkaihong
     * @date 2019/5/3 11:09
     * @param state
     * @param request
     */
    private void checkStateLegal(String state, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String sessionState = (String) httpSession.getAttribute(STATE_WECHAT);
        if (sessionState == null) {
            throw new IllegalArgumentException("缺失session state");
        }

        if (!state.equals(sessionState)) {
            throw new IllegalArgumentException("非法state");
        }

        /**校验通过，将session中的state移除*/
        httpSession.removeAttribute(STATE_WECHAT);
    }

    @Autowired
    private RestTemplate restTemplate;

    private static final String WECHAT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private WechatAccess getWechatAccess(String app, String code) {
        WechatInfo wechatInfo = getWechatInfo(app);

        String accessTokenUrl = String.format(WECHAT_ACCESS_TOKEN_URL, wechatInfo.getAppid(), wechatInfo.getSecret(),
                code);

        String string = restTemplate.getForObject(accessTokenUrl, String.class);
        WechatAccess wechatAccess = JSONObject.parseObject(string, WechatAccess.class);
        log.info("wechatAccess:{}", wechatAccess);

        return wechatAccess;
    }

    private static final String WECHAT_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 获取微信个人用户信息
     * @author xiangkaihong
     * @date 2019/5/3 11:19
     * @param wechatAccess
     * @return
     */
    private PubSysWechatuser getPubSysWechatuser(WechatAccess wechatAccess) {
        String userInfoUrl = String.format(WECHAT_USERINFO_URL, wechatAccess.getAccess_token(),
                wechatAccess.getOpenid());

        String string = restTemplate.getForObject(userInfoUrl, String.class);

        string = new String(string.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        PubSysWechatuser pubSysWechatuser01 = JSONObject.parseObject(string, PubSysWechatuser.class);
        log.info("pubSysWechatuser:{}", pubSysWechatuser01);

        return pubSysWechatuser01;
    }

    /**
     * 获取Url
     * @author xiangkaihong
     * @date 2019/5/3 11:25
     * @param toUrl
     * @param pubSysWechatuser
     * @return
     */

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getToUrl(String toUrl, PubSysWechatuser pubSysWechatuser) {
        StringBuilder builder = new StringBuilder(toUrl);
        if (!toUrl.contains("?")) {
            builder.append("?");
        }

        if (pubSysWechatuser.getUserId() != null) {
            builder.append("&hasUser=1");
        }
        builder.append("&openId=").append(pubSysWechatuser.getOpenId());

        String tempCode = cachePubSysWechatuser(pubSysWechatuser);
        builder.append("&tempCode=").append(tempCode);

        builder.append("&nickName=").append(pubSysWechatuser.getNickName());
        builder.append("&headImg=").append(pubSysWechatuser.getHeadImg());

        return builder.toString();
    }

    private String cachePubSysWechatuser(PubSysWechatuser pubSysWechatuser) {
        String tempCode = UUID.randomUUID().toString();
        String key = prefixKey(tempCode);

        /**用tempCode和微信信息做个临时关系，后续的微信和账号绑定、微信登陆将会校验这个tempCode*/
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(pubSysWechatuser), 4, TimeUnit.HOURS);
        log.info("缓存微信信息:{},{}", tempCode, pubSysWechatuser);

        return tempCode;
    }

    private String prefixKey(String key) {
        return "wechat:temp:" + key;
    }

    /**
     * 绑定用户
     * @author xiangkaihong
     * @date 2019/5/3 11:30
     * @param pubSysUser
     * @param tempCode
     * @param openId
     * @return
     */

    @Autowired
    private PubSysUserService pubSysUserService;

    @Transactional
    @Override
    public void bindingUser(PubSysUser pubSysUser, String tempCode, String openId) {
        PubSysWechatuser pubSysWechatuser = checkAndGetPubSysWechatuser(tempCode, openId);

        PubSysUsercredentials pubSysUsercredentials = new PubSysUsercredentials();
        pubSysUsercredentials.setUserId(pubSysUser.getUserId());
        pubSysUsercredentials.setUserName(openId);
        pubSysUsercredentials.setCreType(CredentialType.USERNAME.name());
        pubSysUsercredentials.setOperatorId("000000");
        pubSysUsercredentials.setOperatorName("超级管理员");

        pubSysUsercredentialsDao.saves(pubSysUsercredentials);
        log.info("保存微信登陆凭证，{}", pubSysUsercredentials);

        if (StringUtils.isBlank(pubSysUser.getHeadImg())) {
            pubSysUser.setHeadImg(pubSysWechatuser.getHeadImg());
            pubSysUserService.updatePubSysUser(pubSysUser);
        }

        pubSysWechatuser.setUserId(pubSysUser.getUserId());
        pubSysWechatuserDao.updates(pubSysWechatuser);
        log.info("{}，绑定微信成功，给微信设置用户id，{}", pubSysUser, pubSysWechatuser);
    }

    /**
     * 检查并获取微信用户信息
     * @author xiangkaihong
     * @date 2019/5/3 11:40
     * @param tempCode
     * @param openId
     * @return pubSysWechatuser
     */
    public PubSysWechatuser checkAndGetPubSysWechatuser(String tempCode, String openId) {
        String key = prefixKey(tempCode);
        String string = stringRedisTemplate.opsForValue().get(key);
        if (string == null) {
            throw new IllegalArgumentException("无效的code");
        }

        PubSysWechatuser pubSysWechatuser = JSONObject.parseObject(string, PubSysWechatuser.class);
        if (!pubSysWechatuser.getOpenId().equals(openId)) {
            throw new IllegalArgumentException("无效的openid");
        }

        /**删除临时tempCode*/
        stringRedisTemplate.delete(tempCode);

        return pubSysWechatuser;
    }
}
