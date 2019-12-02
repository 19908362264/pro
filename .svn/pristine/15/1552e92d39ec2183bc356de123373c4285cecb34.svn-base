package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.entity.homepage.ScoreRanking;
import com.benwunet.bks.model.BksExamAvgAchievement;
import com.benwunet.bks.model.BksExamUpload;
import com.benwunet.bks.model.BksGradeSubsection;
import com.benwunet.bks.model.BksStudentTestscore;
import com.benwunet.bks.model.dto.BksExamDTO;
import com.benwunet.mws.model.vo.QueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/26 11:50
 */
public interface BksStudentTestscoreDao extends BaseMapper<BksStudentTestscore> {

    List<BksExamDTO> getExamList1(@Param("userId") String userId, @Param("examId") String examId);


    List<BksExamDTO> getExamAnalyse(String userId);

    BksStudentTestscore getStudetnScore(String studentCode);

    List<BksStudentTestscore> getStudentExamList(@Param("year") String year, @Param("examName")String examName,@Param("schoolName")String schoolName, @Param("keyword") String keyword, @Param("page") Integer page, @Param("size") Integer size);
    Integer getStudentExamListCount(@Param("year")String year, @Param("examName")String examName,@Param("schoolName")String schoolName,  @Param("keyword")String keyword);

    List<BksStudentTestscore> getStudentExamRankList(@Param("year") String year, @Param("examName") String examName,@Param("schoolName") String schoolName, @Param("courseId") String courseId, @Param("page") Integer page, @Param("size") Integer size);

    List<ScoreRanking> getStudentTotalRankList(QueryVO vo);

    List<BksStudentTestscore> getTestScores(QueryVO vo);

    BksExamAvgAchievement getAvgAchievement(QueryVO vo);

    List<BksGradeSubsection> getScoreRangeData(QueryVO vo);


    Integer getStudentExamRankListCount(@Param("schoolYear")String schoolYear,  @Param("examName") String examName, @Param("schoolName")String schoolName, @Param("courseId") String courseId);

    List<BksGradeSubsection> getSubsection(QueryVO vo);

    List<BksExamUpload> getUpload(String schoolName);
}
