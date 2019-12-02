package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import com.benwunet.bks.model.dto.BksAttentionedSchoolsDTO;
import com.benwunet.bks.service.*;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户 接口
 *
 * @author
 * @date 2019/6/22 8:51
 */
@RestController
@RequestMapping("/bks/user")
public class UserController {

    @Autowired
    private BksUserSchoolMajorService bksUserSchoolMajorService;
    @Autowired
    private BksUserService userService;
    @Autowired
    private BksMajorService majorService;
    @Autowired
    private BksMajorCategoryService majorCategoryService;
    @Autowired
    private BksSchoolService schoolService;
    @Autowired
    private BksHighschoolService highschoolService;
    @Autowired
    private BksProvinceService bksProvinceService;



    /**
     * 添加关注 接口
     *
     * @return ResponseResult
     * @author FC
     * @date 2019/6/18 16:12
     */
    @PostMapping("/attention/{type}/{userId}/{msId}")
    public ResponseResult addAttention(@PathVariable("type") String type, @PathVariable("userId") String userId, @PathVariable("msId") String msId) {

        String msType = type.toLowerCase().trim();
        if ("m".equals(msType)) {
            return attentionMajor(userId, msId);
        } else if ("s".equals(msType)) {
            return attentionSchool(userId, msId);
        } else {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "关注类型参数错误", null);
        }

    }

    /**
     * 添加关注（专业） 接口
     *
     * @param userId
     * @param majorId
     * @return ResponseResult
     * @author FC
     * @date 2019/6/25 11:00
     */
    public ResponseResult attentionMajor(String userId, String majorId) {
        BksUserSchoolMajor userSchoolMajor = new BksUserSchoolMajor();
        userSchoolMajor.setUserId(userId);
        BksUserSchoolMajor isExist = bksUserSchoolMajorService.getOne(new QueryWrapper<BksUserSchoolMajor>()
                .eq("user_id", userId)
                .eq("major_id", majorId));
        if (isExist == null) {
            userSchoolMajor.setMajorId(majorId);
        } else {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "此专业，用户已关注", null);
        }
        boolean flag = bksUserSchoolMajorService.save(userSchoolMajor);
        if (flag == false) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "关注失败", null);
        }
        //关注成功后，为关注的专业的热度增加100
        return addMajorPopulation(majorId);

    }

    /**
     * 添加关注（学校） 接口
     *
     * @param userId
     * @param schoolId
     * @return ResponseResult
     * @author FC
     * @date 2019/6/25 11:00
     */
    @ApiOperation(value = "添加关注（学校） 接口",notes = "添加关注（学校） 接口")
    public ResponseResult attentionSchool(
            @ApiParam(value = "用户编号",required = true) String userId,
            @ApiParam(value = "学校编号",required = true) String schoolId) {
        BksUserSchoolMajor userSchoolMajor = new BksUserSchoolMajor();
        userSchoolMajor.setUserId(userId);
        BksUserSchoolMajor isExist = bksUserSchoolMajorService.getOne(new QueryWrapper<BksUserSchoolMajor>()
                .eq("user_id", userId)
                .eq("school_id", schoolId));
        if (isExist == null) {
            userSchoolMajor.setSchoolId(schoolId);
        } else {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "此学校用户已关注", null);
        }

        boolean flag = bksUserSchoolMajorService.save(userSchoolMajor);
        if (flag == false) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "关注失败", null);
        }
        //关注成功后，为关注的学校的热度增加100
        return addSchoolPopulation(schoolId);

    }

    /**
     * 为专业热度增加100 接口
     *
     * @param majorId
     * @return ResponseResult
     * @author FC
     * @date 2019/6/25 11:16
     */
    public ResponseResult addMajorPopulation(String majorId) {

//        //拿到majorId查询major_category_id
//        BksMajor major = majorService.getOne(new QueryWrapper<BksMajor>().eq("major_id", majorId).select("major_category_id"));
//        if (major == null) {
//            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询错误1", null);
//        }
//        String majorCategoryId = major.getMajorCategoryId();

        //为此专业热度增加100
        BksMajorCategory majorCategory = majorCategoryService.getOne(new QueryWrapper<BksMajorCategory>()
                .eq("major_category_id", majorId)
                .select("id", "major_category_heat_ranking"));
        if (majorCategory == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询错误2", null);
        }
        Integer majorCategoryHeatRanking = majorCategory.getMajorCategoryHeatRanking() + 100;

        majorCategory.setMajorCategoryHeatRanking(majorCategoryHeatRanking);

        boolean flag = majorCategoryService.updateById(majorCategory);
        if (flag == false) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "关注失败", null);
        }
        return ResponseResult.app(0, ResultCode.PT_OK, "关注成功", majorCategory);
    }


    /**
     * 为学校热度增加100 接口
     *
     * @param schoolId
     * @return ResponseResult
     * @author FC
     * @date 2019/6/25 11:16
     */
    public ResponseResult addSchoolPopulation(String schoolId) {

        //为此学校热度增加100
        BksSchool school = schoolService.getOne(new QueryWrapper<BksSchool>()
                .eq("school_id", schoolId)
                .select("id", "popularity"));
        if (school == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询错误", null);
        }
        Integer popularity = school.getPopularity() + 100;
        school.setPopularity(popularity);

        boolean flag = schoolService.updateById(school);
        if (flag == false) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "关注失败", null);
        }
        return ResponseResult.app(0, ResultCode.PT_OK, "关注成功", school);
    }

    /**
     * 取消关注 接口
     * @return ResponseResult
     * @author FC
     * @date 2019/6/18 16:12
     */
    @DeleteMapping("/attention/{type}/{userId}/{msId}")
    public ResponseResult deleteAttention(@PathVariable("type") String type, @PathVariable("userId") String userId, @PathVariable("msId") String msId) {

        Map<String, Object> columnMap = new HashMap<>(2);
        columnMap.put("user_id", userId);
        String msType = type.toLowerCase().trim();
        if ("m".equals(msType)) {
            columnMap.put("major_id", msId);
        } else if ("s".equals(msType)) {
            columnMap.put("school_id", msId);
        } else {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "关注类型参数错误", null);
        }

        boolean flag = bksUserSchoolMajorService.removeByMap(columnMap);
        if (flag == false) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "取消关注失败", null);
        }
        return ResponseResult.app(0, ResultCode.PT_OK, "关注已取消", columnMap);
    }

    /**
     * 查询关注 接口
     *
     * @return ResponseResult
     * @author FC
     * @date 2019/6/18 16:12
     */
    @GetMapping("/attention/{type}/{userId}")
    public ResponseResult queryAttention(@PathVariable("type") String type, @PathVariable("userId") String userId,Integer page) {
        List<BksUserSchoolMajor> list = bksUserSchoolMajorService.queryAttention(type, userId,(page-1)*10);
        if (list == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询关注失败", null);
        }
        return ResponseResult.app(0, ResultCode.PT_OK, "查询关注成功", list);
    }


    /**
     * 用户中心查询关注（关注的学校或专业及详情） 接口
     *
     * @param userId
     * @param type
     * @return ResponseResult
     * @date 2019/6/22 10:24
     */
    @GetMapping("/attention/{userId}")
    public ResponseResult getAttentions(@PathVariable("userId") String userId, String type,Integer page,String province){
        if (page <= 0){
            page = 1;
        }
        //查询用户信息，预处理 查询类型
        String msType = type.toLowerCase().trim();
        BksUser user = userService.getOne(new QueryWrapper<BksUser>().eq("user_id", userId));
        if (user == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "用户信息不存在", null);
        }
        //把从前端得到的省名称转换为省ID，即:provinceId
        String provinceId;
        if (province == null || "".equals(province)){
            //如果前端传过来的为空，默认查重庆的
            province = "重庆";
        }
        BksProvince bksProvince = bksProvinceService.getOne(new QueryWrapper<BksProvince>().eq("province_name",province));
        if (bksProvince == null){
            //如果前端传过来的数据，查询不到，就默认为重庆
            provinceId = "50";
        }else {
            provinceId = bksProvince.getProvinceId();
        }

        //创建封装前端需要的数据格式的容器
        Map<String,Object> map = new HashMap<>(3);

        //根据查询类型参数，做出不同的查询
        if ("m".equals(msType)) {

            //查询该用户关注的专业的 ID列表，这里只获得了关注专业的ID
            List<BksUserSchoolMajor> majorPage = bksUserSchoolMajorService.queryAttention(type, userId, (page-1)*10);
            System.out.println("============"+majorPage);

            //统计该用户关注的专业的 总数量
            Integer total = userService.getSchoolMajorTotal(type, userId);

            List<BksAttentionedMajorsDTO> attentionedMajors = new ArrayList<>();
            for (BksUserSchoolMajor sm : majorPage) {
                String majorCategoryId = sm.getMajorId();

                //获得ID列表后，连表查询专业的综合信息，结果放到DTO
                BksAttentionedMajorsDTO attentionedMajor = userService.getAttentionedMajorDetails(majorCategoryId);

                //统计开设该专业的 学校的数量
                Integer countUniversity = userService.countUniversity(attentionedMajor.getMajorCategoryId());

                System.out.println("================="+"countUniversity"+countUniversity+"=================");
                attentionedMajor.setUniversityCount(countUniversity);
                attentionedMajors.add(attentionedMajor);
            }
            //封装前端需要的数据格式
            map.clear();
            map.put("total",total);
            map.put("page",page);
            map.put("data",attentionedMajors);
            return ResponseResult.app(0, ResultCode.PT_OK, "查询成功", map);

        } else if ("s".equals(msType)) {
            //查询关注的学校 ID列表，这里只获得了关注学校的ID
            List<BksUserSchoolMajor> list = bksUserSchoolMajorService.queryAttention(type, userId,(page-1)*10);

            //统计该用户关注的学校的 总数量
            Integer total = userService.getSchoolMajorTotal(type, userId);

            List<BksAttentionedSchoolsDTO> attentionedSchools = new ArrayList<>();
            for (BksUserSchoolMajor sm : list) {
                String schoolId = sm.getSchoolId();

                //获得ID列表后，连表查询学校的综合信息，结果放到DTO
                //连表查询，主要是获得学校的信息
                BksAttentionedSchoolsDTO attentionedSchool = userService.getAttentionedSchoolDetails(schoolId);
                //如果为空，代表链表查询失败，数据不完整，不做展示
                if (attentionedSchool == null){
                    continue;
                }
                //查询该学校在考生所在省份的分数线，获得最新一年的数据
                BksAttentionedSchoolsDTO schoolScore = userService.getSchoolScore(schoolId,provinceId);
                if (schoolScore != null){
                    attentionedSchool.setYear(schoolScore.getYear());
                    attentionedSchool.setAverage(schoolScore.getAverage());
                    attentionedSchool.setMin(schoolScore.getMin());
                }else{
                    attentionedSchool.setYear("--");
                    attentionedSchool.setAverage("--");
                    attentionedSchool.setMin("--");
                }

            //查询该学校所开设的所有专业
                List<BksMajor> majors = majorService.list(new QueryWrapper<BksMajor>().eq("school_id", schoolId).select("major_name"));
                //majors小于3的情况
                //将该学校所开设的专业的前三个组合为一个字符串，放到DTO的majors字段中
                for (int i = 0; i < 3; i++) {
                    String str = majors.get(i).getMajorName();
                    String str1 = attentionedSchool.getMajors();
                    if (str1 == null) {
                        str1 = "";
                    }
                    str1 += (str + " ");
                    attentionedSchool.setMajors(str1);
                }
                attentionedSchools.add(attentionedSchool);
            }

            //封装前端需要的数据格式
            map.clear();
            map.put("total",total);
            map.put("page",page);
            map.put("data",attentionedSchools);
            return ResponseResult.app(0, ResultCode.PT_OK, "查询成功", map);

        }
        return ResponseResult.app(1, ResultCode.PT_ERROR, "关注类型参数错误", null);


    }


    /**
     * 用户中心个人信息查询
     *
     * @param userId
     * @return ResponseResult
     * @date 2019/6/25 10:24
     */
    @GetMapping("/getone")
    public ResponseResult getUseInfo(String userId) {
        QueryWrapper<BksUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        BksUser one = userService.getOne(queryWrapper);
        if (one == null) {
            throw new IllegalArgumentException("该用户不存在");
        }
        QueryWrapper<BksHighschool> query = new QueryWrapper<>();
        query.eq("school_id", one.getSchoolId());
        BksHighschool highschool = highschoolService.getOne(query);
        Map<String, Object> map = new HashMap<>();
        map.put("headImg", one.getHeadImg());
        map.put("sex", one.getSex());
        map.put("nickName", one.getNickName());
        map.put("mobile", one.getMobile());
        map.put("provinceId", one.getProvincesId());
        map.put("city_id", one.getCityId());
        map.put("district_id", one.getDistrictId());
        map.put("birthday", one.getBirthday() != null ? new SimpleDateFormat("yyyy-MM-dd").format(one.getBirthday()) : "");
        map.put("school", highschool != null ? highschool.getSchoolName() : "");
        map.put("userName", one.getStudentName());
        map.put("studentCode", one.getStudentCode());
        return ResponseResult.app(0, 0, "", map);
    }


    @GetMapping("/province")
    public ResponseResult getProvince() {
        QueryWrapper<BksProvince> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", "0");
        List<BksProvince> list = bksProvinceService.list(queryWrapper);
        return ResponseResult.app(0, 0, "", list);
    }

   // private final String server = "http://218.70.169.6:8082/tg/";
    private final String server = "http://218.70.169.6:8082/tg/";

    @PostMapping("/puthead/{id}")
    public ResponseResult putHeadImg(MultipartFile file, @PathVariable String id) throws IOException {
        String path = "D://localFile//";
        String name = UUID.randomUUID().toString().replace("-","") + ".png";
        File local = new File(path, name);
        file.transferTo(local);
        Map<String, Object> data = new HashMap<>();

        QueryWrapper<BksUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        BksUser one = userService.getOne(queryWrapper);
        if (one == null) {
            return ResponseResult.app(1, 1, "用户不存在", "");
        }
        one.setHeadImg(server+name);
        userService.saveOrUpdate(one);
        return ResponseResult.app(0, 0, "头像修改成功", "");
    }

}
