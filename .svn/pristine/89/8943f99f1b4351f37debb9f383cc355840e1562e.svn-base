package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.StudentTestScoreDao;
import com.benwunet.bks.entity.dto.StudentTestScoreDTO;
import com.benwunet.bks.model.BksStudentTestscore;
import com.benwunet.bks.service.StudentTestScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 文件上传
 * @author FC
 * @date 2019/6/25 15:04
 */
@Service
public class StudentTestScoreServiceImpl extends ServiceImpl<StudentTestScoreDao, BksStudentTestscore> implements StudentTestScoreService {

    @Resource
    StudentTestScoreDao scoreDao;

    @Override
    public List<StudentTestScoreDTO> gradeAchievementSum(String examName) {
        return scoreDao.gradeAchievementSum(examName);
    }

    @Override
    public List<StudentTestScoreDTO> districtSum(String examName) {
        return scoreDao.districtSum(examName);
    }

    @Override
    public List<StudentTestScoreDTO> provincesAchievementSum(String examName) {
        return scoreDao.provincesAchievementSum(examName);
    }
}
