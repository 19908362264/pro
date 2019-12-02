package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksSchoolDao;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.BksSchool;
import com.benwunet.bks.model.dto.BksSchoolDTO;
import com.benwunet.bks.model.vo.BksSchoolVO;
import com.benwunet.bks.service.BksSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BksSchoolServiceImpl extends ServiceImpl<BksSchoolDao, BksSchool> implements BksSchoolService {

    @Resource
    BksSchoolDao bksSchoolDao;

    /**
     * 查询高校（按照人气值排名）
     */
    @Override
    public List<BksSchool> listSchoolByPopularity() {
        return bksSchoolDao.listSchoolByPopularity();
    }


    /**
     * 查询高校专业（按照人气值排名）
     */
    @Override
    public List<BksMajorCategory> listMajorByRanking(){
        return bksSchoolDao.listMajorByRanking();
    }

    @Override
    public List<BksSchoolDTO> getSchoolList(String schoolName, String provinceId, String levelId, String kindId, Integer page,String tx) {

       return bksSchoolDao.getSchoolList(schoolName,provinceId,levelId,kindId,page,tx);
    }

    @Override
    public List<BksSchoolDTO> getSchoolMajorList(String schoolId) {

        return bksSchoolDao.getSchoolMajorList(schoolId);
    }

    @Override
    public List<BksSchoolDTO> getSchoolMajorLimitList(String schoolId) {
        return bksSchoolDao.getSchoolMajorLimitList(schoolId);
    }

    @Override
    public List<BksSchoolDTO> getMajorSchoolList(String majorId,Integer page) {
        return bksSchoolDao.getMajorSchoolList(majorId, page);

    }

    @Override
    public int getSchoolListCount(String schoolName, String provinceId, String levelId, String kindId,String tx) {
        return bksSchoolDao.getSchoolListCount(schoolName,provinceId,levelId,kindId,tx);
    }

    @Override
    public BksSchoolDTO getOneSchool(String school_id) {

        return bksSchoolDao.getOneSchool(school_id);
    }

    @Override
    public List<BksSchoolDTO> getSchoolHeat(Integer page) {
        return  bksSchoolDao.getSchoolHeat(page);
    }

    @Override
    public IPage<BksSchoolVO> matchUniversity(String provinceId, Double score, Page<BksSchoolVO> pageQuery) {
        return bksSchoolDao.matchUniversity(pageQuery,provinceId,score);
    }


//    @Override
//    public List<BksSchoolDTO> getSchoolAndMajorList(String schoolId) {
//        return bksSchoolDao.getSchoolAndMajorList(schoolId);
//
//    }
//
//    @Override
//    public List<BksSchoolDTO> getSchoolAndMajorLimitList(String schoolId) {
//
//        return bksSchoolDao.getSchoolAndMajorLimitList(schoolId);
//
//    }


}
