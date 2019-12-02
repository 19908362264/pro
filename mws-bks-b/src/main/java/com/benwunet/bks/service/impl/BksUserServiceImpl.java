package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksUserDao;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import com.benwunet.bks.model.dto.BksAttentionedSchoolsDTO;
import com.benwunet.bks.service.BksUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BksUserServiceImpl extends ServiceImpl<BksUserDao, BksUser> implements BksUserService {
    @Autowired
    BksUserDao userDao;

    @Override
    public List<BksMajorCategory> getCategory(String collegesId) {
        return userDao.getCategory(collegesId);
    }



    @Override
    public Integer getSchoolMajorTotal(String type, String userId) {
        return userDao.getSchoolMajorTotal(type,userId);
    }

    @Override
    public BksAttentionedSchoolsDTO getAttentionedSchoolDetails(String schoolId) {
        return userDao.getAttentionedSchoolDetails(schoolId);
    }

    @Override
    public BksAttentionedSchoolsDTO getSchoolScore(String schoolId,String provinceId) {
        return userDao.getSchoolScore(schoolId,provinceId);
    }

    @Override
    public BksAttentionedMajorsDTO getAttentionedMajorDetails(String majorCategoryId) {
        return userDao.getAttentionedMajorDetails(majorCategoryId);
    }

    @Override
    public Integer countUniversity(String mcId) {
        return userDao.countUniversity(mcId);
    }
}
