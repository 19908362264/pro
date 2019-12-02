package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksUserSchoolMajorDao;
import com.benwunet.bks.model.BksUserSchoolMajor;
import com.benwunet.bks.service.BksUserSchoolMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BksUserSchoolMajorServiceImpl extends ServiceImpl<BksUserSchoolMajorDao, BksUserSchoolMajor> implements BksUserSchoolMajorService {
    @Autowired
    private BksUserSchoolMajorDao userSchoolMajorDao;

    @Override
    public Integer getMajorConcern(String userId, String majorCategoryId) {
        return userSchoolMajorDao.getMajorConcern(userId,majorCategoryId);
    }

    @Override
    public Integer getSchoolConcern(String userId, String schoolId) {

        return userSchoolMajorDao.getSchoolConcern(userId,schoolId);
    }

    @Override
    public List<BksUserSchoolMajor> queryAttention(String type, String userId, Integer pag) {
        return userSchoolMajorDao.queryAttention(type,userId,pag);
    }


}
