package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksHighschool;
import com.benwunet.bks.model.BksProvince;

import java.util.List;

public interface BksProvinceDao extends BaseMapper<BksProvince> {

    /**
     * 查询省市区
     * @return List
     *
     */
    List<BksProvince> getDistrict();


    /**
     * 查询学校
     * @param provinceId
     * @return List
     */
    List<BksHighschool> getSchool(String provinceId);


}
