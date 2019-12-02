package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksExamUpload;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author C
 * @since 2019-07-01
 */
public interface BksExamUploadDao extends BaseMapper<BksExamUpload> {

    @Select("SELECT DISTINCT exam_name FROM  `bks_exam_upload` where school_name = #{schoolName} and status = 1 order by gmt_create desc")
    List<BksExamUpload> getlist(String schoolName );

    @Select("SELECT DISTINCT school_years FROM  `bks_exam_upload` where status = 1 order by gmt_create desc")
    List<BksExamUpload> getYearlist();

    @Select("SELECT DISTINCT school_years FROM  `bks_exam_upload` where school_name=#{schoolName}and status = 1 order by gmt_create desc")
    List<BksExamUpload> getYearlistBySchool(String schoolName);

    @Select("SELECT DISTINCT exam_name FROM  `bks_exam_upload` where school_years=#{schoolYear}and status = 1 order by gmt_create desc")
    List<BksExamUpload> getExamListByYear(String schoolYear);
}
