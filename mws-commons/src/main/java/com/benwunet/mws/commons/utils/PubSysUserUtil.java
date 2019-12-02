package com.benwunet.mws.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.benwunet.mws.model.base.LoginPubSysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

/**
 * 获取登陆的 LoginPubSysUser
 * @author xiangkaihong
 * @date 2019-04-27 9:40
 */

public class PubSysUserUtil {

    public static LoginPubSysUser getLoginPubSysUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                Object principal = authentication.getPrincipal();
                if (principal instanceof LoginPubSysUser) {
                    return (LoginPubSysUser) principal;
                }

                Map map = (Map) authenticationToken.getDetails();
                map = (Map) map.get("principal");

                return JSONObject.parseObject(JSONObject.toJSONString(map), LoginPubSysUser.class);
            }
        }
        return null;
    }
}
