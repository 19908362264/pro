package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksHighschool;
import com.benwunet.bks.model.BksProvince;
import com.benwunet.bks.model.BksSchool;
import com.benwunet.mws.model.result.ResponseResult;

import java.util.List;

public interface BksProvinceService extends IService<BksProvince> {

    /**
     * 查询省市区
     * @return List
     */
    List<BksProvince> getDistrict();

    /**
     * 查询学校
     * @param provinceId
     * @return List
     */
    List<BksHighschool> getSchool(String provinceId);
}
