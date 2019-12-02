package com.benwunet.bks.model;


import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author C
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksQuestion implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 用户id（提问者id）
     */
    private String userId;

    /**
     *用户名称
     */
    private String userName;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 分类id
     */
    private Integer typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 提问方式 1-悬赏  2-专家  3-免费
     */
    private Integer manner;

    /**
     * 专家id
     */
    private String professorId;
    private String professorName;

    /**
     * 悬赏金额
     */
    private Double money;

    /**
     * 问题浏览次数
     */
    private Long num;

    /**
     * 状态 0-未解决 1-已解决
     */
    private Integer status;
    private Integer payStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private String remark;


}
