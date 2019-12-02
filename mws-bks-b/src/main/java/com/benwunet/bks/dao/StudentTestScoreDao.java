package com.benwunet.bks.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.entity.dto.StudentTestScoreDTO;
import com.benwunet.bks.model.BksStudentTestscore;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文件上传
 * @author FC
 * @date 2019/6/25 15:04
 */

public interface StudentTestScoreDao extends BaseMapper<BksStudentTestscore> {


    List<StudentTestScoreDTO> gradeAchievementSum(String examName);



    List<StudentTestScoreDTO> districtSum(String examName);



    List<StudentTestScoreDTO> provincesAchievementSum(String examName);






}
