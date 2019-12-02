package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksExamUploadDao;
import com.benwunet.bks.model.BksExamUpload;
import com.benwunet.bks.service.BksExamUploadService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author C
 * @since 2019-07-01
 */
@Service
public class BksExamUploadServiceImpl extends ServiceImpl<BksExamUploadDao, BksExamUpload> implements BksExamUploadService {

    @Override
    public List<BksExamUpload> getlist(String schoolName) {
        return baseMapper.getlist(schoolName) ;
    }

    @Override
    public List<BksExamUpload> getYearlist() {
        return baseMapper.getYearlist();
    }

    @Override
    public List<BksExamUpload> getYearlistBySchool(String schoolName) {

        return baseMapper.getYearlistBySchool(schoolName);
    }

    @Override
    public List<BksExamUpload> getExamListByYear(String schoolYear) {

        return baseMapper.getExamListByYear(schoolYear);
    }
}
