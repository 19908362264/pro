package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksStudentTestscoreDao;
import com.benwunet.bks.model.BksStudentTestscore;
import com.benwunet.bks.model.dto.BksExamDTO;
import com.benwunet.bks.service.BksStudentTestscoreService;
import org.apache.ibatis.annotations.Param;
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
    public List<BksExamDTO> getExamList1(String userId, String examId) {

        return testscoreDao.getExamList1(userId,examId);
    }

    @Override
    public List<BksExamDTO> getExamAnalyse(String userId) {
        return testscoreDao.getExamAnalyse(userId);
    }

    @Override
    public BksStudentTestscore getStudetnScore(String studentCode) {
        return testscoreDao.getStudetnScore(studentCode);
    }

    @Override
    public List<BksStudentTestscore> getStudentExamList(@Param("year") String year, @Param("examName") String examName,String schoolName,@Param("keyword") String keyword, Integer page, Integer size) {
        return testscoreDao.getStudentExamList(year,examName,schoolName,keyword,page,size);
    }

    @Override
    public List<BksStudentTestscore> getStudentExamRankList(String year, String examName,String schoolName, String courseId, Integer page, Integer size) {
        return testscoreDao.getStudentExamRankList(year,examName,schoolName,courseId,page,size);
    }

    @Override
    public Integer getStudentExamListCount(String year, String examName,String schoolName, String keyword) {
        return  testscoreDao.getStudentExamListCount(year,examName,schoolName,keyword);
    }

    @Override
    public Integer getStudentExamRankListCount(String schoolYear, String examName,String schoolName, String courseId) {

        return  testscoreDao.getStudentExamRankListCount(schoolYear,examName,schoolName,courseId);
    }


}
