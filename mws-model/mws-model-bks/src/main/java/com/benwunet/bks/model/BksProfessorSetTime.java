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
 * @since 2019-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksProfessorSetTime implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;
    /**
     * 专家id
     */
    private String professorId;

    /**
     * 日期 2019-8-29
     */

    private String date;

    /**
     * 0-时间段 1-上午 2-下午 3-全天
     */
    /**
     * 1--8:00-9:00
     * 2- 9:00-10:00
     * 3--10:00-11:00
     * 4--11:00-12:00
     * 5--13:30-14:30
     * 6--14:30-15:30
     * 7--15:30-16:30
     * 8--16:30-17:30
     */
    private Integer timeType;
    private Integer type;

    private Integer status;
    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String remark;

    public static BksProfessorSetTime of(){

        return new BksProfessorSetTime();
    }


}
