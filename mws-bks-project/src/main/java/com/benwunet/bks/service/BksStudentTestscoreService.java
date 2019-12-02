package com.benwunet.bks.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksStudentTestscore;
import com.benwunet.bks.model.dto.BksExamDTO;

import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/26 11:51
 */
public interface BksStudentTestscoreService extends IService<BksStudentTestscore> {
    List<BksExamDTO> getExamList(String userId, String examId);

    List<BksExamDTO> getExamAnalyse(String userId);


    BksStudentTestscore getStudetnScore(String studentCode);

}
