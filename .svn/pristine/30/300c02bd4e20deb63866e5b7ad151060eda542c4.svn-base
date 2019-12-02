package com.benwunet.bks.controller.tourists;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwunet.bks.constants.Constants;
import com.benwunet.bks.entity.homepage.BksExamAvgAchievementVo;
import com.benwunet.bks.model.*;
import com.benwunet.bks.service.*;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/7/25 17:21
 */

@RestController
@RequestMapping
@Api(value = "考试成绩相关", tags = "考试成绩相关")
public class ExamController {

    @Autowired
    private BksUserService userService;

    @Autowired
    private BksStudentTestscoreService testscoreService;
    @Autowired
    private BksExamAvgAchievementService bksExamAvgAchievementService;
    @Autowired
    private BksExamRankingService bksExamRankingService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param year
     * @param examName
     * @param courseId
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "成绩查询", notes = "成绩查询")
    @GetMapping("/studentExamList")
    public ResponseResult getExamList(
            @ApiParam(value = "学级", required = false) String year,
            @ApiParam(value = "考试批次名称", required = false) String examName,
            @ApiParam(value = "科目id 1001:语文，1002：数学，1003：英语，1004：生物，1005地理，1006：政治，1007:化学，1008：物理，1009：历史", required = false) String courseId,
            @ApiParam(value = "学校名称", required = true)String schoolName,
            @ApiParam(value = "查询关键字：学号或者姓名", required = false) String keyword,
            @ApiParam(value = "当前页", required = true) Integer page,
            @ApiParam(value = "每页数量", required = true) Integer size) {
        if (page == null || page == 0) {
            page = 1;
        }
        QueryWrapper<BksExamUpload> query = new QueryWrapper<>();
        query.orderByDesc("gmt_create");
        query.eq("school_name",schoolName);
        query.eq("status",1);
        BksExamUpload bksStudentTestscore = bksExamUploadService.list(query).get(0);
        if (bksStudentTestscore == null) {
            return ResponseResult.app(0, 0, "学生成绩数据不存在", "");
        }
        if (year == null || year.equals("")) {
            year = bksStudentTestscore.getSchoolYears();
        }
        if (examName == null || examName.equals("")) {
            examName = bksStudentTestscore.getExamName();
        }

        List<Map<String, Object>> list = new ArrayList<>();
        List<BksStudentTestscore> examList = testscoreService.getStudentExamList(year, examName,schoolName, keyword, (page - 1) * size, size);
        Integer total = testscoreService.getStudentExamListCount(year, examName,schoolName, keyword);
        for (BksStudentTestscore bksExamDTO : examList) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("studentCode", bksExamDTO.getStudentId());
            dataMap.put("studentName", bksExamDTO.getName());
            dataMap.put("schoolYear", bksExamDTO.getSchoolYears());
            dataMap.put("className", bksExamDTO.getClassName());
            dataMap.put("examName", bksExamDTO.getExamName());
            if (courseId == null || courseId.equals("")) {
                dataMap.put("scoreYW", bksExamDTO.getSubjectYuwen() == null ? "" : bksExamDTO.getSubjectYuwen());
                dataMap.put("scoreSX", bksExamDTO.getSubjectShuxue() == null ? "" : bksExamDTO.getSubjectShuxue());
                dataMap.put("scoreYY", bksExamDTO.getSubjectYingyu() == null ? "" : bksExamDTO.getSubjectYingyu());
                dataMap.put("scoreSW", bksExamDTO.getSubjectShengwu() == null ? "" : bksExamDTO.getSubjectShengwu());
                dataMap.put("scoreDL", bksExamDTO.getSubjectDili() == null ? "" : bksExamDTO.getSubjectDili());
                dataMap.put("scoreZZ", bksExamDTO.getSubjectZhenzhi() == null ? "" : bksExamDTO.getSubjectZhenzhi());
                dataMap.put("scoreHX", bksExamDTO.getSubjectHuaxue() == null ? "" : bksExamDTO.getSubjectHuaxue());
                dataMap.put("scoreWL", bksExamDTO.getSubjectWuli() == null ? "" : bksExamDTO.getSubjectWuli());
                dataMap.put("scoreLS", bksExamDTO.getSubjectLishi() == null ? "" : bksExamDTO.getSubjectLishi());
                Double totalScore = (bksExamDTO.getMyScore() == null ? 0.00 : bksExamDTO.getMyScore());
                dataMap.put("totalScore", totalScore);
            }
            if (courseId != null) {
                if (courseId.equals(Constants.SUBJECT_YW)) {
                    dataMap.put("score", bksExamDTO.getSubjectYuwen() == null ? "" : bksExamDTO.getSubjectYuwen());
                }
                if (courseId.equals(Constants.SUBJECT_SX)) {
                    dataMap.put("score", bksExamDTO.getSubjectShuxue() == null ? "" : bksExamDTO.getSubjectShuxue());
                }
                if (courseId.equals(Constants.SUBJECT_YY)) {
                    dataMap.put("score", bksExamDTO.getSubjectYingyu() == null ? "" : bksExamDTO.getSubjectYingyu());
                }
                if (courseId.equals(Constants.SUBJECT_SW)) {
                    dataMap.put("score", bksExamDTO.getSubjectShengwu() == null ? "" : bksExamDTO.getSubjectShengwu());
                }
                if (courseId.equals(Constants.SUBJECT_DL)) {
                    dataMap.put("score", bksExamDTO.getSubjectDili() == null ? "" : bksExamDTO.getSubjectDili());
                }
                if (courseId.equals(Constants.SUBJECT_ZZ)) {
                    dataMap.put("score", bksExamDTO.getSubjectZhenzhi() == null ? "" : bksExamDTO.getSubjectZhenzhi());
                }
                if (courseId.equals(Constants.SUBJECT_HX)) {
                    dataMap.put("score", bksExamDTO.getSubjectHuaxue() == null ? 0.00 : bksExamDTO.getSubjectHuaxue());
                }
                if (courseId.equals(Constants.SUBJECT_WL)) {

                    dataMap.put("score", bksExamDTO.getSubjectWuli() == null ? 0.00 : bksExamDTO.getSubjectWuli());
                }
                if (courseId.equals(Constants.SUBJECT_LS)) {

                    dataMap.put("score", bksExamDTO.getSubjectLishi() == null ? 0.00 : bksExamDTO.getSubjectLishi());
                }
            }
            list.add(dataMap);
        }
        return ResponseResult.page(0, "", (long) total, list);

    }

    @ApiOperation(value = "平均成绩", notes = "平均成绩")
    @GetMapping("/getAvgScore")
    public ResponseResult getAvgScore(
            @ApiParam(value = "学级", required = false) String year,
            @ApiParam(value = "学校名称", required = true) String schoolName,
            @ApiParam(value = "科目id 1001:语文，1002：数学，1003：英语，1004：生物，1005地理，1006：政治，1007:化学，1008：物理，1009：历史", required = false) String courseId,
            @ApiParam(value = "2:学校 1:平台", required = false) String key,
            @ApiParam(value = "1:学校 2:区县 3:市 4:全部", required = false) String num) {
        QueryWrapper<BksExamUpload> query = new QueryWrapper<>();
        query.orderByDesc("gmt_create");
        query.eq("school_name",schoolName);
        query.eq("status",1);
        BksExamUpload bksExamUpload = bksExamUploadService.list(query).get(0);
        if (bksExamUpload == null) {
            return ResponseResult.app(0, 0, "学生成绩数据不存在", "");
        }
        if (year == null || year.equals("")) {
            year = bksExamUpload.getSchoolYears();
        }
        List<BksExamAvgAchievementVo> list = bksExamAvgAchievementService.getAvgScorelist(year, schoolName);

        if (list == null || list.size() == 0) {
            return ResponseResult.app(0, 0, "无考试记录", "");
        }
        Map<String, Object> map = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<Double> gradeScoreList = new ArrayList<>();
        List<Double> districtScoreList = new ArrayList<>();
        List<Double> provinceScoreList = new ArrayList<>();
        for (BksExamAvgAchievementVo bksExamAvgAchievement : list) {
            nameList.add(bksExamAvgAchievement.getExamBatches());
            if (key.equals("1") || key == null || key.equals("")) {
                if (num == null || num.equals("") || num.equals("1")) {
                    if (courseId == null || courseId.equals("")) {
                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievement());
                    }
                    if (courseId != null) {
                        if (courseId.equals(Constants.SUBJECT_YW)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementYw());
                        }
                        if (courseId.equals(Constants.SUBJECT_SX)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementSx());
                        }
                        if (courseId.equals(Constants.SUBJECT_YY)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementYy());
                        }
                        if (courseId.equals(Constants.SUBJECT_SW)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementSw());
                        }
                        if (courseId.equals(Constants.SUBJECT_DL)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementDl());
                        }
                        if (courseId.equals(Constants.SUBJECT_ZZ)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementZz());
                        }
                        if (courseId.equals(Constants.SUBJECT_HX)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementHx());
                        }
                        if (courseId.equals(Constants.SUBJECT_WL)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementWl());
                        }
                        if (courseId.equals(Constants.SUBJECT_LS)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementLs());
                        }
                    }
                }
                if (num.equals("2")) {
                    if (courseId == null || courseId.equals("")) {
                        districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievement());
                    }
                    if (courseId != null) {
                        if (courseId.equals(Constants.SUBJECT_YW)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementYw());
                        }
                        if (courseId.equals(Constants.SUBJECT_SX)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementSx());
                        }
                        if (courseId.equals(Constants.SUBJECT_YY)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementYy());
                        }
                        if (courseId.equals(Constants.SUBJECT_SW)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementSw());
                        }
                        if (courseId.equals(Constants.SUBJECT_DL)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementDl());
                        }
                        if (courseId.equals(Constants.SUBJECT_ZZ)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementZz());
                        }
                        if (courseId.equals(Constants.SUBJECT_HX)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementHx());
                        }
                        if (courseId.equals(Constants.SUBJECT_WL)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementWl());
                        }
                        if (courseId.equals(Constants.SUBJECT_LS)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementLs());
                        }
                    }

                }
                if (num.equals("3")) {
                    if (courseId == null || courseId.equals("")) {
                        provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievement());
                    }
                    if (courseId != null) {
                        if (courseId.equals(Constants.SUBJECT_YW)) {
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementYw());

                        }
                        if (courseId.equals(Constants.SUBJECT_SX)) {
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementSx());

                        }
                        if (courseId.equals(Constants.SUBJECT_YY)) {

                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementYy());
                        }
                        if (courseId.equals(Constants.SUBJECT_SW)) {

                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementSw());
                        }
                        if (courseId.equals(Constants.SUBJECT_DL)) {

                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementDl());
                        }
                        if (courseId.equals(Constants.SUBJECT_ZZ)) {

                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementZz());
                        }
                        if (courseId.equals(Constants.SUBJECT_HX)) {

                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementHx());
                        }
                        if (courseId.equals(Constants.SUBJECT_WL)) {

                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementWl());
                        }
                        if (courseId.equals(Constants.SUBJECT_LS)) {

                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementLs());
                        }
                    }


                }
                if (num.equals("4")) {
                    if (courseId == null || courseId.equals("")) {
                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievement());
                        districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievement());
                        provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievement());
                    }
                    if (courseId != null) {
                        if (courseId.equals(Constants.SUBJECT_YW)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementYw());
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementYw());
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementYw());
                        }
                        if (courseId.equals(Constants.SUBJECT_SX)) {
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementSx());
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementSx());
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementSx());
                        }
                        if (courseId.equals(Constants.SUBJECT_YY)) {
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementYy());
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementYy());
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementYy());
                        }
                        if (courseId.equals(Constants.SUBJECT_SW)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementSw());
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementSw());
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementSw());
                        }
                        if (courseId.equals(Constants.SUBJECT_DL)) {
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementDl());
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementDl());
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementDl());
                        }
                        if (courseId.equals(Constants.SUBJECT_ZZ)) {
                            districtScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementZz());
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementZz());
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementZz());
                        }
                        if (courseId.equals(Constants.SUBJECT_HX)) {
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementHx());
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementHx());
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementHx());
                        }
                        if (courseId.equals(Constants.SUBJECT_WL)) {
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementWl());
                            districtScoreList.add(bksExamAvgAchievement.getDistrictAvgAchievementWl());
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementWl());
                        }
                        if (courseId.equals(Constants.SUBJECT_LS)) {
                            provinceScoreList.add(bksExamAvgAchievement.getProvincesAvgAchievementLs());
                            districtScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementLs());
                            gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementLs());
                        }
                    }
                }
            }
            if (key.equals("2")) {
                if (courseId == null || courseId.equals("")) {
                    gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievement());
                }
                if (courseId != null) {
                    if (courseId.equals(Constants.SUBJECT_YW)) {
                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementYw());

                    }
                    if (courseId.equals(Constants.SUBJECT_SX)) {
                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementSx());

                    }
                    if (courseId.equals(Constants.SUBJECT_YY)) {

                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementYy());
                    }
                    if (courseId.equals(Constants.SUBJECT_SW)) {

                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementSw());
                    }
                    if (courseId.equals(Constants.SUBJECT_DL)) {

                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementDl());
                    }
                    if (courseId.equals(Constants.SUBJECT_ZZ)) {

                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementZz());
                    }
                    if (courseId.equals(Constants.SUBJECT_HX)) {

                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementHx());
                    }
                    if (courseId.equals(Constants.SUBJECT_WL)) {

                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementWl());
                    }
                    if (courseId.equals(Constants.SUBJECT_LS)) {

                        gradeScoreList.add(bksExamAvgAchievement.getGradeAvgAchievementLs());
                    }
                } else {
                    return ResponseResult.app(0, 0, "请正确输入平台或者学校", "");
                }

            }

            map.put("year", year);
            map.put("name", nameList);
            map.put("gradeScore", gradeScoreList);
            map.put("districtScore", districtScoreList);
            map.put("provinceScore", provinceScoreList);
            map.put("schoolName", bksExamAvgAchievement.getSchoolName());

        }
        return ResponseResult.app(0, 0, "", map);
    }

    @Autowired
    private BksExamUploadService bksExamUploadService;

    @ApiOperation(value = "查询学校的考试批次", notes = "查询学校的考试批次")
    @GetMapping("/examNameList")
    public ResponseResult examNameList(
            @ApiParam(value = "1:学校 2:平台", required = false) String key,
            @ApiParam(value = "学校名称", required = true) String schoolName) {
            List<String> mapArrayList = new ArrayList<>();
            List<BksExamUpload> list = bksExamUploadService.getlist(schoolName);
            if (list == null || list.size() == 0) {
                return ResponseResult.app(1, 1, "无考试批次", "");
            }
            for (BksExamUpload bksExamUpload : list) {
                if (bksExamUpload == null || StringUtils.isEmpty(bksExamUpload.getExamName())) {
                    continue;
                }
                mapArrayList.add(bksExamUpload.getExamName());
            }
            if (mapArrayList == null || mapArrayList.size() == 0) {
                return ResponseResult.app(0, 0, "无数据", "");
            }

            return ResponseResult.app(0, 0, "", mapArrayList);
        }


    @ApiOperation(value = "通过学级查询所有考试批次", notes = "通过学级查询所有考试批次")
    @GetMapping("/getExamListByYear")
    public ResponseResult examNameListBySchool(@ApiParam(value = "学级", required = true) String schoolYear) {

        List<BksExamUpload> list = bksExamUploadService.getExamListByYear(schoolYear);
        List<String> collect = list.stream().map(a -> a.getExamName()).collect(Collectors.toList());
        return ResponseResult.app(0, 0, "", collect);

   }

    @ApiOperation(value = "通过学级查询学校对应的考试批次", notes = "通过学级查询学校对应的考试批次")
    @GetMapping("/examNameListBySchoolYear")
    public ResponseResult examNameListBySchoolYear(@ApiParam(value = "学校名称", required = true) String schoolYear, String schoolName) {
        QueryWrapper<BksExamUpload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school_years", schoolYear);
        queryWrapper.eq("school_name", schoolName);
        queryWrapper.eq("status", 1);
        queryWrapper.orderByDesc("gmt_create");
        List<BksExamUpload> list = bksExamUploadService.list(queryWrapper);
        List<String> collect = list.stream().map(a -> a.getExamName()).collect(Collectors.toList());
        return ResponseResult.app(0, 0, "", collect);

    }

    @ApiOperation(value = "查询学校的学级", notes = "查询学校的学级")
    @GetMapping("/schoolYearsListBySchool")
    public ResponseResult schoolYearsListBySchool(@ApiParam(value = "学校名称", required = true) String schoolName) {
        List<BksExamUpload> list = bksExamUploadService.getYearlistBySchool(schoolName);
        List<String> collect = list.stream().map(a -> a.getSchoolYears()).collect(Collectors.toList());
        return ResponseResult.app(0, 0, "", collect);

    }


    @ApiOperation(value = "学级查询", notes = "学级查询")
    @GetMapping("/schoolYearsList")
    public ResponseResult schoolYearsList(@ApiParam(value = "1:学校 2:平台", required = false) String key,
                                          @ApiParam(value = "学校名称", required = true) String schoolName) {
        List<String> mapArrayList = new ArrayList<>();
        if (key == null || key.equals("") || key.equals("2")) {
            List<BksExamUpload> list = bksExamUploadService.getYearlist();
            if (list == null || list.size() == 0) {
                return ResponseResult.app(1, 1, "数据未导入", "");
            }
            for (BksExamUpload bksExamUpload : list) {
                if (bksExamUpload == null || StringUtils.isEmpty(bksExamUpload.getSchoolYears())) {
                    continue;
                }
                mapArrayList.add(bksExamUpload.getSchoolYears());
            }
            if (mapArrayList == null || mapArrayList.size() == 0) {
                return ResponseResult.app(0, 0, "无数据", "");
            }
        } else if (key.equals("1")) {

            List<BksExamUpload> list = bksExamUploadService.getYearlistBySchool(schoolName);
            mapArrayList = list.stream().map(a -> a.getSchoolYears()).collect(Collectors.toList());

        }
        return ResponseResult.app(0, 0, "", mapArrayList);
    }


    @ApiOperation(value = "成绩排名", notes = "成绩排名")
    @GetMapping("/examRankList")
    public ResponseResult examRankList(
            @ApiParam(value = "学级", required = false) String schoolYear,
            @ApiParam(value = "考试批次名称", required = false) String examName,
            @ApiParam(value = "学校名称", required = true) String schoolName,
            @ApiParam(value = "科目id 1001:语文，1002：数学，1003：英语，1004：生物，1005地理，1006：政治，1007:化学，1008：物理，1009：历史", required = false) String
                    courseId,
            @ApiParam(value = "当前页", required = true) Integer page,
            @ApiParam(value = "每页数量", required = true) Integer size) {
        if (page == null || page == 0) {
            page = 1;
        }
        QueryWrapper<BksExamUpload> query = new QueryWrapper<>();
        query.orderByDesc("gmt_create");
        query.eq("school_name",schoolName);
        query.eq("status",1);
        BksExamUpload bksExamUpload = bksExamUploadService.list(query).get(0);
        if (bksExamUpload == null) {
            return ResponseResult.app(0, 0, "学生成绩数据不存在", "");
        }
        if (schoolYear == null || schoolYear.equals("")) {
            schoolYear = bksExamUpload.getSchoolYears();
        }
        if (examName == null || examName.equals("")) {
            examName = bksExamUpload.getExamName();
        }
        List<Map<String, Object>> list = new ArrayList<>();
        List<BksStudentTestscore> examList = testscoreService.getStudentExamRankList(schoolYear, examName, schoolName, courseId, (page - 1) * size, size);
        Integer total = testscoreService.getStudentExamRankListCount(schoolYear, examName,schoolName, courseId);

        if (examList == null || examList.size() == 0) {
            return ResponseResult.app(0, 0, "考试成绩数据不存在", "");
        }
        for (BksStudentTestscore bksExamDTO : examList) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("studentCode", bksExamDTO.getStudentId());
            dataMap.put("studentName", bksExamDTO.getName());
            dataMap.put("schoolYear", bksExamDTO.getSchoolYears());
            dataMap.put("className", bksExamDTO.getClassName());
            dataMap.put("examName", bksExamDTO.getExamName());
            String key = String.format("id_%s_exam_%s_name_%s", bksExamDTO.getStudentId(), bksExamDTO.getExamName(), bksExamDTO.getName());
//            BksExamRanking one = (BksExamRanking) redisTemplate.opsForValue().get(key);
            String o = (String) redisTemplate.opsForValue().get(key);
            BksExamRanking one = JSON.parseObject(o, BksExamRanking.class);
            if (one == null) {
                QueryWrapper<BksExamRanking> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("student_id", bksExamDTO.getStudentId());
                queryWrapper.eq("exam_batches", bksExamDTO.getExamName());
                queryWrapper.eq("school_name", bksExamDTO.getSchool());
                one = bksExamRankingService.getOne(queryWrapper);
                String s = JSON.toJSONString(one);
                redisTemplate.opsForValue().set(key, s, 10, TimeUnit.MINUTES);
            }
            if (one == null) {
                return ResponseResult.app(0, 0, "排名数据不存在", "");
            }
            if (courseId == null || courseId.equals("")) {
                Double totalScore = (one.getMyScore() == null ? 0.00 : one.getMyScore());
                dataMap.put("totalScore", totalScore);
                dataMap.put("ranking", one.getGradeRanking());
                dataMap.put("change", one.getGradeRankingChg());
            }
            if (courseId != null) {
                if (courseId.equals(Constants.SUBJECT_YW)) {
                    dataMap.put("score", bksExamDTO.getSubjectYuwen() == null ? "" : bksExamDTO.getSubjectYuwen());
                    dataMap.put("YWranking", one.getGradeRankingYw());
                    dataMap.put("change", one.getGradeRankingYwChg());
                }
                if (courseId.equals(Constants.SUBJECT_SX)) {
                    dataMap.put("score", bksExamDTO.getSubjectShuxue() == null ? "" : bksExamDTO.getSubjectShuxue());
                    dataMap.put("SXranking", one.getGradeRankingSx());
                    dataMap.put("change", one.getGradeRankingSxChg());
                }
                if (courseId.equals(Constants.SUBJECT_YY)) {
                    dataMap.put("score", bksExamDTO.getSubjectYingyu() == null ? "" : bksExamDTO.getSubjectYingyu());
                    dataMap.put("YYranking", one.getGradeRankingYy());
                    dataMap.put("change", one.getGradeRankingYyChg());
                }
                if (courseId.equals(Constants.SUBJECT_SW)) {
                    dataMap.put("score", bksExamDTO.getSubjectShengwu() == null ? "" : bksExamDTO.getSubjectShengwu());
                    dataMap.put("SWranking", one.getGradeRankingSw());
                    dataMap.put("change", one.getGradeRankingSwChg());
                }
                if (courseId.equals(Constants.SUBJECT_DL)) {
                    dataMap.put("score", bksExamDTO.getSubjectDili() == null ? "" : bksExamDTO.getSubjectDili());
                    dataMap.put("DLranking", one.getGradeRankingDl());
                    dataMap.put("change", one.getGradeRankingDlChg());
                }
                if (courseId.equals(Constants.SUBJECT_ZZ)) {
                    dataMap.put("score", bksExamDTO.getSubjectZhenzhi() == null ? "" : bksExamDTO.getSubjectZhenzhi());
                    dataMap.put("ZZranking", one.getGradeRankingZz());
                    dataMap.put("change", one.getGradeRankingZzChg());
                }
                if (courseId.equals(Constants.SUBJECT_HX)) {
                    dataMap.put("score", bksExamDTO.getSubjectHuaxue() == null ? 0.00 : bksExamDTO.getSubjectHuaxue());
                    dataMap.put("HXranking", one.getGradeRankingHx());
                    dataMap.put("change", one.getGradeRankingHxChg());
                }
                if (courseId.equals(Constants.SUBJECT_WL)) {

                    dataMap.put("score", bksExamDTO.getSubjectWuli() == null ? 0.00 : bksExamDTO.getSubjectWuli());
                    dataMap.put("WLranking", one.getGradeRankingWl());
                    dataMap.put("change", one.getGradeRankingWlChg());
                }
                if (courseId.equals(Constants.SUBJECT_LS)) {
                    dataMap.put("score", bksExamDTO.getSubjectLishi() == null ? 0.00 : bksExamDTO.getSubjectLishi());
                    dataMap.put("LSranking", one.getGradeRankingLs());
                    dataMap.put("change", one.getGradeRankingLsChg());
                }

            }
            list.add(dataMap);
        }

        return ResponseResult.page(0, "", (long) total, list);

    }

    @Autowired
    private BksGradeOneSubsectionService bksGradeOneSubsectionService;

    @Autowired
    private BksUserService bksUserService;

    @GetMapping("/oneMinuteAndOneParagrap")
    @ApiOperation(value = "一分一段", notes = "一分一段")
    public ResponseResult oneMinuteAndOneParagrap(
            @ApiParam(value = "用户编号", required = true) String userId,
            @ApiParam(value = "学级", required = false) String year,
            @ApiParam(value = "考试批次名称", required = false) String examName,
           // @ApiParam(value = "1：区县；2：省市", required = false) String key,
            @ApiParam(value = "当前页", required = true) Integer page,
            @ApiParam(value = "每页数量", required = true) Integer size) {
        QueryWrapper<BksUser> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        BksUser one = userService.getOne(query);
        if (one == null) {
            return ResponseResult.app(1, 1, "该用户不存在", "");
        }

        QueryWrapper<BksExamUpload> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.eq("school_name",one.getSchoolName());
        queryWrapper.eq("status",1);
        BksExamUpload bksExamUpload = bksExamUploadService.list(queryWrapper).get(0);
        if (bksExamUpload == null) {
            return ResponseResult.app(0, 0, "学生成绩数据不存在", "");
        }

        if (year == null || year.equals("")) {
            year = bksExamUpload.getSchoolYears();
        }

        if (examName == null || examName.equals("")) {
            examName =bksExamUpload.getExamName();
        }
       QueryWrapper<BksGradeOneSubsection> gradeOneSubsectionQueryWrapper= new QueryWrapper<>();
       gradeOneSubsectionQueryWrapper.eq("school_years", year);
       gradeOneSubsectionQueryWrapper.eq("school_name",one.getSchoolName());
       gradeOneSubsectionQueryWrapper.eq("exam_name", examName);
       if (page == null || page == 0) {
          page = 1;
       }
        int total = bksGradeOneSubsectionService.count(gradeOneSubsectionQueryWrapper);
      if (total < 0) {
           return ResponseResult.app(1, 1, "一分一段数据不存在", "");
        }
     List<Map<String, Object>> mapList = new ArrayList<>();
       Page<BksGradeOneSubsection> pageQuery = new Page<>(page, size, total);
      IPage<BksGradeOneSubsection> gradeOneSubsections = bksGradeOneSubsectionService.page(pageQuery, gradeOneSubsectionQueryWrapper);

        for (BksGradeOneSubsection gradeOneSubsection : gradeOneSubsections.getRecords()) {
            Map<String, Object> map = new HashMap<>();
            map.put("schoolName", gradeOneSubsection.getSchoolName());
            map.put("seonScore", Math.floor(Double.parseDouble(gradeOneSubsection.getSectionScore())));
            map.put("gradeNums", gradeOneSubsection.getSectionNums());
            map.put("gradeAccumulation", gradeOneSubsection.getSectionAccumulation());
            map.put("schoolYear", gradeOneSubsection.getSchoolYears());
            map.put("examName", gradeOneSubsection.getExamName());
         //   if (key == null || key.equals("") || key.equals("1")) {
                map.put("districtNums", gradeOneSubsection.getDistrictSectionNums());
                map.put("districtAccumulation", gradeOneSubsection.getDictrictSectionAccumulation());
          //  } else if (key.equals("2")) {
                map.put("cityNums", gradeOneSubsection.getCitySectionNums());
                map.put("cityAccumulation", gradeOneSubsection.getCitySectionAccumulation());
          //  } else {
              //  return ResponseResult.app(1, 1, "请正确选择省市区县", "");
               mapList.add(map);
            }

            // String redisKey = String.format("year_%s_exam_%s_section_%s", gradeOneSubsection.getSchoolYears(), gradeOneSubsection.getExamName(), gradeOneSubsection.getSectionScore());

       // }
        return ResponseResult.page(0, "", (long) total, mapList);
    }


   /* public List<BksGradeOneSubsection> getResult(QueryWrapper<BksGradeOneSubsection> query, String year, String schoolName,
                                                 String examName, Integer page, Integer size) {
        query.eq("school_years", year);
        query.eq("school_name", schoolName);
        query.eq("exam_name", examName);
        if (page == null || page == 0) {
            page = 1;
        }
        int total = bksGradeOneSubsectionService.count(query);
        if (total < 0) {
            throw new IllegalArgumentException("一分一段数据不存在");
        }
        Page<BksGradeOneSubsection> pageQuery = new Page<>(page, size, total);
        IPage<BksGradeOneSubsection> gradeOneSubsections = bksGradeOneSubsectionService.page(pageQuery, query);

        return gradeOneSubsections.getRecords();
    }
*/
}
