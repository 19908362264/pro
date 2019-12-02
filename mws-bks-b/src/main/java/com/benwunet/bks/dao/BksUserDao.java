package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.dto.MajorCategory;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import com.benwunet.bks.model.dto.BksAttentionedSchoolsDTO;
import com.benwunet.bks.model.vo.MajorCategoryVo;

import java.util.List;

public interface BksUserDao extends BaseMapper<BksUser> {

    /**
     * 查询本科或专科专业类别接口
     * @author FC
     * @param collegesId
     * @return List
     * @date 2019/6/18 9:03
     */
    List<BksMajorCategory> getCategory(String collegesId);




    /**
     * 连表查询被关注学校的详细信息 接口
     * @author FC
     * @param schoolId
     * @return BksAttentionedSchoolsDTO
     * @date 2019/6/22 10:24
     */
    BksAttentionedSchoolsDTO getAttentionedSchoolDetails(String schoolId);


    /**
     * 查询给定学校最新年份在考生所在的省份的 分数线
     * @author FC
     * @param schoolId
     * @return BksAttentionedSchoolsDTO
     * @date 2019/7/2 9:31
     */
    BksAttentionedSchoolsDTO getSchoolScore(String schoolId, String provinceId);



    /**
     * 连表查询被关注专业的详细信息 接口
     * @author FC
     * @param majorCategoryId
     * @return BksAttentionedMajorsDTO
     * @date 2019/6/22 17:44
     */
    BksAttentionedMajorsDTO getAttentionedMajorDetails(String majorCategoryId);

    /**
     * 连表查询被关注专业的详细信息，统计开设该专业的学校数量
     * @author FC
     * @date 2019/6/22 18:00
     */
    Integer countUniversity(String mcId);

    List<MajorCategory> findMajorCategory(MajorCategoryVo entity);

    Integer findMajorCategoryCount(MajorCategory entity);

    Integer getSchoolMajorTotal(String type, String userId);
}
