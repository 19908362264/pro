package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksMajorCategoryDao;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import com.benwunet.bks.model.dto.BksSchoolDTO;
import com.benwunet.bks.service.BksMajorCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BksMajorCategoryServiceImpl extends ServiceImpl<BksMajorCategoryDao, BksMajorCategory> implements BksMajorCategoryService {

    @Autowired
    private  BksMajorCategoryDao majorCategoryDao;
    @Override
    public List<BksAttentionedMajorsDTO> getMajorHeat(Integer page) {

        return majorCategoryDao.getMajorHeat(page);
    }

    @Override
    public List<String> getMajor() {
        return majorCategoryDao.getMajor();
    }
}
