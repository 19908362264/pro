package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksMajor;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BksMajorDao extends BaseMapper<BksMajor> {

    @Select("SELECT a.major_id, a.major_name, c.`code`, c.bachelor_degree, c.level1_name, b.school_name, b.school_profile, b.address, c.learn_what FROM bks_major_category AS c LEFT JOIN bks_major AS a ON  a.major_category_id = c.major_category_id LEFT JOIN bks_school AS b ON a.school_id = b.school_id WHERE a.major_category_id = #{major_category_id}")
    List<BksMajor> getMajorInformationList(String major_category_id);


    @Select("SELECT count(1) FROM bks_major_category AS c LEFT JOIN bks_major AS a ON  a.major_category_id = c.major_category_id LEFT JOIN bks_school AS b ON a.school_id = b.school_id WHERE a.major_category_id = #{major_category_id}")
    Integer getMajorInformationListCount(String majorCategoryId);

}
