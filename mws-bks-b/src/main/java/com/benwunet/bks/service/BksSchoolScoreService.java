package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksSchoolScore;
import com.benwunet.bks.model.dto.BksSchoolScoreDTO;

import java.util.List;

public interface BksSchoolScoreService extends IService<BksSchoolScore> {
    List<BksSchoolScoreDTO> getVoluntaryForecasting(String cityId, String schoolProvinceId, String type, String score, Integer page);

    Integer getVoluntaryForecastingCount(String cityId, String schoolProvinceId, String type, String score);

    List<BksSchoolScoreDTO> getBorderline(String schoolId, Integer page);

    Integer getBorderlineCount(String schoolId);
}
