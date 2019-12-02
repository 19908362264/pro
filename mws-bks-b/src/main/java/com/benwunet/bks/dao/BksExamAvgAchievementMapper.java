package com.benwunet.bks.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.entity.homepage.BksExamAvgAchievementVo;
import com.benwunet.bks.model.BksExamAvgAchievement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 考试平均分 Mapper 接口
 * </p>
 *
 * @author C
 * @since 2019-06-25
 */
public interface BksExamAvgAchievementMapper extends BaseMapper<BksExamAvgAchievement> {
    @Select("SELECT grade_avg_achievement_yw,exam_batches,school_name,\n" +
            "grade_avg_achievement_sx,\n" +
            "grade_avg_achievement_yy,\n" +
            "max(grade_avg_achievement_wl) grade_avg_achievement_wl,\n" +
            "max(grade_avg_achievement_ls) grade_avg_achievement_ls,\n" +
            "max(grade_avg_achievement_zz) grade_avg_achievement_zz,\n" +
            "max(grade_avg_achievement_hx) grade_avg_achievement_hx,\n" +
            "max(grade_avg_achievement_sw) grade_avg_achievement_sw,\n" +
            "max(grade_avg_achievement_dl) grade_avg_achievement_dl,\n" +
            "provinces_avg_achievement_yw ,\n" +
            "provinces_avg_achievement_sx ,\n" +
            "provinces_avg_achievement_yy ,\n" +
            "max(provinces_avg_achievement_wl) provinces_avg_achievement_wl,\n" +
            "max(provinces_avg_achievement_ls) provinces_avg_achievement_ls,\n" +
            "max(provinces_avg_achievement_zz) provinces_avg_achievement_zz,\n" +
            "max(provinces_avg_achievement_hx) provinces_avg_achievement_hx,\n" +
            "max(provinces_avg_achievement_sw) provinces_avg_achievement_sw,\n" +
            "district_avg_achievement_yw,\n" +
            "district_avg_achievement_sx,\n" +
            "district_avg_achievement_yy,\n" +
            "district_avg_achievement,\n" +
            "grade_avg_achievement,\n" +
            "provinces_avg_achievement,\n" +
            "max(district_avg_achievement_wl) district_avg_achievement_wl,\n" +
            "max(district_avg_achievement_ls) district_avg_achievement_ls,\n" +
            "max(district_avg_achievement_zz) district_avg_achievement_zz,\n" +
            "max(district_avg_achievement_hx) district_avg_achievement_hx,\n" +
            "max(district_avg_achievement_sw) district_avg_achievement_sw,\n" +
            "max(district_avg_achievement_dl) district_avg_achievement_dl FROM bks_exam_avg_achievement WHERE "+
            "(case when #{year} is not null and #{year} != '' then (school_years=#{year}) else (1=1) end) and school_name=#{schoolName} GROUP BY exam_batches")
    List<BksExamAvgAchievementVo> getAvgScorelist(String year,String schoolName);
}
