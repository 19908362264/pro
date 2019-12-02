package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksUniversityEnrollGdf;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-25
 */
public interface BksUniversityEnrollGdfMapper extends BaseMapper<BksUniversityEnrollGdf> {

    List<BksUniversityEnrollGdf> matchMajor(String provinceId, Double score);
}
