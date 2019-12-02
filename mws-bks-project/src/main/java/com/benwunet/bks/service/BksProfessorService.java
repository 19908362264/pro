package com.benwunet.bks.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.model.dto.BksProfessorDTO;
import com.benwunet.bks.model.vo.ExpertConsultationVo;
import com.benwunet.bks.model.vo.QueryVO;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author C
 * @since 2019-08-20
 */
public interface BksProfessorService extends IService<BksProfessor> {

    List<BksProfessorDTO> getProfessorsList(ExpertConsultationVo expertConsultationVo);

    Integer getProfessorsListCount(ExpertConsultationVo expertConsultationVo);

    PageResult getAppointments(QueryVO vo);

    PageResult getQuestions(QueryVO vo);

    ResponseResult getWorks(QueryVO vo);
}
