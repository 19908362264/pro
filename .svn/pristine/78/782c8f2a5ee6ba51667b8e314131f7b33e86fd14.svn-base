package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksProfessorMapper;
import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.model.dto.BksProfessorDTO;
import com.benwunet.bks.model.vo.*;
import com.benwunet.bks.service.BksProfessorService;
import com.benwunet.bks.util.DateUtils;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author C
 * @since 2019-08-20
 */
@Service
public class BksProfessorServiceImpl extends ServiceImpl<BksProfessorMapper, BksProfessor> implements BksProfessorService {

    @Autowired
    private BksProfessorMapper bksProfessorMapper;

    @Override
    public List<BksProfessorDTO> getProfessorsList(ExpertConsultationVo expertConsultationVo) {
        return bksProfessorMapper.getProfessorsList(expertConsultationVo);
    }

    @Override
    public Integer getProfessorsListCount(ExpertConsultationVo expertConsultationVo) {
        return bksProfessorMapper.getProfessorsListCount(expertConsultationVo);
    }

    @Override
    public PageResult getAppointments(QueryVO vo) {
        //预约列表
        List<ProfessorAppointmentVO> list = bksProfessorMapper.getAppointments(vo);
        //预约时间
        list.forEach(appointmentVO -> {
            List<BksProfessorTimeVO> times = bksProfessorMapper.getTimes(appointmentVO.getId(), vo.getDate());
            appointmentVO.setList(times);
        });
        return list.isEmpty() ? new PageResult(vo.getPageCurrent(), vo.getPageSize(), 0, null) : new PageResult(vo.getPageCurrent(), vo.getPageSize(), list.size(), list);
    }

    @Override
    public PageResult getQuestions(QueryVO vo) {
        List<QuestionInfoVO> list = bksProfessorMapper.getQuestions(vo);
        return list.isEmpty() ? new PageResult(vo.getPageCurrent(), vo.getPageSize(), 0, null) : new PageResult(vo.getPageCurrent(), vo.getPageSize(), list.size(), list);
    }

    @Override
    public ResponseResult getWorks(QueryVO vo) {

        List<String> dateList = DateUtils.complementTime(vo.getBeginTime(), vo.getEndTime());
        vo.setDates(dateList);
        List<WorkVO> works = bksProfessorMapper.getWorks(vo);

        List<WorksVO> worksList = dateList.stream().map(date -> WorksVO.of().setDate(date))
                .peek(work -> {
                    WorkVO workVO = works.stream().filter(p -> p.getDate().equals(work.getDate()))
                            .findFirst().orElse(WorkVO.of().setStatusList(null));
                    work.setStatusList(workVO.getStatusList());
                }).collect(Collectors.toList());

        //待办工作数
       int backlogNum = bksProfessorMapper.getWorkNum(vo);
       return ResponseResult.app(0, ResultCode.PT_OK, "OK", WorkBenchVO.of().setBacklogNum(backlogNum).setWorkList(worksList));
    }


}
