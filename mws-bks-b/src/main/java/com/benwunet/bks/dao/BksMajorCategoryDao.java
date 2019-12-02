package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BksMajorCategoryDao extends BaseMapper<BksMajorCategory> {

    @Select("SELECT \n" +
            "major_category.major_category_name,\n" +
            "major_category.major_category_id,\n" +
            "major_category.level2_name,\n" +
            "major_category.level1_name,\n" +
            "major_category.major_category_heat_ranking\n" +
            "-- major_category.major_category_name\n" +
            "\n" +
            "FROM\n" +
            "bks_major_category major_category\n" +
            "\n" +
            "WHERE major_category.parent_id = 2\n" +
            "\n" +
            "ORDER BY major_category.major_category_heat_ranking DESC\n" +
            "\n" +
            "LIMIT #{page}, #{size}")
    List<BksAttentionedMajorsDTO> getMajorHeat(@Param("page") Integer page, @Param("size") Integer size);
}
