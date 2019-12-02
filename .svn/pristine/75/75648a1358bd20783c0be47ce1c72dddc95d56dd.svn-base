package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksSchoolScore;
import com.benwunet.bks.model.dto.BksSchoolScoreDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BksSchoolScoreDao extends BaseMapper<BksSchoolScore> {


  /*  @Select("SELECT \n" +
            "DISTINCT\n" +
            "\tschool_score.school_id,\n" +
            "\tschool.school_name,\n" +
            "\tcampus.campus_name,\n" +
            "\tcolleges.colleges_name,\n" +
            "\tprovince.province_name,\n" +
            "\tschool_score.average AS avg,\n" +
            "\tschool_score.`year`,\n" +
            "\tschool_score.min,\n" +
            "\tschool_score.max,\n" +
            "\tschool_score.score AS province_score,\n" +
            "\tschool_score.batch,\n" +
            "\t\tschool_score.local_batch_name\n" +
            "FROM\n" +
            "\tbks_school_score school_score\n" +
            "\tINNER JOIN bks_school school ON school_score.school_id = school.school_id\n" +
            "\tINNER JOIN bks_campus campus ON school.campus_id = campus.campus_id\n" +
            "\tINNER JOIN bks_colleges colleges ON school.colleges_id = colleges.colleges_id\n" +
            "\tINNER JOIN bks_province province ON school.province_id = province.province_id \n" +
            "WHERE\n" +
            "\tschool_score.MAX <> '--' \n" +
            "\tAND school_score.MIN <> '--' \n" +
            "\tAND school_score.average <> '--' \n" +
            "\tAND school_score.MIN < #{score} \n" +
            "\tAND school_score.MIN +'200' > #{score} AND\n" +
            "\tschool_score.province = #{cityId} \n" +
            "\tAND school.province_id = #{schoolProvinceId} \n" +
            "\tAND school_score.type = #{type} \n" +
            "\tAND YEAR IN ( '2018', '2017' ) \n" +
            "\tGROUP BY\n" +
            "\tschool.school_name\n" +
            "-- \tAND school_score.batch IN ( '7', '10' ) \n" +
            "ORDER BY\n" +
            "\tschool_score.MIN DESC ,\n" +
            "\tschool_score.`year` DESC\n" +
            "\tLIMIT #{page},\n" +
            "\t10\n" +
            "\t")*/
    List<BksSchoolScoreDTO> getVoluntaryForecasting(String cityId, String schoolProvinceId, String type, String score, Integer page);


    /*@Select("\t\n" +
            "\tSELECT\n" +
            "\n" +
            "\tcount(DISTINCT school.school_name)\n" +
            "\n" +
            "FROM\n" +
            "\tbks_school_score school_score\n" +
            "\tINNER JOIN bks_school school ON school_score.school_id = school.school_id\n" +
            "\tINNER JOIN bks_campus campus ON school.campus_id = campus.campus_id\n" +
            "\tINNER JOIN bks_colleges colleges ON school.colleges_id = colleges.colleges_id\n" +
            "\tINNER JOIN bks_province province ON school.province_id = province.province_id \n" +
            "WHERE\n" +
            "\tschool_score.MAX <> '--' \n" +
            "\tAND school_score.MIN <> '--' \n" +
            "\tAND school_score.average <> '--' \n" +
            "\tAND school_score.MIN < #{score} \n" +
            "\tAND school_score.MIN +'200' > #{score} AND\n" +
            "\tschool_score.province = #{cityId} \n" +
            "\tAND school.province_id = #{schoolProvinceId} \n" +
            "\tAND school_score.type = #{type} \n" +
            "\tAND YEAR IN ( '2018', '2017' ) ")*/
    Integer getVoluntaryForecastingCount(String cityId, String schoolProvinceId, String type, String score);



@Select("SELECT DISTINCT\n" +
        "  school_score.type ,\n" +
        "\tschool_score.average AS avg,\n" +
        "\tschool_score.`year`,\n" +
        "\tschool_score.min,\n" +
        "\tschool_score.max,\n" +
        "\tschool_score.score  province_score,\n" +
        "\tschool_score.local_batch_name AS batch\n" +
        "FROM\n" +
        "\tbks_school_score school_score\n" +
        "\tINNER JOIN bks_school school ON school_score.school_id = school.school_id \n" +
        "\tINNER JOIN bks_province province ON school.province_id= province.province_id\t\t\n" +
        "WHERE\n" +
        "\tschool_score.province = '50' \n" +
        "\tAND school_score.school_id=#{schoolId}\n" +
        "\tORDER BY school_score.`year`DESC\n" +
        "\t")
    List<BksSchoolScoreDTO> getBorderline(String schoolId, Integer page);
//LIMIT #{page},10

@Select("SELECT \n" +
        "count(1)" +
        "FROM\n" +
        "\tbks_school_score school_score\n" +
        "\tINNER JOIN bks_school school ON school_score.school_id = school.school_id \n" +
        "\tINNER JOIN bks_province province ON school.province_id= province.province_id\t\t\n" +
        "WHERE\n" +
        "\tschool_score.province = '50' \n" +
        "\tAND school_score.school_id=#{schoolId}\n" +
        "\tORDER BY school_score.`year`DESC")
    Integer getBorderlineCount(String schoolId);
}
