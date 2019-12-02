package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksSubjectCombDao;
import com.benwunet.bks.model.BksSubjectComb;
import com.benwunet.bks.model.dto.BksProvinceSubjectCombDTO;
import com.benwunet.bks.service.BksSubjectCombService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 学科的组合 服务实现类
 * </p>
 *
 * @author C
 * @since 2019-06-25
 */
@Service
public class BksSubjectCombServiceImpl extends ServiceImpl<BksSubjectCombDao, BksSubjectComb> implements BksSubjectCombService {
    @Resource
    BksSubjectCombDao subjectCombDao;

    /**
     * 获取最新的年份
     * @author FC
     * @return String
     * @date 2019/6/27 19:08
     */
    @Override
    public String getYear(String districtName,String schoolName) {
        return subjectCombDao.getYear(districtName,schoolName);
    }

    /**
     * 统计区县学科组合热度
     * @author FC
     * @param districtName
     * @param year
     * @return List
     * @date 2019/6/27 17:07
     */
    @Override
    public List<BksProvinceSubjectCombDTO> getDistrictSubjectCombs(String districtName,String year) {
        return subjectCombDao.getDistrictSubjectCombs(districtName,year);
    }

    /**
     * 统计全重庆市学科组合热度
     * @author FC
     * @param year
     * @return List
     * @date 2019/6/27 17:07
     */
    @Override
    public List<BksProvinceSubjectCombDTO> getProvinceSubjectCombs(String year) {
        return subjectCombDao.getProvinceSubjectCombs(year);
    }

    @Override
    public List<BksSubjectComb> getSchools(String schoolName, String districtName, String year) {
        return subjectCombDao.getSchools(schoolName,districtName,year);
    }
}
