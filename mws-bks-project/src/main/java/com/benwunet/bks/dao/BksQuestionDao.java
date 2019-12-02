package com.benwunet.bks.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.dto.BksQuestionAnswerDTO;
import com.benwunet.bks.model.dto.BksQuestionDTO;
import com.benwunet.bks.model.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author C
 * @since 2019-08-20
 */
public interface BksQuestionDao extends BaseMapper<BksQuestion> {

    List<BksServiceType> getServiceTypes(QueryVO vo);

    List<QuestionVO> getQuestions(QueryVO vo);

    Long getQuestionCount(QueryVO vo);

    int addAnswer(BksQuestionAnswerDTO dto);

    int addQuestion(BksQuestionDTO dto);

    void addPic(@Param("questionId") Integer questionId, @Param("answerId") Integer answerId, @Param("urls") List<String> urls);

    QuestionInfoVO getQuestion(Integer id);

    List<AnswerVO> getAnswers(Integer id);

    AnswerVO getAnswer(String userId);

    List<BksQuestionChoiceness> getEssential();

    List<RewardVO> getHighPrice();

    List<BksProfessor> getProfessors();

    Long getAnswerNum();

    void updateNum(@Param("num") long num, @Param("id") Integer id);

    long getAnswerById(Integer id);

    int updateLikeNum(@Param("num") long num, @Param("id") Integer id);

    List<BannerVO> getBanners(Integer typeId);

    List<ProfessorVO> getProfessorList();

    List<PopularVO> getPopulars(QueryVO vo);

    List<RewardVO> getRewards(QueryVO vo);

    List<String> getQuestionPics(Integer id);

    List<String> getAnswerPics(Integer id);

    String getQuestionUserId(Integer id);

    int updateAccept(Integer id);

    int checkAnswer(BksQuestionAnswerDTO dto);

    int getPraise(QueryVO vo);

    List<ProfessorVO> getQuestionProfessors(QueryVO vo);
}

