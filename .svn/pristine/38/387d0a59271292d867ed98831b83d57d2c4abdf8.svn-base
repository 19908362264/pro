package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksProvinceDao;
import com.benwunet.bks.model.BksHighschool;
import com.benwunet.bks.model.BksProvince;
import com.benwunet.bks.model.BksSchool;
import com.benwunet.bks.service.BksProvinceService;
import com.benwunet.mws.model.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BksProvinceServiceImpl extends ServiceImpl<BksProvinceDao,BksProvince> implements BksProvinceService {

    @Resource
    BksProvinceDao provinceDao;

    @Override
    public List<BksProvince> getDistrict() {
        return provinceDao.getDistrict();
    }

    @Override
    public List<BksHighschool> getSchool(String provinceId) {
        return provinceDao.getSchool(provinceId);
    }
}
