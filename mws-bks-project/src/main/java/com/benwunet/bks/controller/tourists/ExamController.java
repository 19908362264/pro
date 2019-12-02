package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import com.benwunet.bks.model.dto.BksExamDTO;
import com.benwunet.bks.model.dto.BksSchoolDTO;
import com.benwunet.bks.model.queryVO.ScoreQueryVO;
import com.benwunet.bks.model.vo.BksExamRankingVO;
import com.benwunet.bks.model.vo.BksScoreAnalysisVO;
import com.benwunet.bks.service.*;
import com.benwunet.mws.model.result.RequestResult;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/22 14:25
 */
@RestController
@RequestMapping
@Validated
@Api(value = "考试相关", tags = "考试相关")
public class ExamController {

    @Autowired
    private BksUserExamService bksUserExamService;

    @Autowired
    private BksStudentTestscoreService testscoreService;

    @Autowired
    private BksSchoolService bksSchoolService;

    @Autowired
    private BksMajorCategoryService bksMajorCategoryService;

    @Autowired
    private BksExamAvgAchievementService bksExamAvgAchievementService;

    @Autowired
    private BksExamRankingService bksExamRankingService;

    @Autowired
    private BksUserService userService;


    public String getSdudentcode(String userId) {
        QueryWrapper<BksUser> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        BksUser one = userService.getOne(query);
        if (one == null) {
            throw new IllegalArgumentException("该用户不存在");
        }
        if (one.getStudentCode() == null || "".equals(one.getStudentCode())) {
            throw new IllegalArgumentException("" +
                    "该用户未完善学籍信息");
        }
        return one.getStudentCode();


    }

    /**
     * 查询成绩
     *
     * @param userId
     * @param examId
     * @param courseId
     * @return
     */
    @GetMapping("/examlist")
    @ApiOperation(value = "成绩查询", notes = "成绩查询")
    public ResponseResult getExamList(
            @ApiParam(value = "用户编号", required = true) String userId,
            @ApiParam(value = "考试批次名称", required = false) String examId,
            @ApiParam(value = "科目id 1001:语文，1002：数学，1003：英语，1004：生物，1005地理，1006：政治，1007:化学，1008：物理，1009：历史", required = false) String courseId) {
        String sdudentcode = getSdudentcode(userId);
        List<Map<String, Object>> list = new ArrayList<>();
       /* QueryWrapper<BksStudentTestscore> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("student_id",sdudentcode);
        queryWrapper.orderByDesc("gmt_create");
        BksStudentTestscore bksStudentTestscore = testscoreService.list(queryWrapper).get(0);
        String subjectComb = bksStudentTestscore.getSubjectComb();*/
        List<BksExamDTO> examList = testscoreService.getExamList(sdudentcode, examId);
        for (BksExamDTO bksExamDTO : examList) {
            Map<String, Object> dataMap = new HashMap<>();
            if (courseId == null || courseId.equals("")) {
                dataMap.put("examName", bksExamDTO.getExamName());
                dataMap.put("scoreYuwen", bksExamDTO.getSubjectYuwen() == null ? "" : bksExamDTO.getSubjectYuwen());
                dataMap.put("scoreShuxue", bksExamDTO.getSubjectShuxue() == null ? "" : bksExamDTO.getSubjectShuxue());
                dataMap.put("scoreYingyu", bksExamDTO.getSubjectYingyu() == null ? "" : bksExamDTO.getSubjectYingyu());
                dataMap.put("scoreShengwu", bksExamDTO.getSubjectShengwu() == null ? "" : bksExamDTO.getSubjectShengwu());
                dataMap.put("scoreDili", bksExamDTO.getSubjectDili() == null ? "" : bksExamDTO.getSubjectDili());
                dataMap.put("scoreZhengzhi", bksExamDTO.getSubjectZhenzhi() == null ? "" : bksExamDTO.getSubjectZhenzhi());
                dataMap.put("scoreHuaxue", bksExamDTO.getSubjectHuaxue() == null ? "" : bksExamDTO.getSubjectHuaxue());
                dataMap.put("scoreWuli", bksExamDTO.getSubjectWuli() == null ? "" : bksExamDTO.getSubjectWuli());
                dataMap.put("scoreLishi", bksExamDTO.getSubjectLishi() == null ? "" : bksExamDTO.getSubjectLishi());
                Double totalScore =
                        (bksExamDTO.getSubjectYuwen() == null ? 0.00 : bksExamDTO.getSubjectYuwen()) +
                                (bksExamDTO.getSubjectShuxue() == null ? 0.00 : bksExamDTO.getSubjectShuxue()) +
                                (bksExamDTO.getSubjectYingyu() == null ? 0.00 : bksExamDTO.getSubjectYingyu()) +
                                (bksExamDTO.getSubjectShengwu() == null ? 0.00 : bksExamDTO.getSubjectShengwu()) +
                                (bksExamDTO.getSubjectDili() == null ? 0.00 : bksExamDTO.getSubjectDili()) +
                                (bksExamDTO.getSubjectZhenzhi() == null ? 0.00 : bksExamDTO.getSubjectZhenzhi()) +
                                (bksExamDTO.getSubjectHuaxue() == null ? 0.00 : bksExamDTO.getSubjectHuaxue()) +
                                (bksExamDTO.getSubjectWuli() == null ? 0.00 : bksExamDTO.getSubjectWuli()) +
                                (bksExamDTO.getSubjectLishi() == null ? 0.00 : bksExamDTO.getSubjectLishi());
                dataMap.put("totalScore", totalScore);
            }
            if (courseId != null) {
                if (courseId.equals("1001")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreYuwen", bksExamDTO.getSubjectYuwen() == null ? "" : bksExamDTO.getSubjectYuwen());

                }
                if (courseId.equals("1002")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreShuxue", bksExamDTO.getSubjectShuxue() == null ? "" : bksExamDTO.getSubjectShuxue());
                }
                if (courseId.equals("1003")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreYingyu", bksExamDTO.getSubjectYingyu() == null ? "" : bksExamDTO.getSubjectYingyu());
                }
                if (courseId.equals("1004")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreShengwu", bksExamDTO.getSubjectShengwu() == null ? "" : bksExamDTO.getSubjectShengwu());
                }
                if (courseId.equals("1005")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreDili", bksExamDTO.getSubjectDili() == null ? "" : bksExamDTO.getSubjectDili());
                }
                if (courseId.equals("1006")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreZhengzhi", bksExamDTO.getSubjectZhenzhi() == null ? "" : bksExamDTO.getSubjectZhenzhi());
                }
                if (courseId.equals("1007")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreHuaxue", bksExamDTO.getSubjectHuaxue() == null ? 0.00 : bksExamDTO.getSubjectHuaxue());
                }
                if (courseId.equals("1008")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreWuli()", bksExamDTO.getSubjectWuli() == null ? 0.00 : bksExamDTO.getSubjectWuli());
                }
                if (courseId.equals("1009")) {
                    dataMap.put("examName", bksExamDTO.getExamName());
                    dataMap.put("scoreLishi()", bksExamDTO.getSubjectLishi() == null ? 0.00 : bksExamDTO.getSubjectLishi());
                }
            }
            list.add(dataMap);
        }
        return ResponseResult.app(0, 0, "", list);
    }

    /**
     * 查询考试批次
     *
     * @param userId
     * @return
     */
    @GetMapping("/exambatch")
    @ApiOperation(value = "查询考试批次", notes = "查询考试批次")
    public ResponseResult getExamBatch(
            @ApiParam(value = "用户编号", required = true) String userId) {
        String sdudentcode = getSdudentcode(userId);
        List<Map<String, Object>> list = new ArrayList<>();
        QueryWrapper<BksStudentTestscore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", sdudentcode);
        List<BksStudentTestscore> studentTestscoreList = testscoreService.list(queryWrapper);
        //  List<BksExamDTO> examBatch = testscoreService.getExamBatch(sdudentcode);
        for (BksStudentTestscore bksStudentTestscore : studentTestscoreList) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("examName", bksStudentTestscore.getExamName());
            dataMap.put("subjectComb", bksStudentTestscore.getSubjectComb());
            list.add(dataMap);
        }
        return ResponseResult.app(0, 0, "", list);
    }

    @Autowired
    private BksUserSchoolMajorService bksUserSchoolMajorService;

    /**
     * 查询学校热度
     *
     * @param
     * @return
     */
    @GetMapping("/schoolHeat")
    @ApiOperation(value = "查询学校热度", notes = "查询学校热度")
    public ResponseResult getSchoolHeat(@ApiParam(value = "当前页", required = true) Integer page, @ApiParam(value = "用户编号", required = true) String userId) {
        if (page == null || page == 0) {
            page = 1;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        List<BksSchoolDTO> schoolHeat = bksSchoolService.getSchoolHeat((page - 1) * 15);
        int i = (page - 1) * 15 + 1;
        for (BksSchoolDTO bksSchoolDTO : schoolHeat) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", i++);
            dataMap.put("school_id", bksSchoolDTO.getSchoolId());
            dataMap.put("school_name", bksSchoolDTO.getSchoolName());
            dataMap.put("province_name", bksSchoolDTO.getProvinceName());
            dataMap.put("campus_name", bksSchoolDTO.getCampusName());
            dataMap.put("popularity", bksSchoolDTO.getPopularity());
            QueryWrapper<BksUserSchoolMajor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("school_id", bksSchoolDTO.getSchoolId());
            int count = bksUserSchoolMajorService.count(queryWrapper);
            if (count > 0) {
                dataMap.put("attention", "1");
            } else {
                dataMap.put("attention", "0");
            }
            list.add(dataMap);
        }
        int popularity = bksSchoolService.count(new QueryWrapper<BksSchool>().orderByDesc("popularity"));
        return ResponseResult.app(0, 0, String.valueOf(popularity), list);
    }


    /**
     * 查询专业热度
     *
     * @param
     * @return
     */
    @GetMapping("/majorlHeat")
    @ApiOperation(value = "查询专业热度", notes = "查询专业热度")
    public ResponseResult getmajorlHeat(@ApiParam(value = "当前页", required = true) Integer page, @ApiParam(value = "用户编号", required = true) String userId) {
        if (page == null || page == 0) {
            page = 1;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        List<BksAttentionedMajorsDTO> majorHeat = bksMajorCategoryService.getMajorHeat((page - 1) * 15);
        int i = (page - 1) * 15 + 1;
        for (BksAttentionedMajorsDTO bksAttentionedMajorsDTO : majorHeat) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", i++);
            dataMap.put("major_category_id", bksAttentionedMajorsDTO.getMajorCategoryId());
            dataMap.put("major_category_name", bksAttentionedMajorsDTO.getMajorCategoryName());
            dataMap.put("level2_name", bksAttentionedMajorsDTO.getLevel2Name());
            dataMap.put("level1_name", bksAttentionedMajorsDTO.getLevel1Name());
            dataMap.put("major_category_heat_ranking", bksAttentionedMajorsDTO.getMajorCategoryHeatRanking());
            QueryWrapper<BksUserSchoolMajor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("major_id", bksAttentionedMajorsDTO.getMajorCategoryId());
            int count = bksUserSchoolMajorService.count(queryWrapper);
            if (count > 0) {
                dataMap.put("attention", "1");
            } else {
                dataMap.put("attention", "0");
            }
            list.add(dataMap);
        }
        int count = bksMajorCategoryService.count(new QueryWrapper<BksMajorCategory>().orderByDesc("major_category_heat_ranking"));
        return ResponseResult.app(0, 0, String.valueOf(count), list);
    }

    /**
     * 查询考试批次分数
     *
     * @param
     * @return
     */
    @GetMapping("/getExamBatchScore")
    @ApiOperation(value = "查询考试批次分数", notes = "查询考试批次分数")
    public ResponseResult getExamBatchScore(@ApiParam(value = "用户编号", required = true) String userId, @ApiParam(value = "考试批次名称", required = true) String examName) {
        String sdudentcode = getSdudentcode(userId);
        QueryWrapper<BksStudentTestscore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", sdudentcode);
        if (examName == null || examName.equals("")) {
            Map<String, Object> dataMap = new HashMap<>();
            queryWrapper.orderByDesc("gmt_create");
            List<BksStudentTestscore> list = testscoreService.list(queryWrapper);
            if (list == null || list.size() == 0) {
                return ResponseResult.app(1, 1, "考试信息不存在", dataMap);
            }
            BksStudentTestscore bksExamDTO = list.get(0);
            Double totalScore =
                    (bksExamDTO.getSubjectYuwen() == null ? 0.00 : bksExamDTO.getSubjectYuwen()) +
                            (bksExamDTO.getSubjectShuxue() == null ? 0.00 : bksExamDTO.getSubjectShuxue()) +
                            (bksExamDTO.getSubjectYingyu() == null ? 0.00 : bksExamDTO.getSubjectYingyu()) +
                            (bksExamDTO.getSubjectShengwu() == null ? 0.00 : bksExamDTO.getSubjectShengwu()) +
                            (bksExamDTO.getSubjectDili() == null ? 0.00 : bksExamDTO.getSubjectDili()) +
                            (bksExamDTO.getSubjectZhenzhi() == null ? 0.00 : bksExamDTO.getSubjectZhenzhi()) +
                            (bksExamDTO.getSubjectHuaxue() == null ? 0.00 : bksExamDTO.getSubjectHuaxue()) +
                            (bksExamDTO.getSubjectWuli() == null ? 0.00 : bksExamDTO.getSubjectWuli()) +
                            (bksExamDTO.getSubjectLishi() == null ? 0.00 : bksExamDTO.getSubjectLishi());
            dataMap.put("totalScore", totalScore);
            dataMap.put("examName", bksExamDTO.getExamName());
            return ResponseResult.app(0, 0, "", dataMap);
        } else {
            Map<String, Object> dataMap = new HashMap<>();
            queryWrapper.eq("exam_name", examName);
            BksStudentTestscore bksExamDTO = testscoreService.getOne(queryWrapper);
            if (bksExamDTO == null) {
                return ResponseResult.app(1, 1, "考试信息不存在", dataMap);
            }
            Double totalScore =
                    (bksExamDTO.getSubjectYuwen() == null ? 0.00 : bksExamDTO.getSubjectYuwen()) +
                            (bksExamDTO.getSubjectShuxue() == null ? 0.00 : bksExamDTO.getSubjectShuxue()) +
                            (bksExamDTO.getSubjectYingyu() == null ? 0.00 : bksExamDTO.getSubjectYingyu()) +
                            (bksExamDTO.getSubjectShengwu() == null ? 0.00 : bksExamDTO.getSubjectShengwu()) +
                            (bksExamDTO.getSubjectDili() == null ? 0.00 : bksExamDTO.getSubjectDili()) +
                            (bksExamDTO.getSubjectZhenzhi() == null ? 0.00 : bksExamDTO.getSubjectZhenzhi()) +
                            (bksExamDTO.getSubjectHuaxue() == null ? 0.00 : bksExamDTO.getSubjectHuaxue()) +
                            (bksExamDTO.getSubjectWuli() == null ? 0.00 : bksExamDTO.getSubjectWuli()) +
                            (bksExamDTO.getSubjectLishi() == null ? 0.00 : bksExamDTO.getSubjectLishi());
            dataMap.put("totalScore", totalScore);
            dataMap.put("examName", examName);
            return ResponseResult.app(0, 0, "", dataMap);

        }

    }


    /**
     * 成绩分析
     *
     * @param userId
     * @param courseId
     * @return
     */
    @GetMapping("/getExamAnalyse")
    @ApiOperation(value = "成绩分析", notes = "成绩分析")
    public ResponseResult getExamAnalyse(@ApiParam(value = "用户编号", required = true) String userId,
                                         @ApiParam(value = "科目id 1001:语文，1002：数学，1003：英语，1004：生物，1005地理，1006：政治，1007:化学，1008：物理，1009：历史", required = false) String courseId) {
        String sdudentcode = getSdudentcode(userId);

        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        List<BksExamDTO> examList = testscoreService.getExamAnalyse(sdudentcode);
        List<String> choose_subject = new ArrayList<>();
        if (examList == null || examList.size() == 0) {
            return ResponseResult.app(1, 1, "该考试信息不存在", map);
        }
        BksExamDTO dto = examList.get(0);
        choose_subject.add("语文");
        choose_subject.add("数学");
        choose_subject.add("英语");
        if (dto.getSubjectShengwu() != null) {
            choose_subject.add("生物");
        }
        if (dto.getSubjectDili() != null) {
            choose_subject.add("地理");
        }
        if (dto.getSubjectZhenzhi() != null) {
            choose_subject.add("政治");
        }
        if (dto.getSubjectHuaxue() != null) {
            choose_subject.add("化学");
        }
        if (dto.getSubjectWuli() != null) {
            choose_subject.add("物理");
        }
        if (dto.getSubjectLishi() != null) {
            choose_subject.add("历史");
        }
        map.put("subject", choose_subject);
        for (BksExamDTO bksExamDTO : examList) {
            Map<String, Object> dataMap = new HashMap<>();
            if (courseId == null || courseId.equals("")) {
                dataMap.put("name", bksExamDTO.getExamName());
//                dataMap.put("score1", bksExamDTO.getSubjectYuwen() == null ? "" : bksExamDTO.getSubjectYuwen());
//                dataMap.put("score2", bksExamDTO.getSubjectShuxue() == null ? "" : bksExamDTO.getSubjectShuxue());
//                dataMap.put("score3", bksExamDTO.getSubjectYingyu() == null ? "" : bksExamDTO.getSubjectYingyu());
//                dataMap.put("score4", bksExamDTO.getSubjectShengwu() == null ? "" : bksExamDTO.getSubjectShengwu());
//                dataMap.put("score5", bksExamDTO.getSubjectDili() == null ? "" : bksExamDTO.getSubjectDili());
//                dataMap.put("score6", bksExamDTO.getSubjectZhenzhi() == null ? "" : bksExamDTO.getSubjectZhenzhi());
//                dataMap.put("score7", bksExamDTO.getSubjectHuaxue() == null ? "" : bksExamDTO.getSubjectHuaxue());
//                dataMap.put("score8", bksExamDTO.getSubjectWuli() == null ? "" : bksExamDTO.getSubjectWuli());
//                dataMap.put("score9", bksExamDTO.getSubjectLishi() == null ? "" : bksExamDTO.getSubjectLishi());
                Double totalScore =
                        (bksExamDTO.getSubjectYuwen() == null ? 0.00 : bksExamDTO.getSubjectYuwen()) +
                                (bksExamDTO.getSubjectShuxue() == null ? 0.00 : bksExamDTO.getSubjectShuxue()) +
                                (bksExamDTO.getSubjectYingyu() == null ? 0.00 : bksExamDTO.getSubjectYingyu()) +
                                (bksExamDTO.getSubjectShengwu() == null ? 0.00 : bksExamDTO.getSubjectShengwu()) +
                                (bksExamDTO.getSubjectDili() == null ? 0.00 : bksExamDTO.getSubjectDili()) +
                                (bksExamDTO.getSubjectZhenzhi() == null ? 0.00 : bksExamDTO.getSubjectZhenzhi()) +
                                (bksExamDTO.getSubjectHuaxue() == null ? 0.00 : bksExamDTO.getSubjectHuaxue()) +
                                (bksExamDTO.getSubjectWuli() == null ? 0.00 : bksExamDTO.getSubjectWuli()) +
                                (bksExamDTO.getSubjectLishi() == null ? 0.00 : bksExamDTO.getSubjectLishi());

                dataMap.put("score", totalScore);
            }
            if (courseId != null) {
                if (courseId.equals("语文")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectYuwen());
                }
                if (courseId.equals("数学")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectShuxue());
                }
                if (courseId.equals("英语")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectYingyu());
                }
                if (courseId.equals("生物")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectShengwu());
                }
                if (courseId.equals("地理")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectDili());
                }
                if (courseId.equals("政治")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectZhenzhi());
                }
                if (courseId.equals("化学")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectHuaxue());
                }
                if (courseId.equals("物理")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectWuli());
                }
                if (courseId.equals("历史")) {
                    dataMap.put("name", bksExamDTO.getExamName());
                    dataMap.put("score", bksExamDTO.getSubjectLishi());
                }
            }
            list.add(dataMap);
            map.put("data", list);


        }

        return ResponseResult.app(0, 0, "", map);
    }


    /**
     * 考试全市平均分
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/getAvgScore")
    @ApiOperation(value = "考试全市平均分", notes = "考试全市平均分")
    public ResponseResult getAvgScore(@ApiParam(value = "用户编号", required = true) String userId,
                                      @ApiParam(value = "科目id 1001:语文，1002：数学，1003：英语，1004：生物，1005地理，1006：政治，1007:化学，1008：物理，1009：历史", required = false) String courseId,
                                      @ApiParam(value = "1：省市，2：区县，3：学校", required = false) String num) {
        String sdudentcode = getSdudentcode(userId);
        QueryWrapper<BksExamAvgAchievement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", sdudentcode);
        List<BksExamAvgAchievement> list = bksExamAvgAchievementService.list(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<Double> scoreList = new ArrayList<>();
        for (BksExamAvgAchievement bksExamAvgAchievement : list) {

            if (num == null || num.equals("") || num.equals("1")) {
                if (courseId == null || courseId.equals("")) {
                    nameList.add(bksExamAvgAchievement.getExamBatches());
                    scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievement());
                }
                if (courseId != null) {
                    if (courseId.equals("语文")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementYw());
                    }
                    if (courseId.equals("数学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementSx());
                    }
                    if (courseId.equals("英语")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementYy());
                    }
                    if (courseId.equals("生物")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementSw());
                    }
                    if (courseId.equals("地理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementDl());
                    }
                    if (courseId.equals("政治")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementZz());
                    }
                    if (courseId.equals("化学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementHx());
                    }
                    if (courseId.equals("物理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementWl());
                    }
                    if (courseId.equals("历史")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementLs());
                    }
                }
            } else if (num.equals("2")) {
                if (courseId == null || courseId.equals("")) {
                    nameList.add(bksExamAvgAchievement.getExamBatches());
                    scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievement());
                }
                if (courseId != null) {
                    if (courseId.equals("语文")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementYw());
                    }
                    if (courseId.equals("数学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementSx());
                    }
                    if (courseId.equals("英语")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementYy());
                    }
                    if (courseId.equals("生物")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementSw());
                    }
                    if (courseId.equals("地理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementDl());
                    }
                    if (courseId.equals("政治")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementZz());
                    }
                    if (courseId.equals("化学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementHx());
                    }
                    if (courseId.equals("物理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementWl());
                    }
                    if (courseId.equals("历史")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMaxAchievementLs());
                    }
                }
            } else if (num.equals("3")) {
                if (courseId == null || courseId.equals("")) {
                    nameList.add(bksExamAvgAchievement.getExamBatches());
                    scoreList.add(bksExamAvgAchievement.getProvincesMinAchievement());
                }
                if (courseId != null) {
                    if (courseId.equals("语文")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementYw());
                    }
                    if (courseId.equals("数学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementSx());
                    }
                    if (courseId.equals("英语")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementYy());
                    }
                    if (courseId.equals("生物")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementSw());
                    }
                    if (courseId.equals("地理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementDl());
                    }
                    if (courseId.equals("政治")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementZz());
                    }
                    if (courseId.equals("化学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementHx());
                    }
                    if (courseId.equals("物理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementWl());
                    }
                    if (courseId.equals("历史")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        scoreList.add(bksExamAvgAchievement.getProvincesMinAchievementLs());
                    }
                }
            }
            map.put("name", nameList);
            map.put("score", scoreList);

        }

        return ResponseResult.app(0, 0, "", map);
    }


    /**
     * 每次考试排名雷达
     *
     * @param
     * @param
     * @return
     */
    @ApiOperation(value = "考试排名雷达", notes = "考试排名雷达")
    @GetMapping("/getranking")
    public ResponseResult getRanking(
            @ApiParam(value = "用户编号", required = true) String userId,
            @ApiParam(value = "科目id 1001:语文，1002：数学，1003：英语，1004：生物，1005地理，1006：政治，1007:化学，1008：物理，1009：历史", required = false) String courseId,
            @ApiParam(value = "1：省市，2：区县，3：学校", required = false) String num) {

        String sdudentcode = getSdudentcode(userId);
        List<BksExamDTO> examList = testscoreService.getExamAnalyse(sdudentcode);
        List<String> choose_subject = new ArrayList<>();
        if (examList == null || examList.size() == 0) {
            return ResponseResult.app(1, 1, "考试记录不存在", "");
        }
        BksExamDTO dto = examList.get(0);
        choose_subject.add("语文");
        choose_subject.add("数学");
        choose_subject.add("英语");
        if (dto.getSubjectShengwu() != null) {
            choose_subject.add("生物");
        }
        if (dto.getSubjectDili() != null) {
            choose_subject.add("地理");
        }
        if (dto.getSubjectZhenzhi() != null) {
            choose_subject.add("政治");
        }
        if (dto.getSubjectHuaxue() != null) {
            choose_subject.add("化学");
        }
        if (dto.getSubjectWuli() != null) {
            choose_subject.add("物理");
        }
        if (dto.getSubjectLishi() != null) {
            choose_subject.add("历史");
        }

        QueryWrapper<BksExamRanking> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", sdudentcode);
        List<BksExamRanking> list = bksExamRankingService.list(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<Double> rankingList = new ArrayList<>();
        for (BksExamRanking bksExamAvgAchievement : list) {
            if (num == null || num.equals("") || num.equals("1")) {
                if (courseId == null || courseId.equals("")) {
                    nameList.add(bksExamAvgAchievement.getExamBatches());
                    rankingList.add(bksExamAvgAchievement.getProvincesRanking());
                }
                if (courseId != null) {
                    if (courseId.equals("语文")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingYw());
                    }
                    if (courseId.equals("数学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingSx());
                    }
                    if (courseId.equals("英语")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingYy());
                    }
                    if (courseId.equals("生物")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingSw());
                    }
                    if (courseId.equals("地理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingDl());
                    }
                    if (courseId.equals("政治")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingZz());
                    }
                    if (courseId.equals("化学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingHx());
                    }
                    if (courseId.equals("物理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingWl());
                    }
                    if (courseId.equals("历史")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getProvincesRankingLs());
                    }
                }
            } else if (num.equals("2")) {
                if (courseId == null || courseId.equals("")) {
                    nameList.add(bksExamAvgAchievement.getExamBatches());
                    rankingList.add(bksExamAvgAchievement.getDistrictRanking());
                }
                if (courseId != null) {
                    if (courseId.equals("语文")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingYw());
                    }
                    if (courseId.equals("数学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingSx());
                    }
                    if (courseId.equals("英语")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingYy());
                    }
                    if (courseId.equals("生物")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingSw());
                    }
                    if (courseId.equals("地理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingDl());
                    }
                    if (courseId.equals("政治")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingZz());
                    }
                    if (courseId.equals("化学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingHx());
                    }
                    if (courseId.equals("物理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingWl());
                    }
                    if (courseId.equals("历史")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getDistrictRankingLs());
                    }
                }
            } else if (num.equals("3")) {
                if (courseId == null || courseId.equals("")) {
                    nameList.add(bksExamAvgAchievement.getExamBatches());
                    rankingList.add(bksExamAvgAchievement.getGradeRanking());
                }
                if (courseId != null) {
                    if (courseId.equals("语文")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingYw());
                    }
                    if (courseId.equals("数学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingSx());
                    }
                    if (courseId.equals("英语")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingYy());
                    }
                    if (courseId.equals("生物")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingSw());
                    }
                    if (courseId.equals("地理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingDl());
                    }
                    if (courseId.equals("政治")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingZz());
                    }
                    if (courseId.equals("化学")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingHx());
                    }
                    if (courseId.equals("物理")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingWl());
                    }
                    if (courseId.equals("历史")) {
                        nameList.add(bksExamAvgAchievement.getExamBatches());
                        rankingList.add(bksExamAvgAchievement.getGradeRankingLs());
                    }
                }
            }
            map.put("name", nameList);
            map.put("ranking", rankingList);
            map.put("subject", choose_subject);

        }
        return ResponseResult.app(0, 0, "", map);
    }


    @GetMapping("/getrank")
    @ApiOperation(value = "考试排名", notes = "考试排名")
    public ResponseResult getRankingSubject(
            @ApiParam(value = "用户编号", required = true) String userId,
            @ApiParam(value = "考试批次名称", required = false) String examName,
            @ApiParam(value = "1：省市，2：区县，3：学校", required = false) String num) {
        String sdudentcode = getSdudentcode(userId);
        Map<String, Object> map = new HashMap<>();
        List<String> choose_subject = new ArrayList<>();
        choose_subject.add("总分");
        List<String> nameList = new ArrayList<>();
        List<Double> rankingList = new ArrayList<>();
        List<Double> totalList = new ArrayList<>();
        QueryWrapper<BksExamRanking> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", sdudentcode);
        BksExamRanking bksExamAvgAchievement = new BksExamRanking();
        if (examName == null || examName.equals("")) {
            queryWrapper.orderByDesc("gmt_create");
            List<BksExamRanking> list = bksExamRankingService.list(queryWrapper);
            if (list == null || list.size() == 0) {
                return ResponseResult.app(0, 0, "考试记录不存在", "");
            } else {
                bksExamAvgAchievement = list.get(0);
            }
        } else {
            queryWrapper.eq("exam_batches", examName);
            bksExamAvgAchievement = bksExamRankingService.list(queryWrapper).get(0);
        }
        //bksExamAvgAchievement 数据库必须有值如果为null，会报错
        QueryWrapper<BksExamAvgAchievement> query = new QueryWrapper<>();
        query.eq("student_id", sdudentcode);
        query.eq("exam_batches", bksExamAvgAchievement.getExamBatches());
        BksExamAvgAchievement one = bksExamAvgAchievementService.list(query).get(0);
        if (num == null || num.equals("") || num.equals("1")) {

            nameList.add(bksExamAvgAchievement.getExamBatches());
            rankingList.add(bksExamAvgAchievement.getProvincesRanking());
            totalList.add(one.getProvincesPersons().doubleValue());
            map.put("count", one.getProvincesPersons() + 1);
            if (bksExamAvgAchievement.getProvincesRankingYw() != null) {
                choose_subject.add("语文");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingYw());
                totalList.add(one.getProvincesPersonYw().doubleValue());

            }
            if (bksExamAvgAchievement.getProvincesRankingSx() != null) {
                choose_subject.add("数学");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingSx());
                totalList.add(one.getProvincesPersonSx().doubleValue());
            }
            if (bksExamAvgAchievement.getProvincesRankingYy() != null) {
                choose_subject.add("英语");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingYy());
                totalList.add(one.getProvincesPersonYy().doubleValue());
            }
            if (bksExamAvgAchievement.getProvincesRankingSw() != null) {
                choose_subject.add("生物");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingSw());
                totalList.add(one.getProvincesPersonSw().doubleValue());
            }

            if (bksExamAvgAchievement.getProvincesRankingDl() != null) {
                choose_subject.add("地理");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingDl());
                totalList.add(one.getProvincesPersonDl().doubleValue());
            }
            if (bksExamAvgAchievement.getProvincesRankingZz() != null) {
                choose_subject.add("政治");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingZz());
                totalList.add(one.getProvincesPersonZz().doubleValue());
            }
            if (bksExamAvgAchievement.getProvincesRankingHx() != null) {
                choose_subject.add("化学");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingHx());
                totalList.add(one.getProvincesPersonHx().doubleValue());
            }
            if (bksExamAvgAchievement.getProvincesRankingWl() != null) {
                choose_subject.add("物理");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingWl());
                totalList.add(one.getProvincesPersonWl().doubleValue());
            }
            if (bksExamAvgAchievement.getProvincesRankingLs() != null) {
                choose_subject.add("历史");
                rankingList.add(bksExamAvgAchievement.getProvincesRankingLs());
                totalList.add(one.getProvincesPersonLs().doubleValue());
            }

        } else if (num.equals("2")) {
            rankingList.add(bksExamAvgAchievement.getDistrictRanking());
            nameList.add(bksExamAvgAchievement.getExamBatches());
            totalList.add(one.getDistrictPersons().doubleValue());
            map.put("count", one.getDistrictPersons() + 1);
            if (bksExamAvgAchievement.getDistrictRankingYw() != null) {
                choose_subject.add("语文");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingYw());
                totalList.add(one.getDistrictPersonYw().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingSx() != null) {
                choose_subject.add("数学");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingSx());
                totalList.add(one.getDistrictPersonSx().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingYy() != null) {
                choose_subject.add("英语");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingYy());
                totalList.add(one.getDistrictPersonYy().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingSw() != null) {
                choose_subject.add("生物");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingSw());
                totalList.add(one.getDistrictPersonSw().doubleValue());
            }

            if (bksExamAvgAchievement.getProvincesRankingDl() != null) {
                choose_subject.add("地理");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingDl());
                totalList.add(one.getDistrictPersonDl().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingZz() != null) {
                choose_subject.add("政治");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingZz());
                totalList.add(one.getDistrictPersonZz().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingHx() != null) {
                choose_subject.add("化学");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingHx());
                totalList.add(one.getDistrictPersonHx().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingWl() != null) {
                choose_subject.add("物理");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingWl());
                totalList.add(one.getDistrictPersonWl().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingLs() != null) {
                choose_subject.add("历史");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingLs());
                totalList.add(one.getDistrictPersonLs().doubleValue());
            }
        } else if (num.equals("3")) {
            nameList.add(bksExamAvgAchievement.getExamBatches());
            rankingList.add(bksExamAvgAchievement.getGradeRanking());
            totalList.add(one.getGradePersons().doubleValue());
            map.put("count", one.getGradePersons() + 1);
            if (bksExamAvgAchievement.getGradeRankingYw() != null) {
                choose_subject.add("语文");
                rankingList.add(bksExamAvgAchievement.getGradeRankingYw());
                totalList.add(one.getGradePersonYw().doubleValue());
            }
            if (bksExamAvgAchievement.getGradeRankingSx() != null) {
                choose_subject.add("数学");
                rankingList.add(bksExamAvgAchievement.getGradeRankingSx());
                totalList.add(one.getGradePersonSx().doubleValue());
            }
            if (bksExamAvgAchievement.getGradeRankingYy() != null) {
                choose_subject.add("英语");
                rankingList.add(bksExamAvgAchievement.getGradeRankingYy());
                totalList.add(one.getGradePersonYy().doubleValue());
            }
            if (bksExamAvgAchievement.getGradeRankingSw() != null) {
                choose_subject.add("生物");
                rankingList.add(bksExamAvgAchievement.getGradeRankingSw());
                totalList.add(one.getGradePersonSw().doubleValue());
            }
            if (bksExamAvgAchievement.getGradeRankingDl() != null) {
                choose_subject.add("地理");
                rankingList.add(bksExamAvgAchievement.getGradeRankingDl());
                totalList.add(one.getGradePersonDl().doubleValue());
            }
            if (bksExamAvgAchievement.getGradeRankingZz() != null) {
                choose_subject.add("政治");
                rankingList.add(bksExamAvgAchievement.getGradeRankingZz());
                totalList.add(one.getGradePersonZz().doubleValue());
            }
            if (bksExamAvgAchievement.getDistrictRankingHx() != null) {
                choose_subject.add("化学");
                rankingList.add(bksExamAvgAchievement.getDistrictRankingHx());
                totalList.add(one.getGradePersonHx().doubleValue());
            }
            if (bksExamAvgAchievement.getGradeRankingWl() != null) {
                choose_subject.add("物理");
                rankingList.add(bksExamAvgAchievement.getGradeRankingWl());
                totalList.add(one.getGradePersonWl().doubleValue());
            }
            if (bksExamAvgAchievement.getGradeRankingLs() != null) {
                choose_subject.add("历史");
                rankingList.add(bksExamAvgAchievement.getGradeRankingLs());
                totalList.add(one.getGradePersonLs().doubleValue());
            }
        }
        map.put("examBatch", nameList);
        map.put("subject", choose_subject);
        map.put("ranking", rankingList);
        map.put("personList", totalList);
        return ResponseResult.app(0, 0, "", map);
    }

    /**
     * @author liushuangqing
     * @date 2019/11/22 16:05
     */
    @GetMapping("/recentScore")
    @ApiOperation("最新成绩")
    public ResponseResult newScore(String userId) {

        BksExamRankingVO bksExamRanking = new BksExamRankingVO();
        bksExamRanking.setId(50);
        bksExamRanking.setMyScore(543.5);
        bksExamRanking.setProvincesRanking(350.0);
        bksExamRanking.setArtsFirstLine(545.0);
        bksExamRanking.setArtsSecondLine(458.0);
        bksExamRanking.setScienceFirstLine(525.0);
        bksExamRanking.setScienceSecondLine(435.0);

        return new ResponseResult(0, 0, "", bksExamRanking);
    }

    /**
     * @author liushuangqing
     * @date 2019/11/22 16:05
     */
    @PostMapping("/scoreQuery")
    @ApiOperation("成绩查询_小程序")
    public RequestResult<List<BksStudentTestscore>> scoreQuery(@RequestBody @Valid ScoreQueryVO queryVO) {
        //设置默认页码
        queryVO.setPageCurrent(queryVO.getPageCurrent() == 0 || queryVO.getPageCurrent() == null ? 1 : queryVO.getPageCurrent())
                .setPageSize(queryVO.getPageSize() == 0 || queryVO.getPageSize() == null ? 5 : queryVO.getPageSize());
        //查询条件
        QueryWrapper<BksStudentTestscore> query = new QueryWrapper<>();
        query.eq("school", queryVO.getSchoolName())
                .eq("name", queryVO.getName())
                .eq(queryVO.getStudentId() != null && !"".equals(queryVO.getStudentId()), "student_id", queryVO.getStudentId());

        IPage<BksStudentTestscore> pages = testscoreService.page(new Page<>(queryVO.getPageCurrent(), queryVO.getPageSize()), query);
        return new RequestResult<>(pages.getRecords());
    }

    /**
     * @author liushuangqing
     * @date 2019/11/22 16:05
     */
    @GetMapping("/scoreAnalysis")
    @ApiOperation("成绩分析_小程序")
    public ResponseResult scoreAnalysis(@RequestParam(value = "examId") Integer examId) {

        BksExamRanking examRanking = bksExamRankingService.getById(examId);
        QueryWrapper<BksStudentTestscore> query = new QueryWrapper<>();
        query.eq("exam_name", examRanking.getExamBatches());
        query.eq("student_id", examRanking.getStudentId());
//        query.eq("name",examRanking.getName());
        BksStudentTestscore testscore = testscoreService.getOne(query);
        BksScoreAnalysisVO scoreAnalysis = new BksScoreAnalysisVO();
        BeanUtils.copyProperties(examRanking, scoreAnalysis);
        BeanUtils.copyProperties(testscore, scoreAnalysis);
        return new ResponseResult(0, 0, "", scoreAnalysis);
    }
}
