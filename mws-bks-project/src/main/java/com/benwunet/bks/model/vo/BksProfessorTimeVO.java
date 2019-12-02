package com.benwunet.bks.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/23 10:11
 */
@Data
public class BksProfessorTimeVO  {

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String date;

    private Integer timeType;

    private Integer type;

//    /**
//     * 预约开始时间
//     */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone="GMT+8")
//    private LocalDateTime beginTime;
//
//    /**
//     * 预约结束时间
//     */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss ",timezone="GMT+8")
//    private LocalDateTime endTime;
}
