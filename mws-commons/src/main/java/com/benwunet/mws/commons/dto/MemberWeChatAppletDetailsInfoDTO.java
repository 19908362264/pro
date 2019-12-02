package com.benwunet.mws.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: WangLin
 * @Date: 2019/9/24 11:54
 * @Description:  微信小程序注册详情信息
**/
@Data
public class MemberWeChatAppletDetailsInfoDTO implements Serializable {

    /** 用户代码:单位简码(5位)+顺序号(3位)+预留2位 */
    private String memberId;
    /** openId */
    private String openId;
    /** 用户昵称 */
    private String nickName;
    /** 手机号 */
    private String mobile;
    /** 用户性别 */
    private String gender;
    /** 语言 */
    private String language;
    /**  */
    private String city;
    /**  */
    private String province;
    /**  */
    private String country;
    /**  */
    private String avatarUrl;
    /**  */
    private Object watermark;
}
