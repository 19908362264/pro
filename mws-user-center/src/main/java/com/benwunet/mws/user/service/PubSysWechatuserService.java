package com.benwunet.mws.user.service;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.base.PubSysWechatuser;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 *微信用户信息Service
 * @author xiangkaihong
 * @date 2019/5/1 13:28
 */

public interface PubSysWechatuserService {

    /**
     * 获取微信授权url
      * @param app
     * @param request
     * @param toUrl
     * @return
     */
    String getWechatuserAuthorizeUrl(String app, HttpServletRequest request, String toUrl)
            throws UnsupportedEncodingException;

    /**
     * 获取微信个人用户信息
     * @param app
     * @param request
     * @param code
     * @param state
     * @return
     */
    PubSysWechatuser getPubSysWechatuser(String app, HttpServletRequest request, String code, String state);

    /**获取Url*/
    String getToUrl(String toUrl, PubSysWechatuser pubSysWechatuser);

    /**绑定用户*/
    void bindingUser(PubSysUser pubSysUser, String tempCode, String openId);

    /**检查并获取微信用户信息*/
    PubSysWechatuser checkAndGetPubSysWechatuser(String tempCode, String openId);
}
