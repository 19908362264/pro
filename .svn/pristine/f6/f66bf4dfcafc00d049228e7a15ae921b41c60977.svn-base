package com.benwunet.bks.controller.professor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.feign.UserClient;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.vo.QueryVO;
import com.benwunet.bks.service.BksProfessorService;

import com.benwunet.bks.service.BksProfessorSetTimeService;
import com.benwunet.bks.service.BksProfessorStandardService;
import com.benwunet.bks.service.BksQuestionComplaintService;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zfy
 * @date 2019/8/29
 */
@RestController
@Api(value = "备考生专家端", tags = "备考生专家端")
public class ProfessorAController {

    @Autowired
    private BksProfessorService service;

    @Autowired
    private BksQuestionComplaintService complaintService;

    @Autowired
    private BksProfessorStandardService standardService;

    @Autowired
    private BksProfessorSetTimeService setTimeService;

    @Autowired
    private UserClient userClient;


    /**
     * 工作台
     *
     * @return ResponseResult
     */
    @PostMapping("/works")
    @ApiOperation(value = "工作台", notes = "{}")
    public ResponseResult getWorks(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getWorks(vo);
    }

    /**
     * 我的预约
     * 用户对该专家的预约，预约状态，每日预约情况
     * @param vo 请求参数
     * @return PageResult
     */
    @PostMapping("/appointments")
    @ApiOperation(value = "专家的预约", notes = "我的预约")
    public PageResult getAppointments(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getAppointments(vo);
    }

    /**
     * 专家解答
     * 提问时选择专家解答和指定专家解答的问题
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @PostMapping("/experts/questions")
    @ApiOperation(value = "专家解答", notes = "专家解答")
    public PageResult getQuestions(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getQuestions(vo);
    }

    /**
     * 反馈意见、投诉
     * @return ResponseResult
     */
    @PostMapping("/bks-anon/complaint")
    @ApiOperation(value = "反馈意见、投诉", notes = "反馈意见、投诉")
    public ResponseResult addComplaint(@RequestBody BksQuestionComplaint dto) {
        try {
            complaintService.save(dto);
            return ResponseResult.app(0, 0, "OK", "");
        } catch (Exception e) {
            return ResponseResult.app(1, 1, e.getMessage(), "");
        }
    }


    /**
     * 设置服务费用
     * @return ResponseResult
     */
    @PutMapping("/addCostStandard")
    @ApiOperation(value = "設置服务费用", notes = "設置服务费用")
    public ResponseResult addCostStandard(@RequestBody List<BksProfessorStandard> list) {
        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.app(1, 1, "请设置计费标准", "");
        }
        for (BksProfessorStandard standard : list) {
            QueryWrapper<BksProfessorStandard> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("professor_id", standard.getProfessorId());
            queryWrapper.eq("type", standard.getType());
            int count = standardService.count(queryWrapper);
            if (count < 1) {
                BksProfessorStandard bksProfessorStandard = new BksProfessorStandard();
                bksProfessorStandard.setProfessorId(standard.getProfessorId());
                bksProfessorStandard.setType(standard.getType());
                bksProfessorStandard.setTypeCost(standard.getTypeCost());
                standardService.saveOrUpdate(bksProfessorStandard);
            } else {
                BksProfessorStandard one = standardService.getOne(queryWrapper);
                one.setTypeCost(standard.getTypeCost());
                one.setType(standard.getType());
                standardService.saveOrUpdate(one);
            }
        }
        return ResponseResult.app(0, 0, "设置成功", "");
    }


    /**
     * 设置专家时间
     *
     * @return ResponseResult
     */
    @PutMapping("/professor/time")
    @ApiOperation(value = "专家设置服务时间", notes = "专家设置服务时间")
    public ResponseResult setProfessorTime(@RequestBody List<BksProfessorSetTime> list) {

        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.app(1, 1, "请设置预约时间", "");
        }
        for (BksProfessorSetTime bksProfessorSetTime : list) {
            QueryWrapper<BksProfessorSetTime> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("professor_id", bksProfessorSetTime.getProfessorId());
            queryWrapper.eq("date", bksProfessorSetTime.getDate());
            queryWrapper.eq("type", bksProfessorSetTime.getType());
            queryWrapper.eq("time_type", bksProfessorSetTime.getTimeType());
            int count = setTimeService.count(queryWrapper);
            if (count<1){
                BksProfessorSetTime professorSetTime =new BksProfessorSetTime();
                professorSetTime.setProfessorId(bksProfessorSetTime.getProfessorId());
                professorSetTime.setDate(bksProfessorSetTime.getDate());
                professorSetTime.setType(bksProfessorSetTime.getType());
                professorSetTime.setTimeType(bksProfessorSetTime.getTimeType());
                setTimeService.saveOrUpdate(professorSetTime);
            }else {
                BksProfessorSetTime one = setTimeService.getOne(queryWrapper);
               one.setDate(bksProfessorSetTime.getDate());
               one.setTimeType(bksProfessorSetTime.getType());
               one.setTimeType(bksProfessorSetTime.getTimeType());
               setTimeService.saveOrUpdate(one);
            }

        }

        return ResponseResult.app(0, 0, "设置成功", "");

    }

    /**
     * 根据userID获取用户信息
     *
     * @param userId 用户id
     * @return pubSysUser
     */
    @GetMapping("/bks-anon/user-info")
    @ApiOperation(value = "根据userID获取用户信息", notes = "根据userID获取用户信息")
    public ResponseResult getUserInfo(String userId) {
        try {
            PubSysUser pubSysUser = userClient.getUserByUserCode(userId);
            return ResponseResult.app(0, 0, "OK", pubSysUser);
        } catch (Exception e) {
            return ResponseResult.app(1, 1, e.getMessage(), "");
        }

    }

//    @PostMapping("/prefessor/time")
//    @ApiOperation(value = "查询专家服务时间", notes = "查询专家服务时间")
//    public ResponseResult professorTime(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo){
//
//        List<String> list = DateUtils.complementTime(vo.getBeginTime(), vo.getEndTime());
//        QueryWrapper<BksProfessorSetTime> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("date", list);
//        queryWrapper.eq("status", 0);
//        queryWrapper.eq("professor_id", vo.getUserId());
//        queryWrapper.groupBy("date", "status");
//        //查出可以预约的日期
//        List<BksProfessorSetTime> setTimeList = setTimeService.list(queryWrapper);
//        return null;
//    }

}