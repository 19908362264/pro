package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.BksSchool;
import com.benwunet.bks.model.dto.BksSchoolDTO;

import java.util.List;

public interface BksSchoolService extends IService<BksSchool> {

    /**
     * 查询高校（按照人气值排名）
     * @return list
     */
    List<BksSchool> listSchoolByPopularity();


    /**
     * 查询高校专业（按照人气值排名）
     * @return list
     */
    List<BksMajorCategory> listMajorByRanking();


    List<BksSchoolDTO> getSchoolList(String schoolName, String provinceId, String levelId, String kindId, Integer page, String tx);

    List<BksSchoolDTO> getSchoolMajorList(String schoolId);

    List<BksSchoolDTO> getSchoolMajorLimitList(String schoolId);

    List<BksSchoolDTO> getMajorSchoolList(String majorId, Integer page);

    int getSchoolListCount(String schoolName, String provinceId, String levelId, String kindId, String tx);

    BksSchoolDTO getOneSchool(String school_id);

    List<BksSchoolDTO> getSchoolHeat(Integer page,Integer size);

    int getSchoolHeatcount();


//    List<BksSchoolDTO> getSchoolAndMajorList(String schoolId);
//
//    List<BksSchoolDTO> getSchoolAndMajorLimitList(String schoolId);
}
