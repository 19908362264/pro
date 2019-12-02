package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksExamAvgAchievementDao;
import com.benwunet.bks.dao.BksStudentTestscoreDao;
import com.benwunet.bks.dao.TeacherDao;
import com.benwunet.bks.entity.dto.TeacherDTO;
import com.benwunet.bks.entity.homepage.*;
import com.benwunet.bks.feign.UserClient;
import com.benwunet.bks.model.*;
import com.benwunet.bks.service.BksExamUploadService;
import com.benwunet.bks.service.TeacherService;
import com.benwunet.mws.commons.utils.MobileUtil;
import com.benwunet.mws.commons.utils.RandomStringUtil;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.vo.QueryVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 * @author zfy
 * @date 2019/7/26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, BksUser> implements TeacherService {

    @Autowired
    private UserClient client;

    @Autowired
    private TeacherService service;

    @Autowired
    private BksExamUploadService uploadService;

    @Autowired
    private BksExamAvgAchievementDao avgDao;

    @Autowired
    private BksStudentTestscoreDao testscoreDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 教师列表
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @Override
    public PageResult getTeachers(QueryVO vo) {
        QueryWrapper<BksUser> query = new QueryWrapper();
        if (vo.getParam() != null && !"".equals(vo.getParam())) {
            query.like("student_name", vo.getParam());
        }
        if (vo.getSchoolName() != null && !"".equals(vo.getSchoolName())) {
            query.eq("school_name", vo.getSchoolName());
        }
        //角色为老师
        query.eq("role", 1);
        query.orderByDesc("gmt_modified");
        int total = service.count(query);
        Page<BksUser> pageQuery = new Page<>(vo.getPageCurrent(), vo.getPageSize(), total);
        pageQuery.setCurrent(vo.getPageCurrent());
        pageQuery.setSize(vo.getPageSize());
        IPage<BksUser> data = service.page(pageQuery, query);
        return new PageResult(data.getCurrent(), data.getSize(), total, data.getRecords());
    }

    /**
     * 新增教师
     *
     * @param dto 教师信息
     * @return ResponseResult
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult addTeacher(TeacherDTO dto) {
        //新增系统用户
        String userId = RandomStringUtil.getRandString(10);
        PubSysUser user = new PubSysUser();
        user.setUserId(userId);
        exchange(dto, user);
        client.addUser(user);
        //新增备考生用户
        if (!checkMobile(user.getMobile())) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "手机号已存在，请勿重复添加！", null);
        } else {
            BksUser bksUser = new BksUser();
            bksUser.setUserId(userId);
            bksUser.setStudentName(user.getUserName());
            bksUser.setRemark(user.getPost());
            //用户角色 1-老师
            bksUser.setRole(1);
            bksUser.setStatus(String.valueOf(dto.getIsUse()));
            //用户权限 0-管理人员 1-使用人员
            bksUser.setStudentCode(user.getUserType());
            bksUser.setSchoolId(dto.getSchoolId());
            bksUser.setSchoolName(dto.getSchoolName());
            bksUser.setMobile(dto.getMobile());
            return service.save(bksUser) ? ResponseResult.app(0, ResultCode.PT_OK, "新增教师成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "新增教师失败", null);
        }
    }

    /**
     * 修改教师
     *
     * @param dto 教师信息
     * @return ResponseResult
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult updateTeacher(TeacherDTO dto) {
        //修改系统类
        PubSysUser user = new PubSysUser();
        user.setUserId(dto.getUserId());
        exchange(dto, user);
        client.updateUser(user);
        //修改备考生用户
        BksUser bksUser = new BksUser();
        bksUser.setStudentName(user.getUserName());
        bksUser.setRemark(user.getPost());
        bksUser.setStatus(String.valueOf(dto.getIsUse()));
        //用户权限 0-管理人员 1-使用人员
        bksUser.setStudentCode(user.getUserType());
        bksUser.setSchoolId(dto.getSchoolId());
        bksUser.setSchoolName(dto.getSchoolName());
        bksUser.setMobile(dto.getMobile());

        QueryWrapper<BksUser> query = new QueryWrapper();
        query.eq("user_id", user.getUserId());
        return service.update(bksUser, query) ? ResponseResult.app(0, ResultCode.PT_OK, "修改教师成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "修改教师失败", null);
    }


    /**
     * 删除教师（可批量删除）
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult delTeacher(QueryVO vo) {
        client.delUser(vo);
        return service.removeByIds(vo.getIds()) ? ResponseResult.app(0, 0, "删除教师成功", null) : ResponseResult.app(1, 1, "删除教师失败", null);
    }

    /**
     * 获取教师详情
     *
     * @param id ID
     * @return ResponseResult
     */
    @Override
    public ResponseResult getTeacher(Integer id) {
        QueryWrapper<BksUser> query = new QueryWrapper();
        query.eq("id", id);
        BksUser bksUser = service.getOne(query);
        return bksUser != null ? ResponseResult.app(0, 0, "", bksUser) : ResponseResult.app(1, 1, "查询教师信息失败", null);
    }

    /**
     * 获取分段人数（学生分布）
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @Override
    public ResponseResult getScoreRange(QueryVO vo) {
        //默认该学校最新考试批次
        getNewBatch(vo);
        String key = String.format("schoolName_%s_examName_%s_schoolYears_%s_subject_%s_type_%s", vo.getSchoolName(), vo.getExamName(), vo.getSchoolYears(), vo.getSubjects(), vo.getSectionType());
        List<ScoreSubVO> scoreSubVO = (List<ScoreSubVO>) redisTemplate.opsForValue().get(key);
        if (scoreSubVO != null) {
            return new ResponseResult(0, 0, "", scoreSubVO);
        }
        List<BksGradeSubsection> list = testscoreDao.getSubsection(vo);
        if (list.isEmpty()) {
            return new ResponseResult(0, 1, "", null);
        }
        //各科参考人数
        Long num = list.stream().mapToLong(BksGradeSubsection::getSectionAccumulation).max().getAsLong();
        List<ScoreSubVO> scoreSubVOS = new ArrayList<>();
        list.forEach(b -> {
            ScoreSubVO vo1 = new ScoreSubVO();
            String typeScore = String.valueOf(Double.valueOf(b.getSectionScore()) - Double.valueOf(b.getSectionType())+1d);
            String sectionScore = b.getSectionScore().substring(0,b.getSectionScore().indexOf(".")) + "~" + typeScore.substring(0,typeScore.indexOf("."));
            String s = setNum(num == 0 ? 0 : b.getSectionNums() / (double) num);
            BeanUtils.copyProperties(b, vo1);
            vo1.setRate(s);
            vo1.setSectionScore(sectionScore);
            scoreSubVOS.add(vo1);
        });
        redisTemplate.opsForValue().set(key, scoreSubVOS, 5, TimeUnit.MINUTES);
        return new ResponseResult(0, 0, "", scoreSubVOS);
    }

    /**
     * 获取首页数据
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @Override
    public ResponseResult getHomePage(QueryVO vo) {
        //默认该学校最新考试批次
        getNewBatch(vo);

        String key = String.format("schoolName_%s_examName_%s_schoolYears_%s", vo.getSchoolName(), vo.getExamName(), vo.getSchoolYears());
        HomeData homeData = (HomeData) redisTemplate.opsForValue().get(key);

        if (homeData == null) {
            //基础信息
            BksExamAvgAchievement bksExamAvgAchievement = Optional.ofNullable(avgDao.getAchievement(vo)).orElse(new BksExamAvgAchievement());
            String referenceNum = String.valueOf(bksExamAvgAchievement.getGradePersons());
            String gradeAvg = String.valueOf(bksExamAvgAchievement.getGradeAvgAchievement());
            //历次平均成绩
            TimesAvg timesAvg = getTimesAvg(vo);
            //分数段人数
            List<DataVO> scoreRange = getScoreRangeData(vo);
            //学生成绩排名变化
            List<ScoreRanking> scoreRankings = testscoreDao.getStudentTotalRankList(vo);
            //各科成绩分析
            SubjectAnalyze subjectAnalyze = getSubjectAnalyze(vo, scoreRankings);

            HomeData data = HomeData.of()
                    .setSchoolYear(vo.getSchoolYears())
                    .setExamBatch(vo.getExamName())
                    .setReferenceNum(referenceNum)
                    .setGradeAvg(gradeAvg)
                    .setTimesAvg(timesAvg)
                    .setScoreRange(scoreRange)
                    .setScoreRanking(scoreRankings)
                    .setSubjectAnalyze(subjectAnalyze);
            redisTemplate.opsForValue().set(key, data, 5, TimeUnit.MINUTES);
            return new ResponseResult(0, 0, "", data);
        } else {
            return new ResponseResult(0, 0, "", homeData);
        }
    }

    /**
     * 批次列表
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @Override
    public PageResult getBatches(QueryVO vo) {
        QueryWrapper<BksExamUpload> query = new QueryWrapper();
        if (vo.getSchoolYears() != null && !"".equals(vo.getSchoolYears())) {
            query.eq("school_years", vo.getSchoolYears());
        }
        if (vo.getExamName() != null && !"".equals(vo.getExamName())) {
            query.eq("exam_name", vo.getExamName());
        }
        if (vo.getSchoolName() != null && !"".equals(vo.getSchoolName())) {
            query.eq("school_name", vo.getSchoolName());
        }

        query.orderByDesc("gmt_modified");
        int total = uploadService.count(query);
        Page<BksExamUpload> pageQuery = new Page<>(vo.getPageCurrent(), vo.getPageSize(), total);
        pageQuery.setCurrent(vo.getPageCurrent());
        pageQuery.setSize(vo.getPageSize());
        IPage<BksExamUpload> data = uploadService.page(pageQuery, query);
        return new PageResult(data.getCurrent(), data.getSize(), total, data.getRecords());
    }

    /**
     * 修改批次
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @Override
    public ResponseResult updateBatch(BksExamUpload vo) {
        return uploadService.updateById(vo) ? ResponseResult.app(0, ResultCode.PT_OK, "修改批次成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "修改批次失败", null);
    }

    /**
     * 批次作废
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @Override
    public ResponseResult delBatch(BksExamUpload vo) {
        return uploadService.updateById(vo) ? ResponseResult.app(0, ResultCode.PT_OK, "删除批次成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "删除批次失败", null);
    }

    @Override
    public ResponseResult getMobile(String mobile) {
        if (!MobileUtil.checkMobile(mobile)) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "请输入正确的手机号", null);
        } else {
            QueryWrapper<BksUser> query = new QueryWrapper();
            query.eq("mobile", mobile);
            return service.getOne(query) == null ? ResponseResult.app(0, ResultCode.PT_OK, "手机号可以使用", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "手机号以被使用，请重复使用注册！", null);
        }
    }

    /**
     * 各科成绩分析
     *
     * @param vo            请求参数
     * @param scoreRankings 总分成绩
     * @return SubjectAnalyze
     */
    @NotNull
    private SubjectAnalyze getSubjectAnalyze(QueryVO vo, List<ScoreRanking> scoreRankings) {
        BksExamAvgAchievement avgAchievements1 = testscoreDao.getAvgAchievement(vo);
        SubjectAnalyze subjectAnalyze = SubjectAnalyze.of();
        if (avgAchievements1 == null) {
            return subjectAnalyze;
        }
        List<BksStudentTestscore> testScores = testscoreDao.getTestScores(vo);
        BeanUtils.copyProperties(avgAchievements1, subjectAnalyze);
        //总分
        double low = avgAchievements1.getGradeAvgAchievement() - 20;
        long lowNum = scoreRankings.stream().filter(s -> s.getScore() < low).count();
        String lowRate = setNum(avgAchievements1.getGradePersons() == 0 ? 0 : lowNum / (double) avgAchievements1.getGradePersons());
        subjectAnalyze.setGradeLowPersons(lowNum).setGradeLowRate(lowRate);

        double excellent = avgAchievements1.getGradeAvgAchievement() + 20;
        long excellentNum = scoreRankings.stream().filter(s -> s.getScore() > excellent).count();
        String excellentRate = setNum(avgAchievements1.getGradePersons() == 0 ? 0 : excellentNum / (double) avgAchievements1.getGradePersons());
        subjectAnalyze.setGradeExcellentPersons(excellentNum).setGradeExcellentRate(excellentRate);

        long passNum = avgAchievements1.getGradePersons() - lowNum - excellentNum;
        String passRate = setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum / (double) avgAchievements1.getGradePersons());
        subjectAnalyze.setGradePassPersons(passNum).setGradePassRate(passRate);

        //语文
        double low1 = avgAchievements1.getGradeAvgAchievementYw() - 20;
        long lowNum1 = testScores.stream().filter(s -> s.getSubjectYuwen() < low1).count();
        subjectAnalyze.setGradeLowPersonYw(lowNum1).setGradeLowRateYw(setNum(avgAchievements1.getGradePersonYw() == 0 ? 0 : lowNum1 / (double) avgAchievements1.getGradePersonYw()));

        double excellent1 = avgAchievements1.getGradeAvgAchievementYw() + 20;
        long excellentNum1 = testScores.stream().filter(s -> s.getSubjectYuwen() > excellent1).count();
        subjectAnalyze.setGradeExcellentPersonYw(excellentNum1).setGradeExcellentRateYw(setNum(avgAchievements1.getGradePersonYw() == 0 ? 0 : excellentNum1 / (double) avgAchievements1.getGradePersonYw()));

        long passNum1 = avgAchievements1.getGradePersonYw() - lowNum1 - excellentNum1;
        subjectAnalyze.setGradePassPersonYw(passNum1).setGradePassRateYw(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum1 / (double) avgAchievements1.getGradePersons()));

        //数学
        double low2 = avgAchievements1.getGradeAvgAchievementSx() - 20;
        long lowNum2 = testScores.stream().filter(s -> s.getSubjectShuxue() < low2).count();
        subjectAnalyze.setGradeLowPersonSx(lowNum2).setGradeLowRateSx(setNum(avgAchievements1.getGradePersonSx() == 0 ? 0 : lowNum2 / (double) avgAchievements1.getGradePersonSx()));

        double excellent2 = avgAchievements1.getGradeAvgAchievementSx() + 20;
        long excellentNum2 = testScores.stream().filter(s -> s.getSubjectShuxue() > excellent2).count();
        subjectAnalyze.setGradeExcellentPersonSx(excellentNum2).setGradeExcellentRateSx(setNum(avgAchievements1.getGradePersonSx() == 0 ? 0 : excellentNum2 / (double) avgAchievements1.getGradePersonSx()));

        long passNum2 = avgAchievements1.getGradePersonSx() - lowNum2 - excellentNum2;
        subjectAnalyze.setGradePassPersonSx(passNum2).setGradePassRateSx(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum2 / (double) avgAchievements1.getGradePersons()));

        //英语
        double low3 = avgAchievements1.getGradeAvgAchievementYy() - 20;
        long lowNum3 = testScores.stream().filter(s -> s.getSubjectYingyu() < low3).count();
        subjectAnalyze.setGradeLowPersonYy(lowNum3).setGradeLowRateYy(setNum(avgAchievements1.getGradePersonYy() == 0 ? 0 : lowNum3 / (double) avgAchievements1.getGradePersonYy()));

        double excellent3 = avgAchievements1.getGradeAvgAchievementYy() + 20;
        long excellentNum3 = testScores.stream().filter(s -> s.getSubjectYingyu() > excellent3).count();
        subjectAnalyze.setGradeExcellentPersonYy(excellentNum3).setGradeExcellentRateYy(setNum(avgAchievements1.getGradePersonYy() == 0 ? 0 : excellentNum3 / (double) avgAchievements1.getGradePersonYy()));

        long passNum3 = avgAchievements1.getGradePersonYy() - lowNum3 - excellentNum3;
        subjectAnalyze.setGradePassPersonYy(passNum3).setGradePassRateYy(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum3 / (double) avgAchievements1.getGradePersons()));

        //物理
        Double gradeAvgAchievementWl = avgAchievements1.getGradeAvgAchievementWl();
        if (gradeAvgAchievementWl != null) {
            double low4 = gradeAvgAchievementWl - 20;
            long lowNum4 = testScores.stream().filter(s -> s.getSubjectWuli() < low4).count();
            subjectAnalyze.setGradeLowPersonWl(lowNum4).setGradeLowRateWl(setNum(avgAchievements1.getGradePersonWl() == 0 ? 0 : lowNum4 / (double) avgAchievements1.getGradePersonWl()));

            double excellent4 = gradeAvgAchievementWl + 20;
            long excellentNum4 = testScores.stream().filter(s -> s.getSubjectWuli() > excellent4).count();
            subjectAnalyze.setGradeExcellentPersonWl(excellentNum4).setGradeExcellentRateWl(setNum(avgAchievements1.getGradePersonWl() == 0 ? 0 : excellentNum4 / (double) avgAchievements1.getGradePersonWl()));

            long passNum4 = avgAchievements1.getGradePersonWl() - lowNum4 - excellentNum4;
            subjectAnalyze.setGradePassPersonWl(passNum4).setGradePassRateWl(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum4 / (double) avgAchievements1.getGradePersons()));
        } else {
            subjectAnalyze.setGradeLowPersonWl(0L).setGradeLowRateWl("0");
            subjectAnalyze.setGradeExcellentPersonWl(0L).setGradeExcellentRateWl("0");
            subjectAnalyze.setGradePassPersonWl(0L).setGradePassRateWl("0");
        }


        //化学
        Double gradeAvgAchievementHx = avgAchievements1.getGradeAvgAchievementHx();
        if (gradeAvgAchievementHx != null) {
            double low5 = gradeAvgAchievementHx - 20;
            long lowNum5 = testScores.stream().filter(s -> s.getSubjectHuaxueNew() < low5).count();
            subjectAnalyze.setGradeLowPersonHx(lowNum5).setGradeLowRateHx(setNum(avgAchievements1.getGradePersonHx() == 0 ? 0 : lowNum5 / (double) avgAchievements1.getGradePersonHx()));

            double excellent5 = gradeAvgAchievementHx + 20;
            long excellentNum5 = testScores.stream().filter(s -> s.getSubjectHuaxueNew() > excellent5).count();
            subjectAnalyze.setGradeExcellentPersonHx(excellentNum5).setGradeExcellentRateHx(setNum(avgAchievements1.getGradePersonHx() == 0 ? 0 : excellentNum5 / (double) avgAchievements1.getGradePersonHx()));

            long passNum5 = avgAchievements1.getGradePersonHx() - lowNum5 - excellentNum5;
            subjectAnalyze.setGradePassPersonHx(passNum5).setGradePassRateHx(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum5 / (double) avgAchievements1.getGradePersons()));
        } else {
            subjectAnalyze.setGradeLowPersonHx(0L).setGradeLowRateHx("0");
            subjectAnalyze.setGradeExcellentPersonHx(0L).setGradeExcellentRateHx("0");
            subjectAnalyze.setGradePassPersonHx(0L).setGradePassRateHx("0");
        }

        //生物
        Double gradeAvgAchievementSw = avgAchievements1.getGradeAvgAchievementSw();
        if (gradeAvgAchievementSw != null) {
            double low6 = gradeAvgAchievementSw - 20;
            long lowNum6 = testScores.stream().filter(s -> s.getSubjectShengwuNew() < low6).count();
            subjectAnalyze.setGradeLowPersonSw(lowNum6).setGradeLowRateSw(setNum(avgAchievements1.getGradePersonSw() == 0 ? 0 : lowNum6 / (double) avgAchievements1.getGradePersonSw()));

            double excellent6 = gradeAvgAchievementSw + 20;
            long excellentNum6 = testScores.stream().filter(s -> s.getSubjectShengwuNew() > excellent6).count();
            subjectAnalyze.setGradeExcellentPersonSw(excellentNum6).setGradeExcellentRateSw(setNum(avgAchievements1.getGradePersonSw() == 0 ? 0 : excellentNum6 / (double) avgAchievements1.getGradePersonSw()));

            long passNum6 = avgAchievements1.getGradePersonSw() - lowNum6 - excellentNum6;
            subjectAnalyze.setGradePassPersonSw(passNum6).setGradePassRateSw(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum6 / (double) avgAchievements1.getGradePersons()));
        } else {
            subjectAnalyze.setGradeLowPersonSw(0L).setGradeLowRateSw("0").setGradeAvgAchievementSw(0d).setGradeMinAchievementSw(0d).setGradePersonSw(0).setGradeMaxAchievementSw(0d);
            subjectAnalyze.setGradeExcellentPersonSw(0L).setGradeExcellentRateSw("0");
            subjectAnalyze.setGradePassPersonSw(0L).setGradePassRateSw("0");
        }

        //政治
        Double gradeAvgAchievementZz = avgAchievements1.getGradeAvgAchievementZz();
        if (gradeAvgAchievementZz != null) {
            double low7 = gradeAvgAchievementZz - 20;
            long lowNum7 = testScores.stream().filter(s -> s.getSubjectZhenzhiNew() < low7).count();
            subjectAnalyze.setGradeLowPersonZz(lowNum7).setGradeLowRateZz(setNum(avgAchievements1.getGradePersonZz() == 0 ? 0 : lowNum7 / (double) avgAchievements1.getGradePersonZz()));

            double excellent7 = gradeAvgAchievementZz + 20;
            long excellentNum7 = testScores.stream().filter(s -> s.getSubjectZhenzhiNew() > excellent7).count();
            subjectAnalyze.setGradeExcellentPersonZz(excellentNum7).setGradeExcellentRateZz(setNum(avgAchievements1.getGradePersonZz() == 0 ? 0 : excellentNum7 / (double) avgAchievements1.getGradePersonZz()));

            long passNum7 = avgAchievements1.getGradePersonZz() - lowNum7 - excellentNum7;
            subjectAnalyze.setGradePassPersonZz(passNum7).setGradePassRateZz(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum7 / (double) avgAchievements1.getGradePersons()));
        } else {
            subjectAnalyze.setGradeLowPersonZz(0L).setGradeLowRateZz("0");
            subjectAnalyze.setGradeExcellentPersonZz(0L).setGradeExcellentRateZz("0");
            subjectAnalyze.setGradePassPersonZz(0L).setGradePassRateZz("0");
        }

        //地理
        Double gradeAvgAchievementDl = avgAchievements1.getGradeAvgAchievementDl();
        if (gradeAvgAchievementDl != null) {
            double low8 = gradeAvgAchievementDl - 20;
            long lowNum8 = testScores.stream().filter(s -> s.getSubjectDiliNew() < low8).count();
            subjectAnalyze.setGradeLowPersonDl(lowNum8).setGradeLowRateDl(setNum(avgAchievements1.getGradePersonDl() == 0 ? 0 : lowNum8 / (double) avgAchievements1.getGradePersonDl()));

            double excellent8 = gradeAvgAchievementDl + 20;
            long excellentNum8 = testScores.stream().filter(s -> s.getSubjectDiliNew() > excellent8).count();
            subjectAnalyze.setGradeExcellentPersonDl(excellentNum8).setGradeExcellentRateDl(setNum(avgAchievements1.getGradePersonDl() == 0 ? 0 : excellentNum8 / (double) avgAchievements1.getGradePersonDl()));

            long passNum8 = avgAchievements1.getGradePersonDl() - lowNum8 - excellentNum8;
            subjectAnalyze.setGradePassPersonDl(passNum8).setGradePassRateDl(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum8 / (double) avgAchievements1.getGradePersons()));
        } else {
            subjectAnalyze.setGradeLowPersonDl(0L).setGradeLowRateDl("0").setGradeAvgAchievementDl(0d).setGradeMinAchievementDl(0d).setGradePersonDl(0).setGradeMaxAchievementDl(0d);
            subjectAnalyze.setGradeExcellentPersonDl(0L).setGradeExcellentRateDl("0");
            subjectAnalyze.setGradePassPersonDl(0L).setGradePassRateDl("0");
        }

        //历史
        Double gradeAvgAchievementLs = avgAchievements1.getGradeAvgAchievementLs();
        if (gradeAvgAchievementLs != null) {
            double low9 = gradeAvgAchievementLs - 20;
            long lowNum9 = testScores.stream().filter(s -> s.getSubjectLishi() < low9).count();
            subjectAnalyze.setGradeLowPersonLs(lowNum9).setGradeLowRateLs(setNum(avgAchievements1.getGradePersonLs() == 0 ? 0 : lowNum9 / (double) avgAchievements1.getGradePersonLs()));

            double excellent9 = gradeAvgAchievementLs + 20;
            long excellentNum9 = testScores.stream().filter(s -> s.getSubjectLishi() > excellent9).count();
            subjectAnalyze.setGradeExcellentPersonLs(excellentNum9).setGradeExcellentRateLs(setNum(avgAchievements1.getGradePersonLs() == 0 ? 0 : excellentNum9 / (double) avgAchievements1.getGradePersonLs()));

            long passNum9 = avgAchievements1.getGradePersonLs() - lowNum9 - excellentNum9;
            subjectAnalyze.setGradePassPersonLs(passNum9).setGradePassRateLs(setNum(avgAchievements1.getGradePersons() == 0 ? 0 : passNum9 / (double) avgAchievements1.getGradePersons()));
        } else {
            subjectAnalyze.setGradeLowPersonLs(0L).setGradeLowRateLs("0").setGradeAvgAchievementLs(0d).setGradeMinAchievementLs(0d).setGradePersonLs(0).setGradeMaxAchievementLs(0d);
            subjectAnalyze.setGradeExcellentPersonLs(0L).setGradeExcellentRateLs("0");
            subjectAnalyze.setGradePassPersonLs(0L).setGradePassRateLs("0");
        }
        return subjectAnalyze;
    }

    /**
     * 分数段人数
     *
     * @param vo 请求参数
     * @return List<DataVO>
     */
    @NotNull
    private List<DataVO> getScoreRangeData(QueryVO vo) {
        List<BksGradeSubsection> list = testscoreDao.getScoreRangeData(vo);
        List<DataVO> scoreRange = new ArrayList<>();
        for (BksGradeSubsection subsection : list) {
            DataVO vo4 = DataVO.of();
            String typeScore = String.valueOf(Double.valueOf(subsection.getSectionScore()) - Double.valueOf(subsection.getSectionType())+1d);
            String sectionScore = subsection.getSectionScore().substring(0,subsection.getSectionScore().indexOf(".")) + "~" + typeScore.substring(0,typeScore.indexOf("."));
           /* String sectionScore = Double.valueOf(subsection.getSectionScore()) + "~" + (Double.valueOf(subsection.getSectionScore()) - Double.valueOf(subsection.getSectionType())+1d);*/
            vo4.setName(sectionScore).setNum(String.valueOf(subsection.getSectionNums()));
            scoreRange.add(vo4);
        }
        return scoreRange;
    }

    /**
     * 历次平均成绩分布
     *
     * @param vo 请求参数
     * @return TimesAvg
     */
    private TimesAvg getTimesAvg(QueryVO vo) {
        List<BksExamAvgAchievement> avgAchievements = avgDao.getList(vo);
        List<DataVO> schoolAvg = new ArrayList<>();
        List<DataVO> countyAvg = new ArrayList<>();
        List<DataVO> cityAvg = new ArrayList<>();

        for (BksExamAvgAchievement achievement : avgAchievements) {
            //封装返回数据
            DataVO vo1 = DataVO.of();
            DataVO vo2 = DataVO.of();
            DataVO vo3 = DataVO.of();
            vo1.setName(achievement.getExamBatches()).setNum(String.valueOf(achievement.getGradeAvgAchievement()));
            vo2.setName(achievement.getExamBatches()).setNum(String.valueOf(achievement.getDistrictAvgAchievement()));
            vo3.setName(achievement.getExamBatches()).setNum(String.valueOf(achievement.getProvincesAvgAchievement()));
            schoolAvg.add(vo1);
            countyAvg.add(vo2);
            cityAvg.add(vo3);
        }
        return TimesAvg.of().setSchoolAvg(schoolAvg).setCityAvg(cityAvg).setCountyAvg(countyAvg);
    }

    /**
     * 手机号验证
     *
     * @param mobile 手机号
     * @return Boolean
     */
    private Boolean checkMobile(String mobile) {
        if (!MobileUtil.checkMobile(mobile)) {
            return false;
        } else {
            QueryWrapper<BksUser> query = new QueryWrapper();
            query.eq("mobile", mobile);
            BksUser user = service.getOne(query);
            return user == null;
        }
    }

    /**
     * 类转换
     *
     * @param dto  请求类
     * @param user 系统用户类
     */
    private void exchange(TeacherDTO dto, PubSysUser user) {
        user.setUserName(dto.getUserName());
        user.setPost(dto.getPost());
        user.setMobile(dto.getMobile());
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        user.setUserType(dto.getUserType());
        user.setIsUse(dto.getIsUse());
    }

    /**
     * 转换为百分比
     *
     * @param inNum 输入值
     * @return string
     */
    private String setNum(Double inNum) {
        NumberFormat format = NumberFormat.getPercentInstance();
        //设置保留几位小数
        format.setMaximumFractionDigits(2);
        return format.format(inNum);
    }

    /**
     * 默认该学校最新考试批次
     *
     * @param vo 请求参数
     */
    private void getNewBatch(QueryVO vo) {
        if (vo.getExamName() == null || "".equals(vo.getExamName())) {
            List<BksExamUpload> uploads = testscoreDao.getUpload(vo.getSchoolName());
            if (!uploads.isEmpty()){
                vo.setExamName(uploads.get(0).getExamName());
                vo.setSchoolYears(uploads.get(0).getSchoolYears());
            }
        }
    }
}
