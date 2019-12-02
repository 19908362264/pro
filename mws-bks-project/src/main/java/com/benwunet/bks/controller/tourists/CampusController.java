package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.dto.BksProvinceSubjectCombDTO;
import com.benwunet.bks.service.*;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


/**
 *
 * @author FC
 * @date 2019/6/18 15:04
 */
@RestController
@RequestMapping("/bks-anon/campus")
@Api(value = "热度分析" ,tags = "热度分析")
public class CampusController {

    @Autowired
    private BksCourseCombinationService courseCombinationService;
    @Autowired
    private BksUserService userService;
    @Autowired
    private BksSubjectCombService subjectCombService;
    @Autowired
    private BksProvinceService provinceService;
    @Autowired
    private BksStudentTestscoreService testscoreService;
    @Autowired
    private BksExamRankingService rankingService;
    @Autowired
    private BksExamUploadService examUploadService;



    private static final String schoolScope = "SCHOOL";
    private static final String districtScope = "DISTRICT";
    private static final String provinceScope = "PROVINCE";
    private static final String blank = "";



    /**
     * 获取heat-specialty名
     * @author FC
     * @date 2019/6/27 14:12
     */
    @ApiOperation("获取heat-specialty名")
    @GetMapping("/getCombinations")
    public List<BksCourseCombination> getCombinations(){
        return courseCombinationService.list(
                new QueryWrapper<BksCourseCombination>().last("order by course_heat_ranking desc"));
    }

    /**
     * 获取学科组合热度 接口
     *
     * 要求学生信息表（bks_user），必须包含school_name（对应 bks_subject_comb 表的 school_name）,district_id（对应 bks_subject_comb 表的 district_name）
     * 整个 bks_subject_comb 表的数据加起来代表全重庆市的数据
     *
     * scope为常量值：SCHOOL（代表学校）,DISTRICT（代表区县）,PROVINCE（代表省市）
     * @author FC
     * @date 2019/6/27 15:54
     */
    @ApiOperation("获取学科组合热度")
    @GetMapping("/getSubjectCombs/{scope}")
    public ResponseResult getSubjectCombs(String userId, @PathVariable("scope") String scope,String year){
        //根据userId查询用户信息
        BksUser user = userService.getOne(new QueryWrapper<BksUser>().eq("user_id",userId));
        if (user == null){
            return ResponseResult.app(1, ResultCode.PT_ERROR,"用户信息不存在",null);
        }

        //获取学校名称和地区ID
        String schoolName = user.getSchoolName();
        String districtId = user.getDistrictId();

        //获取地区名称
        BksProvince district = provinceService.getOne(new QueryWrapper<BksProvince>()
                .eq("province_id",districtId)
                .select("province_name"));
        if (district == null){
            return ResponseResult.app(1, ResultCode.PT_ERROR,"地区信息不存在",null);
        }
        String districtName = district.getProvinceName();

        //如果前端没有传年份，就默认从数据库获取最新的年份
        if (year == null || blank.equals(year)){
            year = subjectCombService.getYear(districtName,schoolName);
        }

        System.out.println("=====schoolName:"+schoolName+"====="+"districtName:"+districtName+"====="+"year:"+year);

        //获取user的学科组合
        //通过前端传过来的user_id，获得学籍号student_code，用于查询（bks_student_testscore）学生的学科组合
        String studentCode = user.getStudentCode();
        String newSub;
        String before = "";
        //查询（bks_student_testscore），获得学生最新一条的学科组合
        BksStudentTestscore testscore = testscoreService.getStudetnScore(studentCode);

        if (testscore == null){
            //查询不到学生成绩（bks_student_testscore），返回给前端一个"--"代表没有查询到学生的学科组合
            newSub = "--";
        }else {
            before = testscore.getSubjectComb();
            newSub = convert(before);
        }


        //获取学生排名（bks_exam_ranking），数据库最新一条排名
        BksExamRanking examRanking = rankingService.getOne(new QueryWrapper<BksExamRanking>()
                .eq("student_id",studentCode)
                .orderByDesc("gmt_create")
                .last("limit 0,1"));
        Double gradeRanking;
        Double districtRanking;
        Double provinceRanking;
        if (examRanking == null){
            //查询不到学生排名，返回给前端的数据置为0
            gradeRanking = 0.0;
            districtRanking = 0.0;
            provinceRanking = 0.0;
        }else {
            gradeRanking = examRanking.getGradeRanking();
            districtRanking = examRanking.getDistrictRanking();
            provinceRanking = examRanking.getProvincesRanking();
        }


        //封装返回给前端的数据格式
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        map.put("studentSubComb",newSub);
        map.put("gradeRanking",gradeRanking);
        map.put("districtRanking",districtRanking);
        map.put("provinceRanking",provinceRanking);


        if (schoolScope.equals(scope)){
            //返回年级数据
            List<BksSubjectComb> list = getSchoolSubjectCombs(districtName,schoolName,year,before);
            if (list == null){
                //查询（bks_subject_comb），未找到该学校的数据
                return ResponseResult.app(1, ResultCode.PT_ERROR,"学校学科组合查询失败",null);
            }

            map.put("list",list);
            return ResponseResult.app(0,ResultCode.PT_OK,"查询成功",map);
        }else if (districtScope.equals(scope)){
            //返回区县数据
            List<BksProvinceSubjectCombDTO> list = getDistrictSubjectCombs(districtName,year,before);
            if (list == null){
                //查询（bks_subject_comb），未找到该区县的数据
                return ResponseResult.app(1, ResultCode.PT_ERROR,"区县学科组合查询失败",null);
            }

            map.put("list",list);
            return ResponseResult.app(0,ResultCode.PT_OK,"查询成功",map);
        }else if (provinceScope.equals(scope)){
            //返回全市数据
            List<BksProvinceSubjectCombDTO> list = getProvinceSubjectCombs(year,before);
            if (list == null){
                //查询（bks_subject_comb），未找到该省市的数据
                return ResponseResult.app(1, ResultCode.PT_ERROR,"省市学科组合查询失败",null);
            }
            map.put("list",list);
            return ResponseResult.app(0,ResultCode.PT_OK,"查询成功",map);
        }
        return ResponseResult.app(1, ResultCode.PT_ERROR,"查询scope参数错误",null);
    }

    /**
     * 查询全校学科组合 的数据
     * @author FC
     * @date 2019/6/29 9:33
     */
    public List<BksSubjectComb> getSchoolSubjectCombs(String districtName,String schoolName,String year,String before){
//        List<BksSubjectComb> list = subjectCombService.list(new QueryWrapper<BksSubjectComb>()
//                .eq("school_name",schoolName)
//                .eq("district_name",districtName)
//                .eq("school_year",year)
//                .orderByAsc("subject_comb"));
        List<BksSubjectComb> list = subjectCombService.getSchools(schoolName,districtName,year);





        List<BksSubjectComb> arr = new ArrayList<>();

        for (BksSubjectComb subjectComb : list) {

            //将学生的学科组合放到最前
            if (subjectComb.getSubjectComb().equals(before)){
                //将学科组合的数字转换为文字
                String sub = subjectComb.getSubjectComb();
                String subCom = convert(sub);
                subjectComb.setSubjectComb(subCom);
//                //设置招收人数为70%
//                subjectComb.setRecruit(Math.ceil(subjectComb.getTotalNum()*0.7));
                arr.add(subjectComb);
                break;
            }
        }
        for (BksSubjectComb subjectComb : list) {
            String after = convert(subjectComb.getSubjectComb());
            //如果是当前学生的学科组合 就退出本次循环
            if (subjectComb.getSubjectComb().equals(after)){
                continue;
            }
            //将学科组合的数字转换为文字
            String sub = subjectComb.getSubjectComb();
            String subCom = convert(sub);
            subjectComb.setSubjectComb(subCom);
//            //设置招收人数为70%
//            subjectComb.setRecruit(Math.ceil(subjectComb.getTotalNum()*0.7));
            arr.add(subjectComb);

        }
        System.out.println(list);

        return arr;
    }

    /**
     * 查询区县学科组合 的数据
     * @author FC
     * @date 2019/6/29 9:33
     */
    public List<BksProvinceSubjectCombDTO> getDistrictSubjectCombs(String districtName,String year,String before){
        List<BksProvinceSubjectCombDTO> list = subjectCombService.getDistrictSubjectCombs(districtName,year);
        return handle(list,before);
    }

    /**
     * 查询全重庆市学科组合 的数据
     * @author FC
     * @date 2019/6/29 9:34
     */
    public List<BksProvinceSubjectCombDTO> getProvinceSubjectCombs(String year,String before){
        List<BksProvinceSubjectCombDTO> list = subjectCombService.getProvinceSubjectCombs(year);

        return handle(list,before);
    }

    /**
     * 将数字对应的科目，转换为文字
     * @author FC
     * @date 2019/6/29 10:23
     */
    public String convert(String sub){
        if (sub == null){
            return "--";
        }
        String newSub = sub.replace("1","语文");
        sub = newSub.replace("2","数学");
        newSub = sub.replace("3","英语");
        sub = newSub.replace("4","政治");
        newSub = sub.replace("5","历史");
        sub = newSub.replace("6","地理");
        newSub = sub.replace("7","物理");
        sub = newSub.replace("8","化学");
        newSub = sub.replace("9","生物");
        return newSub;
    }

    /**
     * 将从数据库查到的数据进行处理
     * @author FC
     * @date 2019/7/4 12:46
     */
    public List<BksProvinceSubjectCombDTO> handle(List<BksProvinceSubjectCombDTO> list, String before){
        List<BksProvinceSubjectCombDTO> arr = new ArrayList<>();

        //这个循环目的是找到匹配学生的学科组合，然后放到集合第一个
        for (BksProvinceSubjectCombDTO subjectComb : list) {

            //将学生的学科组合放到最前
            if (subjectComb.getSubjectComb().equals(before)){
                System.out.println("=================subjectComb:"+subjectComb.getSubjectComb()+"before:"+before);
                //将学科组合的数字转换为文字
                String sub = subjectComb.getSubjectComb();
                String subCom = convert(sub);
                subjectComb.setSubjectComb(subCom);
//                //设置招收人数为70%
//                subjectComb.setRecruit(Math.ceil(Double.parseDouble(subjectComb.getTotal())*0.7));
                arr.add(subjectComb);
                break;
            }
        }
        System.out.println(arr);
        //这个循环是为了将除学生的学科组合以外的 学科组合依次放到集合里
        for (BksProvinceSubjectCombDTO subjectComb : list) {
            String after = convert(subjectComb.getSubjectComb());
            //如果是当前学生的学科组合 就退出本次循环
            if (subjectComb.getSubjectComb().equals(after)){
                continue;
            }
            //将学科组合的数字转换为文字
            String sub = subjectComb.getSubjectComb();
            String subCom = convert(sub);
            subjectComb.setSubjectComb(subCom);
//            //设置招收人数为70%
//            subjectComb.setRecruit(Math.ceil(Double.parseDouble(subjectComb.getTotal())*0.7));
            arr.add(subjectComb);

        }
        return arr;
    }

    //==================================================================================================================


    @GetMapping("/getUpload")
    public List<BksExamUpload> getUpload(String examName){
        List<BksExamUpload> list;
        if (examName == null){
            list = examUploadService.list();
        }else {
            list = examUploadService.list(new QueryWrapper<BksExamUpload>().like("exam_name",examName));
        }
        if (list.isEmpty()){
            return null;
        }
        for (BksExamUpload examUpload:list){
            System.out.println("======================="+examUpload);
            int sta =  examUpload.getStatus();
            if (sta == 1){
                examUpload.setSta("成功");
            }else {
                examUpload.setSta("失败");
            }
            LocalDateTime tim = examUpload.getGmtCreate();
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = tim.atZone(zoneId);
            Date date = Date.from(zdt.toInstant());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            examUpload.setGmt(sdf.format(date));
        }
        return list;
    }





}
