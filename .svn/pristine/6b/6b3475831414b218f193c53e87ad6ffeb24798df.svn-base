package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksStudentTestscoreDao;
import com.benwunet.bks.model.BksStudentTestscore;
import com.benwunet.bks.model.dto.BksExamDTO;
import com.benwunet.bks.service.BksStudentTestscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/26 11:51
 */
@Service
public class BksStudentTestscoreServiceImpl extends ServiceImpl<BksStudentTestscoreDao, BksStudentTestscore> implements BksStudentTestscoreService {

    @Autowired
    private BksStudentTestscoreDao testscoreDao;

    @Override
    public List<BksExamDTO> getExamList(String userId, String examId) {

        return testscoreDao.getExamList(userId,examId);
    }

    @Override
    public List<BksExamDTO> getExamAnalyse(String userId) {
        return testscoreDao.getExamAnalyse(userId);
    }

    @Override
    public BksStudentTestscore getStudetnScore(String studentCode) {
        return testscoreDao.getStudetnScore(studentCode);
    }

}
