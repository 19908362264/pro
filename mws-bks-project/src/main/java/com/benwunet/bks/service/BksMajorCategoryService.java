package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import com.benwunet.bks.model.dto.BksSchoolDTO;

import java.util.List;

public interface BksMajorCategoryService extends IService<BksMajorCategory> {

    List<BksAttentionedMajorsDTO> getMajorHeat(Integer page);

    List<String> getMajor();
}
