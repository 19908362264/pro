package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.BksSchool;
import com.benwunet.bks.model.dto.BksSchoolDTO;
import com.benwunet.bks.model.vo.BksSchoolVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BksSchoolDao extends BaseMapper<BksSchool> {

    /**
     * 查询高校（按照人气值排名）
     *
     * @return list
     */
    List<BksSchool> listSchoolByPopularity();


    /**
     * 查询高校专业（按照人气值排名）
     *
     * @return list
     */
    List<BksMajorCategory> listMajorByRanking();


    List<BksSchoolDTO> getSchoolList(@Param("schoolName") String schoolName, @Param("provinceId") String provinceId, @Param("levelId") String levelId, @Param("kindId") String kindId, @Param("page") Integer page, @Param("tx") String tx);

    @Select("SELECT\n" +
            "\ta.school_id,\n" +
            "\ta.school_name,\n" +
            "\tb.major_name\n" +
            "FROM\n" +
            "\tbks_school AS a\n" +
            "\t\n" +
            "\tINNER JOIN bks_major AS b ON a.school_id=b.school_id\n" +
            "WHERE\n" +
            "\ta.school_id=#{schoolId}")
    List<BksSchoolDTO> getSchoolMajorList(String schoolId);

    @Select("SELECT\n" +
            "\ta.school_id,\n" +
            "\ta.school_name,\n" +
            "\tb.major_name\n" +
            "FROM\n" +
            "\tbks_school AS a\n" +
            "\t\n" +
            "\tINNER JOIN bks_major AS b ON a.school_id=b.school_id\n" +
            "WHERE\n" +
            "\ta.school_id=#{schoolId}\n" +
            "\tLIMIT 0,4")
    List<BksSchoolDTO> getSchoolMajorLimitList(String schoolId);

    @Select("SELECT\n" +
            "\ta.school_id,\n" +
            "\ta.school_name,\n" +
            "\tb.major_name\n" +
            "FROM\n" +
            "\tbks_school AS a\n" +
            "\t\n" +
            "\tINNER JOIN bks_major AS b ON a.school_id=b.school_id\n" +
            "WHERE\n" +
            "\tb.major_category_id=#{majorId}\n" +
            "\tLIMIT #{page},10")
    List<BksSchoolDTO> getMajorSchoolList(String majorId, Integer page);

    int getSchoolListCount(@Param("schoolName") String schoolName, @Param("provinceId") String provinceId, @Param("levelId") String levelId, @Param("kindId") String kindId, @Param("tx") String tx);

    @Select("SELECT\n" +
            "\ta.school_id,\n" +
            "\ta.school_name,\n" +
            "\ta.f211,\n" +
            "\ta.f985,\n" +
            "\ta.department,\n" +
            "\ta.admissions,\n" +
            "\ta.central,\n" +
            "\ta.is_seal,\n" +
            "\ta.city_name,\n" +
            "\ta.site,\n" +
            "\ta.email,\n" +
            "\ta.town_name,\n" +
            "\ta.phone,\n" +
            "\ta.school_nature_name,\n" +
            "\tb.colleges_name,\n" +
            "\td.province_name,\n" +
            "\tc.campus_name\n" +
            "\n" +
            "FROM\n" +
            "\tbks_school AS a\n" +
            "\tINNER JOIN bks_colleges AS b ON a.colleges_id = b.colleges_id\n" +
            "\tINNER JOIN bks_campus AS c ON a.campus_id = c.campus_id\n" +
            "\tINNER JOIN bks_province AS d ON a.province_id = d.province_id \n" +
            "\n" +
            "WHERE\n" +
            "a.school_id=#{school_id}")
    BksSchoolDTO getOneSchool(@Param("school_id") String school_id);


    @Select("SELECT \n" +
            "school.school_id,\n" +
            "school.school_name,\n" +
            "province.province_name,\n" +
            "campus.campus_name,\n" +
            "popularity\n" +
            "\n" +
            "FROM\n" +
            "\n" +
            "bks_school school \n" +
            "INNER JOIN bks_province province ON school.province_id = province.province_id\n" +
            "INNER JOIN bks_campus campus ON school.campus_id = campus.campus_id\n" +
            "\n" +
            "ORDER BY school.popularity DESC\n" +
            "\n" +
            "LIMIT #{page}, 15")
    List<BksSchoolDTO> getSchoolHeat(Integer page);

    IPage<BksSchoolVO> matchUniversity(Page<BksSchoolVO> pageQuery, @Param("provinceId") String provinceId, @Param("score") Double score);

//
//    @Select("SELECT\n" +
//            "\ta.school_id,\n" +
//            "\ta.school_name,\n" +
//            "\tb.major_name\n" +
//            "FROM\n" +
//            "\tbks_school AS a\n" +
//            "\t\n" +
//            "\tINNER JOIN bks_major AS b ON a.school_id=b.school_id\n" +
//            "WHERE\n" +
//            "\ta.school_id=#{schoolId}")
//    List<BksSchoolDTO> getSchoolAndMajorList(String schoolId);
//
//    @Select("SELECT\n" +
//            "\ta.school_id,\n" +
//            "\ta.school_name,\n" +
//            "\tb.major_name\n" +
//            "FROM\n" +
//            "\tbks_school AS a\n" +
//            "\t\n" +
//            "\tINNER JOIN bks_major AS b ON a.school_id=b.school_id\n" +
//            "WHERE\n" +
//            "\ta.school_id=#{schoolId}\n" +
//            "\tLIMIT 0,4")
//    List<BksSchoolDTO> getSchoolAndMajorLimitList(String schoolId);

}
