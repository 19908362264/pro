package com.benwunet.bks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/9/18 18:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksUser implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * ID
     */

    private Integer id;

    /**
     * 用户代码:单位简码(5位)+顺序号(3位)+预留2位
     */
    private String userId;

    /**
     * 省代码
     */
    private String provincesId;

    /**
     * 市代码
     */
    private String cityId;

    /**
     * 区代码
     */
    private String districtId;

    /**
     * 高中学校代码
     */
    private String schoolId;

    /**
     * 学校名字
     */
    private String schoolName;

    /**
     * 年级
     */
    private String className;

    /**
     * 科目代码
     */
    private String courseId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 出身年月
     */
    //@JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private Date birthday;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headImg;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 证件类型
     */
    private String cardType;

    /**
     * 身份证正面图
     */
    private String cardFrontImg;

    /**
     * 身份证反面图
     */
    private String cardBackImg;

    /**
     * 身份证号码
     */
    private String cardNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学籍号
     */
    private String studentCode;

    /**
     * 0：信息全部完善
     1：个人信息未完善
     2：学籍信息未完善
     */
    private String status;

    /**
     * 其他：0、学生：1、老师：2、家长：3   专家：4
     */
    private Integer role;

    /**
     * 登录时间
     */
    //@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 录入时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 操作员代码
     */
    private String operatorId;

    /**
     * 操作员姓名
     */
    private String operatorName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 会员ID
     */
    private Integer memberId;
}
