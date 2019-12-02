package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksUserSchoolMajor;
import com.benwunet.bks.model.dto.BksSchoolScoreDTO;

import java.util.List;

public interface BksUserSchoolMajorService extends IService<BksUserSchoolMajor> {
    Integer getMajorConcern(String userId, String majorCategoryId);

    Integer getSchoolConcern(String userId, String schoolId);


    /**
     * 查询关注 接口
     * @author FC
     * @param type
     * @param userId
     * @param pag
     * @return list
     * @date 2019/6/18 16:12
     */
    List<BksUserSchoolMajor> queryAttention(String type, String userId, Integer pag);


}
