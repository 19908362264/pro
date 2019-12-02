package com.benwunet.bks.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zfy
 * @date 2019/8/27
 */
@Data
@Accessors(chain = true)
public class PopularVO implements Serializable {

    private static final long serialVersionUID = -3001230446160268665L;

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 回答次数
     */
    private Long answerNum;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone="GMT+8")
    private Date gmtCreate;

}
