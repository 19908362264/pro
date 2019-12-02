package com.benwunet.mws.oauth.service.Impl;

import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.base.constants.CredentialType;
import com.benwunet.mws.oauth.feign.SmsClient;
import com.benwunet.mws.oauth.feign.SysUserClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 *根据用户凭证表的用户名获取用户
 * @author xiangkaihong
 * @date 2019/5/3 16:23
 * 密码校验请看下面两个类
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider
 */

@Slf4j
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SmsClient smsClient;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /**为了支持多类型登录，这里userName后面拼装上登录类型,如username|type*/
        String[] params = userName.split("\\|");
        /**真正的用户名*/
        userName = params[0];
        // LoginPubSysUser loginPubSysUser = sysUserClient.findByUserName(userName);
        // --xiangkaihong modify 2019-05-15
        LoginPubSysUser loginPubSysUser = null;
        try {
            loginPubSysUser = sysUserClient.findByUserName(userName);
        }catch(Exception e){
            e.printStackTrace();
        }

        if (loginPubSysUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if (!loginPubSysUser.isEnabled()) {
            throw new DisabledException("用户已停用");
        }

        if (params.length > 1) {
            /**登录类型*/
            CredentialType credentialType = CredentialType.valueOf(params[1]);
            /**短信登录*/
            if (CredentialType.MOBILE == credentialType) {
                handlerMobileSmsLogin(loginPubSysUser, params);
                /**微信登陆*/
            } else if (CredentialType.WECHAT_OPENID == credentialType) {
                //handlerWechatuserLogin(loginPubSysUser, params);
                loginPubSysUser.setPassword(passwordEncoder.encode(params[0]));
            }
        }

        return loginPubSysUser;
    }

    /**
     * 微信登录，处理逻辑
     * @author xiangkaihong
     * @date 2019/5/3 17:03
     * @param  loginPubSysUser
     * @param  params
     */
    private void handlerWechatuserLogin(LoginPubSysUser loginPubSysUser, String[] params) {
        if (params.length < 3) {
            throw new IllegalArgumentException("非法请求");
        }

        String openId = params[0];
        String tempCode = params[2];

        sysUserClient.wechatuserLoginCheck(tempCode, openId);

        /**其实这里是将密码重置，网关层的微信登录接口，密码也用同样规则即可*/
        loginPubSysUser.setPassword(passwordEncoder.encode(tempCode));
        log.info("微信登陆，{},{}", loginPubSysUser, openId);
    }

    /**
     * 手机号+短信验证码登陆，处理逻辑
     * @author xiangkaihong
     * @date 2019/5/3 17:03
     * @param  loginPubSysUser
     * @param  params
     */
    private void handlerMobileSmsLogin(LoginPubSysUser loginPubSysUser, String[] params) {
        if (params.length < 5) {
            throw new IllegalArgumentException("非法请求");
        }
        String mobile = params[0];
        String key = params[2];
        String code = params[3];
        String md5 = params[4];
        if (!DigestUtils.md5Hex(key + code).equals(md5)) {
            throw new IllegalArgumentException("非法请求");
        }
        String value = smsClient.matcheCodeAndGetMobile(key, code, false, 30);
        if (!StringUtils.equals(mobile, value)) {
            throw new IllegalArgumentException("验证码错误");
        }
        /**其实这里是将密码重置，网关层的短信登录接口，密码也用同样规则即可*/
        loginPubSysUser.setPassword(passwordEncoder.encode(mobile));
        log.info("手机号+短信验证码登陆，{},{}", mobile, code);
    }
}
