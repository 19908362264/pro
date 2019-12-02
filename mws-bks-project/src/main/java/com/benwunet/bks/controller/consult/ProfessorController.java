package com.benwunet.bks.controller.consult;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.dto.BksProfessorDTO;
import com.benwunet.bks.model.vo.*;

import com.benwunet.bks.service.*;
import com.benwunet.bks.util.DateUtils;
import com.benwunet.bks.util.OrderCodeFactory;
import com.benwunet.mws.model.result.BaseResponse;
import com.benwunet.mws.model.result.ResponseResult;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/21 10:09
 */

@RestController
@RequestMapping
@Api(value = "备考生小程序专家咨询一对一", tags = "备考生小程序专家咨询一对一")
public class ProfessorController {
    @Autowired
    private BksProfessorService professorService;

    @Autowired
    private BksProvinceService provinceService;

    @Autowired
    private BksProfessorServeService professorServeService;

    @Autowired
    private BksProfessorAreaService areaService;

    @Autowired
    private BksProfessorAppointmentService professorAppointmentService;

    @Autowired
    private BksQuestionAnswerService questionAnswerService;

    @Autowired
    private BksQuestionService questionService;

    @Autowired
    private BksProfessorTimeService professorTimeService;

    @Autowired
    private BksQuestionOrderService orderService;

    @Autowired
    private BksProfessorSetTimeService setTimeService;

    @Autowired
    private BksProfessorStandardService standardService;

    @Autowired
    private BksUserSchoolMajorService majorService;


    @ApiOperation(value = "小程序专家一对一列表", notes = "小程序专家一对一列表")
    @PostMapping("/bks-anon/professor")
    public ResponseResult expertConsultation(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody ExpertConsultationVo expertConsultationVo) {
        if (expertConsultationVo.getPage() == 0 || "".equals(expertConsultationVo.getPage())) {
            expertConsultationVo.setPage(1);
        }
        expertConsultationVo.setPage((expertConsultationVo.getPage() - 1) * expertConsultationVo.getSize());

        List<BksProfessorDTO> list = professorService.getProfessorsList(expertConsultationVo);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.app(1, 1, "数据不存在", "");
        }
        Integer total = list.size();

        if (total < 0) {
            return ResponseResult.app(1, 1, "数据不存在", "");
        }

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (BksProfessorDTO bksProfessorDTO : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", bksProfessorDTO.getUserId());
            map.put("name", bksProfessorDTO.getProfessorName());
            map.put("headImg", bksProfessorDTO.getHeadImg());
            map.put("type", bksProfessorDTO.getProfessorType());
            map.put("filed", bksProfessorDTO.getSkilledField());
            map.put("introduction", bksProfessorDTO.getIntroduction());
            map.put("status", bksProfessorDTO.getStatus());
            QueryWrapper<BksQuestionAnswer> answerQueryWrapper = new QueryWrapper<>();
            answerQueryWrapper.eq("user_id", bksProfessorDTO.getUserId());
            answerQueryWrapper.eq("accept_type", 1);
            int count = questionAnswerService.count(answerQueryWrapper);
            map.put("peopleCount", count);
            mapList.add(map);
        }

        return ResponseResult.page(0, "", Long.valueOf(total), mapList);

    }


    /**
     * 我关注的专家数量
     *
     * @param userId
     * @param professorId
     * @return
     */
    private Integer getMyconcernProfessor(String userId, String professorId) {
        QueryWrapper<BksUserSchoolMajor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("professor_id", professorId);
        int count = majorService.count(queryWrapper);
        return count;
    }


    @GetMapping("/bks-anon/provinces")
    @ApiOperation(value = "备考生小程序专家服务地域", notes = "备考生小程序专家服务地域")
    public ResponseResult provinceList() {
        QueryWrapper<BksProvince> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        List<BksProvince> list = provinceService.list(queryWrapper);
        List<Map<String, Object>> collect = list.stream().map(x -> {
            Map<String, Object> map = new HashMap<>();
            map.put("provinceId", x.getProvinceId());
            map.put("provinceName", x.getProvinceName());
            return map;
        }).collect(Collectors.toList());
        return ResponseResult.app(0, 0, "", collect);
    }


    @GetMapping("/bks-anon/professor/{professorId}")
    @ApiOperation(value = "备考生小程序专家详情", notes = "备考生小程序专家详情")
    public ResponseResult professorInfo(@ApiParam(value = "用户id", required = true) String userId,
                                        @ApiParam(value = "专家id", required = true) @PathVariable String professorId) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<BksProfessor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", professorId);
        BksProfessor one = professorService.getOne(queryWrapper);
        if (one == null || one.equals("")) {
            return ResponseResult.app(0, 0, "专家不存在", "");
        }
        map.put("id", one.getUserId());
        map.put("name", one.getProfessorName());
        map.put("headImg", one.getHeadImg());
        map.put("filed", one.getSkilledField());
        map.put("introduction", one.getIntroduction());
        map.put("type", one.getProfessorType());
        map.put("year", one.getGuideYear());
        map.put("status", one.getStatus());
        //指定专家的费用
        QueryWrapper<BksProfessorStandard> standardQueryWrapper = new QueryWrapper<>();
        standardQueryWrapper.eq("professor_id", professorId);
        standardQueryWrapper.eq("type", 4);
        BksProfessorStandard standard = standardService.getOne(standardQueryWrapper);
        map.put("typeCost", standard == null ? "" : standard.getTypeCost());
        QueryWrapper<BksProfessorServe> serviceQueryWrapper = new QueryWrapper<>();
        serviceQueryWrapper.eq("user_id", professorId);
        List<BksProfessorServe> list = professorServeService.list(serviceQueryWrapper);

        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.app(0, 0, "服务区域数据不存在", "");
        }
        List<Integer> collect = list.stream().map(BksProfessorServe::getServiceId).collect(Collectors.toList());
        map.put("service", collect);
        QueryWrapper<BksProfessorArea> areaQueryWrapper = new QueryWrapper<>();
        areaQueryWrapper.eq("user_id", professorId);

        List<BksProfessorArea> areaList = areaService.list(areaQueryWrapper);
        if (CollectionUtils.isEmpty(areaList)) {
            return ResponseResult.app(0, 0, "数据有误", "");
        }
        List<String> areas = areaList.stream().map(BksProfessorArea::getProvinceName).collect(Collectors.toList());
        map.put("area", areas);
        QueryWrapper<BksQuestionAnswer> answerQueryWrapper = new QueryWrapper<>();
        answerQueryWrapper.eq("user_id", professorId);
        answerQueryWrapper.eq("accept_type", 1);
        int count = questionAnswerService.count(answerQueryWrapper);
        map.put("peopleCount", count);

        Integer myConcernProfessor = getMyconcernProfessor(userId, professorId);
        map.put("myconcern", myConcernProfessor > 0 ? "已关注" : "未关注");
        return ResponseResult.app(0, 0, "", map);

    }


    @PostMapping("/professorAppointment")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @ApiOperation(value = "备考生专家预约", notes = "备考生专家预约")
    public ResponseResult professorAppointment(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true)
                                               @RequestBody BksProfessorAppointmentVO vo) {

        for (BksProfessorTimeVO professorTime : vo.getList()) {
            QueryWrapper<BksProfessorTime> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("professor_id", vo.getProfessorId());
            queryWrapper.eq("date", professorTime.getDate());
            queryWrapper.eq("type", professorTime.getType());
            queryWrapper.eq("time_type", professorTime.getTimeType());
            int count = professorTimeService.count(queryWrapper);
            if (count > 0) {
                return ResponseResult.app(1, 1, "该时间段已经预约" + professorTime.getDate() + "-----" + professorTime.getTimeType(), "");
            }
        }
        try {
            //预约专家
            BksProfessorAppointment bksProfessorAppointment = new BksProfessorAppointment();
            bksProfessorAppointment.setProfessorId(vo.getProfessorId());
            bksProfessorAppointment.setProfessorName(vo.getProfessorName());
            bksProfessorAppointment.setUserId(vo.getUserId());
            bksProfessorAppointment.setUserName(vo.getUserName());
            bksProfessorAppointment.setDescription(vo.getDescription());
            bksProfessorAppointment.setServiceType(vo.getServiceType());
            bksProfessorAppointment.setServiceWay(vo.getServiceWay());
            //计算预约费用
            bksProfessorAppointment.setCost(vo.getCost());
            bksProfessorAppointment.setStatus(1);
            boolean b = professorAppointmentService.saveOrUpdate(bksProfessorAppointment);
            if (!b) {
                throw new IllegalArgumentException("预约失败");
            }

            QueryWrapper<BksProfessorAppointment> query = new QueryWrapper<>();
            query.eq("user_id", vo.getUserId());
            query.orderByDesc("gmt_create");
            BksProfessorAppointment professorAppointment = professorAppointmentService.list(query).get(0);
            for (BksProfessorTimeVO bksProfessorTimeVO : vo.getList()) {
                //保存预约专家的时间
                BksProfessorTime bksProfessorTime = new BksProfessorTime();
                bksProfessorTime.setAppointmentId(professorAppointment.getId());
                bksProfessorTime.setProfessorId(vo.getProfessorId());
                bksProfessorTime.setDate(bksProfessorTimeVO.getDate());
                bksProfessorTime.setType(bksProfessorTimeVO.getType());
                bksProfessorTime.setTimeType(bksProfessorTimeVO.getTimeType());
                professorTimeService.saveOrUpdate(bksProfessorTime);

                //将专家设置的时间设置为已预约
                QueryWrapper<BksProfessorSetTime> setTimeQueryWrapper = new QueryWrapper<>();
                setTimeQueryWrapper.eq("date", bksProfessorTimeVO.getDate());
                setTimeQueryWrapper.eq("type", bksProfessorTimeVO.getType());
                setTimeQueryWrapper.eq("time_type", bksProfessorTimeVO.getTimeType());
                BksProfessorSetTime one = setTimeService.getOne(setTimeQueryWrapper);
                one.setStatus(1);
                setTimeService.saveOrUpdate(one);

            }

            //预约完成生成订单
            BksQuestionOrder order = new BksQuestionOrder();
            order.setOrderId(OrderCodeFactory.getOrderCode(Long.parseLong(professorAppointment.getUserId())));
            order.setAppointmentId(String.valueOf(professorAppointment.getId()));
            order.setActualAmount(professorAppointment.getCost());
            order.setUserId(professorAppointment.getUserId());
            order.setStatus(0);
            order.setType(2);
            orderService.saveOrUpdate(order);

        } catch (Exception e) {
            return ResponseResult.app(1, 1, e.getMessage(), "");
        }


        return ResponseResult.app(0, 0, "预约成功", "");
    }


    @GetMapping("/bks-anon/getTimeList/{userId}")
    @ApiOperation(value = "备考生专家可以预约的时间", notes = "备考生专家可以预约的时间")
    public ResponseResult getTimeList(@ApiParam(value = "专家id", required = true) @PathVariable("userId") String userId,
                                      @ApiParam(value = "日期", required = true) @RequestParam String date,
                                      @ApiParam(value = "1,上午 2,下午", required = true) Integer type) {
        QueryWrapper<BksProfessorSetTime> timeQueryWrapper = new QueryWrapper<>();
        timeQueryWrapper.eq("professor_id", userId);
        timeQueryWrapper.eq("date", date);
        timeQueryWrapper.eq("type", type);
        // timeQueryWrapper.eq("status", 0);
        List<BksProfessorSetTime> timeList = setTimeService.list(timeQueryWrapper);
        // List<Integer> collect = timeList.stream().map(x -> x.getTimeType()).collect(Collectors.toList());

        return ResponseResult.app(0, 0, "", timeList);
    }


    /**
     * @param userId
     * @return List<BksProfessorServe>
     */
    private List<BksProfessorServe> getServeList(String userId) {
        QueryWrapper<BksProfessorServe> serveQueryWrapper = new QueryWrapper<>();
        serveQueryWrapper.eq("professor_id", userId);
        List<BksProfessorServe> serverList = professorServeService.list(serveQueryWrapper);
        return serverList;
    }


    @GetMapping("/bks-anon/getCostList")
    @ApiOperation(value = "备考生专家服务类型的收费标准", notes = "备考生专家服务类型的收费标准")
    public ResponseResult getOne(
            @ApiParam(value = "专家id", required = true) String professorId) {
        QueryWrapper<BksProfessorStandard> standardQueryWrapper = new QueryWrapper<>();
        standardQueryWrapper.eq("professor_id", professorId);
        List<BksProfessorStandard> list = standardService.list(standardQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseResult.app(1, 1, "暂未设置费用", "");
        }
        return ResponseResult.app(0, 0, "", list);
    }


    @GetMapping("/bks-anon/questionAnswer/{id}")
    @ApiOperation(value = "备考生专家解答", notes = "备考生专家解答")
    public ResponseResult professorAnswer(
            @ApiParam(value = "专家id", required = true) @PathVariable String id,
            @ApiParam(value = "当前页", required = true) Integer page,
            @ApiParam(value = "每页数量", required = true) Integer size) {
        QueryWrapper<BksQuestionAnswer> answerQueryWrapper = new QueryWrapper<>();
        answerQueryWrapper.eq("user_id", id);
        answerQueryWrapper.orderByDesc("gmt_create");
        int total = questionAnswerService.count(answerQueryWrapper);
        if (total <= 0) {
            return ResponseResult.app(1, 1, "数据为空", "");
        }
        Page<BksQuestionAnswer> answerPage = new Page<>(page, size, total);
        IPage<BksQuestionAnswer> questionAnswerIPage = questionAnswerService.page(answerPage, answerQueryWrapper);
        List<Map<String, Object>> collect = questionAnswerIPage.getRecords().stream().map(x -> {
            Map<String, Object> map = new HashMap<>();
            QueryWrapper<BksQuestion> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", x.getQuestionId());
            BksQuestion one = questionService.getOne(queryWrapper);
            map.put("title", one.getTitle());
            map.put("status", one.getStatus());
            map.put("time", x.getGmtCreate());
            map.put("money", one.getMoney());
            QueryWrapper<BksQuestionAnswer> query = new QueryWrapper<>();
            query.eq("question_id", x.getQuestionId());
            int count = questionAnswerService.count(query);
            map.put("id", x.getQuestionId());
            map.put("total", count);
            return map;
        }).collect(Collectors.toList());
        return ResponseResult.page(0, "", Long.valueOf(total), collect);
    }


    /**
     * @param vo
     * @return
     */
    @PostMapping("/bks-anon/professorAppointTime")
    @ApiOperation(value = "专家详情可预约时间", notes = "专家详情可预约时间")
    public BaseResponse professorAppointTime(@RequestBody QueryVO vo) {
        List<String> list = DateUtils.complementTime(vo.getBeginTime(), vo.getEndTime());
        QueryWrapper<BksProfessorSetTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("date", list);
        queryWrapper.eq("status", 0);
        queryWrapper.eq("professor_id", vo.getUserId());
        queryWrapper.groupBy("date", "status");
        //查出可以预约的日期
        List<BksProfessorSetTime> setTimeList = setTimeService.list(queryWrapper);

        List<WorkVO> workList = list.stream().map(date -> WorkVO.of().setDate(date))
                .peek(work -> {
                    BksProfessorSetTime professor = setTimeList.stream().filter(p -> p.getDate().equals(work.getDate()))

                            .findFirst().orElse(BksProfessorSetTime.of().setStatus(1));

                    work.setStatus(professor.getStatus());

                }).collect(Collectors.toList());

        return new BaseResponse(0, "", 0, workList);

    }

    @GetMapping("/bks-anon/appointTime")
    @ApiOperation(value = "专家时间详情", notes = "专家时间详情")
    public BaseResponse appointTime(@RequestBody QueryVO vo) {
        List<String> list = DateUtils.complementTime(vo.getBeginTime(), vo.getEndTime());
        QueryWrapper<BksProfessorSetTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("date", list);
        queryWrapper.eq("status", 0);
        queryWrapper.eq("professor_id", vo.getUserId());
        queryWrapper.groupBy("date", "status");
        //查出可以预约的日期
        List<BksProfessorSetTime> setTimeList = setTimeService.list(queryWrapper);
         
        List<WorkVO> workList = list.stream().map(date -> WorkVO.of().setDate(date))
                .peek(work -> {
                    BksProfessorSetTime professor = setTimeList.stream().filter(p -> p.getDate().equals(work.getDate()))

                            .findFirst().orElse(BksProfessorSetTime.of().setStatus(1));

                    work.setStatus(professor.getStatus());

                }).collect(Collectors.toList());

        return new BaseResponse(0, "", 0, workList);

    }


}
