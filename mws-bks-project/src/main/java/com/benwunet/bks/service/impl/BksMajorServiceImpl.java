package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksMajorDao;
import com.benwunet.bks.model.BksMajor;
import com.benwunet.bks.service.BksMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BksMajorServiceImpl extends ServiceImpl<BksMajorDao, BksMajor> implements BksMajorService{
    @Autowired
    private BksMajorDao majorDao;

    @Override
    public List<BksMajor> getMajorInformationList(String major_category_id) {


        return majorDao.getMajorInformationList(major_category_id);
    }

    @Override
    public Integer getMajorInformationListCount(String majorCategoryId) {
       return majorDao.getMajorInformationListCount(majorCategoryId);

    }
}
