package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksMajor;

import java.util.List;

public interface BksMajorService extends IService<BksMajor> {

    List<BksMajor> getMajorInformationList(String major_category_id);

    Integer getMajorInformationListCount(String majorCategoryId);
}
