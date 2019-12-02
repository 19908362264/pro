package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksSubjectComb;
import com.benwunet.bks.model.dto.BksProvinceSubjectCombDTO;

import java.util.List;

/**
 * <p>
 * 学科的组合 服务类
 * </p>
 *
 * @author C
 * @since 2019-06-25
 */
public interface BksSubjectCombService extends IService<BksSubjectComb> {

    /**
     * 获取最新的年份
     * @author FC
     * @return String
     * @date 2019/6/27 19:08
     */
    String getYear(String districtName, String schoolName);

    /**
     * 统计区县学科组合热度
     * @author FC
     * @param districtName
     * @param year
     * @return List
     * @date 2019/6/27 17:07
     */
    List<BksProvinceSubjectCombDTO> getDistrictSubjectCombs(String districtName, String year);

    /**
     * 统计全重庆市学科组合热度
     * @author FC
     * @param year
     * @return List
     * @date 2019/6/27 17:07
     */
    List<BksProvinceSubjectCombDTO> getProvinceSubjectCombs(String year);


    List<BksSubjectComb> getSchools(String schoolName, String districtName, String year);

}
