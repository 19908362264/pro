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
public class QuestionInfoVO implements Serializable {

    private static final long serialVersionUID = -7874835029428969747L;

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 用户id（提问者id）
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 年级
     */
    private String className;

    /**
     * 省份
     */
    private String provinceName;

    /**
     * 回答次数
     */
    private Long answersNum;

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

    /**
     * 分类id
     */
    private Integer typeId;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private Date answerTime;

    private List<AnswerVO> answers;

    private List<String> urls;


}
