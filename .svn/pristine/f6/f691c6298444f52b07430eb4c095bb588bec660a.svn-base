package com.benwunet.bks.service.impl;

import com.benwunet.bks.dao.BksUserDao;
import com.benwunet.bks.dto.MajorCategory;
import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.service.BaseService;
import com.benwunet.bks.service.BksUserService;
import com.benwunet.bks.model.vo.MajorCategoryVo;
import com.benwunet.mws.model.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BksUserService bksUserService;

    @Autowired
    private BksUserDao bksUserDao;

    @Override
    public void save(BksUser user) {
        boolean save = bksUserService.save(user);
        if (!save){
            throw new ServerException();
        }
    }

    @Override
    public List<MajorCategory> findMajorCategory(Integer school, String name, Integer page) {
        MajorCategoryVo entity = new MajorCategoryVo();
        entity.setMajorCategoryId(school.toString());
        entity.setMajorName(name);
        entity.setPage(page);
        return bksUserDao.findMajorCategory(entity);
    }

    @Override
    public Integer findMajorCategoryCount(Integer school, String name) {
        MajorCategory entity = new MajorCategory();
        entity.setMajorCategoryId(school.toString());
        entity.setMajorName(name);
        return bksUserDao.findMajorCategoryCount(entity);
    }
}
