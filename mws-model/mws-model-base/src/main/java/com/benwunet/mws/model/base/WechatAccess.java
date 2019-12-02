package com.benwunet.mws.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/** 微信网页授权access_token信息
 * @author xiangkaihong
 * @date 2019/4/28 15:27
 */

@Getter
@Setter
@ToString
public class WechatAccess implements Serializable {

    private static final long serialVersionUID = 5211347105128804888L;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
