package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksUniversityEnrollGdfMapper;
import com.benwunet.bks.model.BksUniversityEnrollGdf;
import com.benwunet.bks.service.BksUniversityEnrollGdfService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-25
 */
@Service
public class BksUniversityEnrollGdfServiceImpl extends ServiceImpl<BksUniversityEnrollGdfMapper, BksUniversityEnrollGdf> implements BksUniversityEnrollGdfService {

    @Override
    public List<BksUniversityEnrollGdf> matchMajor(String provinceId, Double score) {
        return baseMapper.matchMajor(provinceId,score);
    }
}
