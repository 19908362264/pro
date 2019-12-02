package com.benwunet.bks.model;


import java.time.LocalDateTime;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zuoli
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksProfessorTime implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 预约id
     */
    private Integer appointmentId;
    private String professorId;

    private String date;

    private Integer timeType;
    /**
     * 1-上午 2-下午
     */
    private Integer type;

    //预约时间段的数量
    private Integer num;

    /**
     * 预约开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 预约结束时间
     */
    private LocalDateTime endTime;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String remark;


}
