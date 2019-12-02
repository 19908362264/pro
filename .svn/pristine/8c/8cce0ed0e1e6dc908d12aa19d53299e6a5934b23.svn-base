package com.benwunet.bks.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksProfessorAppointmentMapper;
import com.benwunet.bks.model.BksProfessorAppointment;
import com.benwunet.bks.service.BksProfessorAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zuoli
 * @since 2019-08-21
 */
@Service
public class BksProfessorAppointmentServiceImpl extends ServiceImpl<BksProfessorAppointmentMapper, BksProfessorAppointment> implements BksProfessorAppointmentService {

    @Override
    public boolean addProfessorAppointment(List<BksProfessorAppointment> list) {
        return  baseMapper.addProfessorAppointment(list);
    }
}
