package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksUserExamDao;
import com.benwunet.bks.model.BksUserExam;
import com.benwunet.bks.model.dto.BksExamDTO;
import com.benwunet.bks.service.BksUserExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/22 14:22
 */
@Service
public class BksUserExamServiceImpl extends ServiceImpl<BksUserExamDao, BksUserExam> implements BksUserExamService {

    @Autowired
    private BksUserExamDao bksUserExamDao;

    @Override
    public List<BksExamDTO> getExamList(String userId, String examId, String courseId) {
        return bksUserExamDao.getExamList(userId,examId,courseId);
    }

    @Override
    public List<BksExamDTO> getExamBatch(String userId) {
        return bksUserExamDao.getExamBatch(userId);

    }
}
