package com.benwunet.bks.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zfy
 * @date 2019/8/21
 */
@Data
@Accessors(chain = true)
public class QuestionVO implements Serializable {

    private static final long serialVersionUID = -2246544274341512638L;

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 问题答案
     */
    private String answerContent;

    /**
     * 回答次数
     */
    private Long answerNum;

    /**
     * 状态 0-未解决 1-已解决
     */
    private Integer status;

    /**
     * 金额
     */
    private Double money;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone="GMT+8")
    private Date gmtCreate;

}
