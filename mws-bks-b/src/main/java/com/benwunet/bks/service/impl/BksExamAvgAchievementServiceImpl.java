package com.benwunet.bks.service.impl;

import com.benwunet.bks.entity.homepage.BksExamAvgAchievementVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksExamAvgAchievementMapper;
import com.benwunet.bks.model.BksExamAvgAchievement;
import com.benwunet.bks.service.BksExamAvgAchievementService;

import java.util.List;

/**
 * <p>
 * 考试平均分 服务实现类
 * </p>
 *
 * @author C
 * @since 2019-06-25
 */
@Service
public class BksExamAvgAchievementServiceImpl extends ServiceImpl<BksExamAvgAchievementMapper, BksExamAvgAchievement> implements BksExamAvgAchievementService {

    @Override
    public List<BksExamAvgAchievementVo> getAvgScorelist(String year,String schoolName) {
        return baseMapper.getAvgScorelist(year,schoolName);
    }
}
