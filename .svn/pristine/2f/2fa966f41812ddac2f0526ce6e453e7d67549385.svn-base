package com.benwunet.bks.model;


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
public class BksQuestionAnswer implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 问题id
     */
    private Integer questionId;

    /**
     * 回答内容
     */
    private String answerContent;

    /**
     * 回答者id
     */
    private String userId;
    private String userName;

    /**
     * 答案类别 1-专家答案 2-普通答案
     */
    private Integer type;

    /**
     * 是否被采纳 0-否 1-是
     */
    private Integer acceptType;

    /**
     * 点赞数量
     */
    private Long num;

    private Date gmtCreate;

    private String remark;


}
