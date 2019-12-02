package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksSchoolScoreDao;
import com.benwunet.bks.model.BksSchoolScore;
import com.benwunet.bks.model.dto.BksSchoolScoreDTO;
import com.benwunet.bks.service.BksSchoolScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BksSchoolScoreServiceImpl extends ServiceImpl<BksSchoolScoreDao, BksSchoolScore> implements BksSchoolScoreService {
    @Autowired
    private BksSchoolScoreDao bksSchoolScoreDao;

    @Override
    public List<BksSchoolScoreDTO> getVoluntaryForecasting(String cityId, String schoolProvinceId, String type, String score, Integer page) {
        return bksSchoolScoreDao.getVoluntaryForecasting(cityId, schoolProvinceId, type, score, page);

    }

    @Override
    public Integer getVoluntaryForecastingCount(String cityId, String schoolProvinceId, String type, String score) {
        return bksSchoolScoreDao.getVoluntaryForecastingCount(cityId, schoolProvinceId, type, score);
    }

    @Override
    public List<BksSchoolScoreDTO> getBorderline(String schoolId, Integer page) {
        return bksSchoolScoreDao.getBorderline(schoolId,page);
    }

    @Override
    public Integer getBorderlineCount(String schoolId) {
        return bksSchoolScoreDao.getBorderlineCount(schoolId);

    }
}
