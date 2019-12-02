package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksSubjectComb;
import com.benwunet.bks.model.dto.BksProvinceSubjectCombDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学科的组合 Mapper 接口
 * </p>
 *
 * @author C
 * @since 2019-06-25
 */
public interface BksSubjectCombDao extends BaseMapper<BksSubjectComb> {

    /**
     * 获取最新的年份
     * @author FC
     * @return String
     * @date 2019/6/27 19:08
     */
    String getYear(@Param("districtName") String districtName,@Param("schoolName") String schoolName);

    /**
     * 统计区县学科组合热度
     * @author FC
     * @param districtName
     * @param year
     * @return List
     * @date 2019/6/27 17:07
     */
    List<BksProvinceSubjectCombDTO> getDistrictSubjectCombs(@Param("districtName") String districtName,@Param("year") String year);

    /**
     * 统计全重庆市学科组合热度
     * @author FC
     * @param year
     * @return List
     * @date 2019/6/27 17:07
     */
    List<BksProvinceSubjectCombDTO> getProvinceSubjectCombs(String year);



    List<BksSubjectComb> getSchools(@Param("schoolName") String schoolName,@Param("districtName") String districtName,@Param("year") String year);

}
