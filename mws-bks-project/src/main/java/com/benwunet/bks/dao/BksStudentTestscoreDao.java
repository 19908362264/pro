package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksStudentTestscore;
import com.benwunet.bks.model.dto.BksExamDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/26 11:50
 */
public interface BksStudentTestscoreDao extends BaseMapper<BksStudentTestscore> {

    List<BksExamDTO> getExamList(@Param("userId") String userId, @Param("examId") String examId);


    List<BksExamDTO> getExamAnalyse(String userId);

    BksStudentTestscore getStudetnScore(String studentCode);

}
