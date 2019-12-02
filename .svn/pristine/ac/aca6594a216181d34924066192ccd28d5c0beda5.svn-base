package com.benwunet.bks.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.model.dto.BksProfessorDTO;
import com.benwunet.bks.model.vo.BksProfessorTimeVO;
import com.benwunet.bks.model.vo.ExpertConsultationVo;

import com.benwunet.bks.model.vo.ProfessorAppointmentVO;
import com.benwunet.bks.model.vo.QueryVO;
import com.benwunet.bks.model.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author C
 * @since 2019-08-20
 */
public interface BksProfessorMapper extends BaseMapper<BksProfessor> {

    List<BksProfessorDTO> getProfessorsList(ExpertConsultationVo expertConsultationVo);

    Integer getProfessorsListCount(ExpertConsultationVo expertConsultationVo);

    List<ProfessorAppointmentVO> getAppointments(QueryVO vo);

    List<BksProfessorTimeVO> getTimes(@Param("id") Integer id, @Param("date") String date);

    List<WorkVO> getWorks(QueryVO vo);

    List<QuestionInfoVO> getQuestions(QueryVO vo);

    int getWorkNum(QueryVO vo);
}
