package com.benwunet.bks.controller;

import com.benwunet.bks.entity.dto.TeacherDTO;
import com.benwunet.bks.model.BksExamUpload;
import com.benwunet.bks.service.TeacherService;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.vo.QueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author zfy
 * @date 2019/7/26
 */
@RestController
@Api(value = "教师管理接口", tags = "教师管理接口")
public class TeacherController {

    @Autowired
    private TeacherService service;

    /**
     * 教师列表
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @PostMapping("/teachers")
    @ApiOperation(value = "教师列表", notes = "教师列表")
    public PageResult getTeachers(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getTeachers(vo);
    }

    /**
     * 新增教师
     *
     * @param dto 教师信息
     * @return ResponseResult
     */
    @PostMapping("/teacher")
    @ApiOperation(value = "新增教师", notes = "新增教师")
    public ResponseResult addTeacher(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody TeacherDTO dto) {
        return service.addTeacher(dto);
    }

    /**
     * 获取教师详情
     *
     * @param id ID
     * @return ResponseResult
     */
    @GetMapping("/teacher/{id}")
    @ApiOperation(value = "获取教师详情", notes = "获取教师详情")
    public ResponseResult getTeacher(@PathVariable("id") @ApiParam(name = "主键ID", value = "id") Integer id) {
        return service.getTeacher(id);
    }

    /**
     * 修改教师
     *
     * @param dto 教师id
     * @return ResponseResult
     */
    @PutMapping("/teacher")
    @ApiOperation(value = "修改教师信息", notes = "修改教师信息")
    public ResponseResult updateTeacher(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody TeacherDTO dto) {
        return service.updateTeacher(dto);
    }

    /**
     * 删除教师（可批量删除）
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @DeleteMapping("/teacher")
    @ApiOperation(value = "删除教师（可批量删除）", notes = "删除教师（可批量删除）")
    public ResponseResult delTeacher(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.delTeacher(vo);
    }

    /**
     * 获取分段人数（学生分布）
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @PostMapping("/scoreRange")
    @ApiOperation(value = "学生分布", notes = "学生分布")
    public ResponseResult getScoreRange(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getScoreRange(vo);
    }

    /**
     * 获取首页数据
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @PostMapping("/home")
    @ApiOperation(value = "首页数据", notes = "首页数据")
    public ResponseResult getHomePage(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getHomePage(vo);
    }

    /**
     * 成绩管理列表
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @PostMapping("/batches")
    @ApiOperation(value = "成绩管理列表", notes = "成绩管理列表")
    public PageResult getBatches(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getBatches(vo);
    }

    /**
     * 修改批次
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @PutMapping("/batch")
    @ApiOperation(value = "修改批次", notes = "修改批次")
    public ResponseResult updateBatch(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody BksExamUpload vo) {
        return service.updateBatch(vo);
    }

    /**
     * 作废批次
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @DeleteMapping("/batch")
    @ApiOperation(value = "作废批次", notes = "作废批次")
    public ResponseResult delBatch(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody BksExamUpload vo) {
        return service.delBatch(vo);
    }

    @GetMapping("/mobile")
    @ApiOperation(value = "验证手机号", notes = "验证手机号")
    public ResponseResult getMobile(String mobile) {
        return service.getMobile(mobile);
    }

}
