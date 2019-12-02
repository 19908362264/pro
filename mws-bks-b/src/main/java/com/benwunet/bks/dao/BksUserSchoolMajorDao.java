package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksUserSchoolMajor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BksUserSchoolMajorDao extends BaseMapper<BksUserSchoolMajor> {

    @Select("SELECT 1 FROM bks_user_school_major WHERE major_id = #{majorCategoryId} AND user_id = #{userId}")
    Integer getMajorConcern(@Param("userId") String userId, @Param("majorCategoryId") String majorCategoryId);

    @Select("SELECT 1 FROM bks_user_school_major WHERE school_id = ${schoolId} AND user_id =${userId}")
    Integer getSchoolConcern(@Param("userId") String userId, @Param("schoolId") String schoolId);


    /**
     * 查询关注 接口
     * @author FC
     * @param userId
     * @return list
     * @date 2019/6/18 16:12
     */
    List<BksUserSchoolMajor> queryAttention(String type, String userId, Integer pag);

}
