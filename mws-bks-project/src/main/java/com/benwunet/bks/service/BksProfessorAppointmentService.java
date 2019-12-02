package com.benwunet.bks.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksProfessorAppointment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zuoli
 * @since 2019-08-21
 */
public interface BksProfessorAppointmentService extends IService<BksProfessorAppointment> {

    boolean addProfessorAppointment(List<BksProfessorAppointment> list);
}
