package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.entity.dto.TeacherDTO;
import com.benwunet.bks.model.BksExamUpload;
import com.benwunet.bks.model.BksUser;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.vo.QueryVO;


/**
 * @author zfy
 * @date 2019/7/26
 */
public interface TeacherService extends IService<BksUser> {

    /**
     * 教师列表
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    PageResult getTeachers(QueryVO vo);

    /**
     * 新增教师
     *
     * @param dto 教师信息
     * @return ResponseResult
     */
    ResponseResult addTeacher(TeacherDTO dto);

    /**
     * 修改教师
     *
     * @param dto 教师信息
     * @return ResponseResult
     */
    ResponseResult updateTeacher(TeacherDTO dto);

    /**
     * 删除教师（可批量删除）
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    ResponseResult delTeacher(QueryVO vo);


    /**
     * 获取教师详情
     *
     * @param id ID
     * @return ResponseResult
     */
    ResponseResult getTeacher(Integer id);

    /**
     * 获取分段人数（学生分段）
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    ResponseResult getScoreRange(QueryVO vo);

    /**
     * 获取首页数据
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    ResponseResult getHomePage(QueryVO vo);

    /**
     * 成绩管理列表
     *
     * @param vo 请求参数
     * @return PageResult
     */
    PageResult getBatches(QueryVO vo);

    /**
     * 修改批次
     *
     * @param vo 请求参数
     * @return PageResult
     */
    ResponseResult updateBatch(BksExamUpload vo);

    /**
     * 作废批次
     *
     * @param vo 请求参数
     * @return PageResult
     */
    ResponseResult delBatch(BksExamUpload vo);

    ResponseResult getMobile(String mobile);
}
