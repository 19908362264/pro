package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksStudentScoreDao;
import com.benwunet.bks.model.BksStudentScore;
import com.benwunet.bks.service.BksStudentScoreService;
import org.springframework.stereotype.Service;

@Service
public class BksStudentScoreServiceImpl extends ServiceImpl<BksStudentScoreDao, BksStudentScore> implements BksStudentScoreService {
}
