package com.benwunet.bks.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksQuestion;
import com.benwunet.bks.model.BksQuestionAnswerPraise;
import com.benwunet.bks.model.dto.BksQuestionAnswerDTO;
import com.benwunet.bks.model.dto.BksQuestionDTO;
import com.benwunet.bks.model.vo.QueryVO;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zfy
 * @date 2019/8/21
 */
public interface BksQuestionService extends IService<BksQuestion> {

    ResponseResult getServiceTypes(QueryVO vo);

    PageResult getQuestions(QueryVO vo);

    ResponseResult getQuestion(QueryVO vo);

    ResponseResult addAnswer(BksQuestionAnswerDTO dto);

    ResponseResult addQuestion(BksQuestionDTO dto);

    ResponseResult getQuestionList(QueryVO vo);

    ResponseResult updateLike(BksQuestionAnswerPraise dto);

    ResponseResult getHome(QueryVO vo);

    ResponseResult getRewards(QueryVO vo);

    ResponseResult uploadFile(MultipartFile[] file);

    ResponseResult updateAccept(QueryVO vo);

    PageResult getProfessors(QueryVO vo);
}
