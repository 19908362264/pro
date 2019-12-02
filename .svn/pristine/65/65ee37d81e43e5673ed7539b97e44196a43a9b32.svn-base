package com.benwunet.mws.user.controller;

import com.benwunet.mws.commons.utils.PubSysUserUtil;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.base.PubSysWechatuser;
import com.benwunet.mws.user.service.PubSysWechatuserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 微信用户控制器
 * @author xiangkaihong
 * @date 2019/5/3 15:11
 */

@Slf4j
@RestController
public class PubSysWechatuserController {

    @Autowired
    private PubSysWechatuserService pubSysWechatuserService;

    /**
     * 引导到授权
     * @author xiangkaihong
     * @date 2019/5/3 15:15
     * @param app
     * @param request
     * @param toUrl   授权后，跳转的页面url，注意url要转义
     * @return
     */
     @GetMapping("/{app}")
    public RedirectView toWechatAuthorize(@PathVariable String app, HttpServletRequest request,
                                          @RequestParam String toUrl) throws UnsupportedEncodingException {
        String url = pubSysWechatuserService.getWechatuserAuthorizeUrl(app, request, toUrl);

        return new RedirectView(url);
    }

    /**
     * 授权后，微信跳转到此接口
     * @author xiangkaihong
     * @date 2019/5/3 15:17
     * @return
     */
    @GetMapping(value = "/{app}/back", params = {"code", "state"})
    public RedirectView wechatBack(HttpServletRequest request, @PathVariable String app, String code, String state,
                                   @RequestParam String toUrl) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code不能为空");
        }

        if (StringUtils.isBlank(state)) {
            throw new IllegalArgumentException("state不能为空");
        }

        PubSysWechatuser pubSysWechatuser = pubSysWechatuserService.getPubSysWechatuser(app, request, code, state);

        toUrl = pubSysWechatuserService.getToUrl(toUrl, pubSysWechatuser);

        return new RedirectView(toUrl);
    }

    /**
     * 微信绑定用户
     * @author xiangkaihong
     * @date 2019/5/3 15:22
     * @param tempCode
     * @param openId
     * @return
     */
    @PostMapping(value = "/binding-user", params = {"tempCode", "openId"})
    public void bindingUser(String tempCode, String openId) {
        PubSysUser pubSysUser = PubSysUserUtil.getLoginPubSysUser();
        if (pubSysUser == null) {
            throw new IllegalArgumentException("非法请求");
        }

        log.info("绑定微信和用户：{},{},{}", pubSysUser, openId, tempCode);
        pubSysWechatuserService.bindingUser(pubSysUser, tempCode, openId);
    }

    /**
     * 微信登陆校验
     * @author xiangkaihong
     * @date 2019/5/3 15:25
     * @param tempCode
     * @param openId
     * @return
     */
   @GetMapping(value = "/login-check", params = {"tempCode", "openId"})
    public void wechatuserLoginCheck(String tempCode, String openId) {
       pubSysWechatuserService.checkAndGetPubSysWechatuser(tempCode, openId);
    }
}
