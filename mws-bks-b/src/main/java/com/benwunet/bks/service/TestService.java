package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.entity.dto.StudentTestScoreDTO;
import com.benwunet.bks.model.BksStudentTestscore;

public interface TestService extends IService<BksStudentTestscore> {
    StudentTestScoreDTO test(String str);
}
