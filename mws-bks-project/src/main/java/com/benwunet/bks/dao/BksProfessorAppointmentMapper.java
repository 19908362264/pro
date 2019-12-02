package com.benwunet.bks.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksProfessorAppointment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zuoli
 * @since 2019-08-21
 */
public interface BksProfessorAppointmentMapper extends BaseMapper<BksProfessorAppointment> {

    boolean addProfessorAppointment(@Param("list") List<BksProfessorAppointment> list);
}
