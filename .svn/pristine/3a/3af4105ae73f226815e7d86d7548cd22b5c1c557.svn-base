package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksUserExam;
import com.benwunet.bks.model.dto.BksExamDTO;

import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/22 14:21
 */
public interface BksUserExamService extends IService<BksUserExam> {
    List<BksExamDTO> getExamList(String userId, String examId, String courseId);

    List<BksExamDTO> getExamBatch(String userId);
}
