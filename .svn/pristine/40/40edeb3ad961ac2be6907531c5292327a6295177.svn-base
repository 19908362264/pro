package com.benwunet.bks.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zfy
 * @date 2019/8/21
 */
@Data
@Accessors(chain = true)
public class AnswerVO implements Serializable {

    private static final long serialVersionUID = -4829739251889262217L;

    private Integer id;

    /**
     * 回答内容
     */
    private String answerContent;

    /**
     * 回答者id
     */
    private String userId;

    /**
     * 答案类别 1-专家答案 2-普通答案
     */
    private Integer type;

    /**
     * 是否被采纳 0-否 1-是
     */
    private Integer acceptType;

    /**
     * 点赞状态 0-否 1-是
     */
    private Integer praiseType;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 回答数
     */
    private Long answerNum;

    /**
     * 被采纳数
     */
    private Long acceptedNum;

    /**
     * 点赞数量
     */
    private Long likeNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private Date gmtCreate;

    private List<String> urls;

}
