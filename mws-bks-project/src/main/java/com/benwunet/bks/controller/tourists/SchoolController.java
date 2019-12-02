package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.vo.BksSchoolScoreVO;
import com.benwunet.bks.service.*;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大学Controller
 *
 * @author C
 * @date 2019/6/12 14:31
 */
@Slf4j
@RestController
@RequestMapping("/bks-anon/school")
public class SchoolController {

    @Autowired
    BksSchoolService bksSchoolService;

    @Autowired
    BksSceneryService sceneryService;

    @Autowired
    BksMajorService majorService;

    @Autowired
    private BksHighschoolService bksHighschoolService;

    @Autowired
    private BksSchoolScoreService bksSchoolScoreService;


    /**
     * 查询高校
     *
     * @author zhoux
     * @date 2019/11/22 14:49
     */
    @GetMapping("/queryCollege")
    public ResponseResult queryCollege(Integer f211, Integer f985, String provinceId, String campusId, Integer page, Integer limit) {
        Page<BksSchool> pageQuery = new Page<>(page, limit);
        QueryWrapper<BksSchool> query = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(f211)) {
            query.eq("f211", f211);
        }
        if (!ObjectUtils.isEmpty(f985)) {
            query.eq("f985", f985);
        }
        if (!ObjectUtils.isEmpty(provinceId)) {
            query.eq("province_id", provinceId);
        }
        if (!ObjectUtils.isEmpty(campusId)) {
            query.eq("campus_id", campusId);
        }
        query.orderByDesc("popularity");
        IPage<BksSchool> data = bksSchoolService.page(pageQuery, query);
        return new ResponseResult(0, "", (int) data.getTotal(), data.getRecords());
    }

    /**
     * 通过批次、省id查询高校
     *
     * @author zhoux
     * @date 2019/11/25 14:49
     */
    @PostMapping("/queryCollegeByBatchAndProvince")
    public ResponseResult queryCollegeByBatchAndProvince(@RequestBody BksSchoolScoreVO bksSchoolScoreVO) {
        QueryWrapper<BksSchoolScore> bksSchoolScoreQueryWrapper = new QueryWrapper<>();
        bksSchoolScoreQueryWrapper.eq("local_batch_name", bksSchoolScoreVO.getLocalBatchName());
        bksSchoolScoreQueryWrapper.eq("province", bksSchoolScoreVO.getProvince());
        int count = bksSchoolScoreService.count(bksSchoolScoreQueryWrapper);
        if(!ObjectUtils.isEmpty(bksSchoolScoreVO.getPageCurrent()) && !ObjectUtils.isEmpty(bksSchoolScoreVO.getPageSize())){
            bksSchoolScoreQueryWrapper.last(" limit " + (bksSchoolScoreVO.getPageCurrent() - 1) * bksSchoolScoreVO.getPageSize() + ","
                    + bksSchoolScoreVO.getPageSize());
        }
        List<BksSchoolScore> BksSchoolScoreList = bksSchoolScoreService.list(bksSchoolScoreQueryWrapper);
        List<BksSchoolScoreVO> BksSchoolScoreListVOList = new ArrayList<>();
        BksSchoolScoreList.forEach(x -> {
            BksSchoolScoreVO bksSchoolScoreVOForCopy = new BksSchoolScoreVO();
            BeanUtils.copyProperties(x, bksSchoolScoreVOForCopy);
            QueryWrapper<BksSchool> bksSchoolQueryWrapper = new QueryWrapper<>();
            bksSchoolQueryWrapper.eq("school_id", x.getSchoolId());
            BksSchool bksSchool = bksSchoolService.getOne(bksSchoolQueryWrapper);
            if (!ObjectUtils.isEmpty(bksSchool)) {
                bksSchoolScoreVOForCopy.setSchoolName(bksSchool.getSchoolName());
            }
            BksSchoolScoreListVOList.add(bksSchoolScoreVOForCopy);
        });
        return new ResponseResult(0, "", count, BksSchoolScoreListVOList);
    }

    /**
     * 通过学校id查询历年分数
     *
     * @author zhoux
     * @date 2019/11/25 14:49
     */
    @PostMapping("/queryScoreBySchoolId")
    public ResponseResult queryScoreBySchoolId(@RequestBody BksSchool bksSchool) {
        QueryWrapper<BksSchoolScore> bksSchoolScoreQueryWrapper = new QueryWrapper<>();
        bksSchoolScoreQueryWrapper.eq("school_id", bksSchool.getSchoolId());
        int count = bksSchoolScoreService.count(bksSchoolScoreQueryWrapper);
        List<BksSchoolScore> BksSchoolScoreList = bksSchoolScoreService.list(bksSchoolScoreQueryWrapper);
        return new ResponseResult(0, "", count, BksSchoolScoreList);
    }

    /**
     * 通过学校id批次年份类型查询历年分数
     *
     * @author zhoux
     * @date 2019/11/25 14:49
     */
    @PostMapping("/queryScoreBySchoolIdBatchYearType")
    public ResponseResult queryScoreBySchoolIdBatchYearType(@RequestBody BksSchoolScore bksSchoolScore) {
        QueryWrapper<BksSchoolScore> bksSchoolScoreQueryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(bksSchoolScore.getSchoolId())) {
            bksSchoolScoreQueryWrapper.eq("school_id", bksSchoolScore.getSchoolId());
        }
        if (!ObjectUtils.isEmpty(bksSchoolScore.getLocalBatchName())) {
            bksSchoolScoreQueryWrapper.eq("local_batch_name", bksSchoolScore.getLocalBatchName());
        }
        if (!ObjectUtils.isEmpty(bksSchoolScore.getYear())) {
            bksSchoolScoreQueryWrapper.eq("year", bksSchoolScore.getYear());
        }
        if (!ObjectUtils.isEmpty(bksSchoolScore.getType())) {
            bksSchoolScoreQueryWrapper.eq("type", bksSchoolScore.getType());
        }
        int count = bksSchoolScoreService.count(bksSchoolScoreQueryWrapper);
        List<BksSchoolScore> BksSchoolScoreList = bksSchoolScoreService.list(bksSchoolScoreQueryWrapper);
        return new ResponseResult(0, "", count, BksSchoolScoreList);
    }

    /**
     * 查询高校（按照人气值排名）
     *
     * @author C
     * @date 2019/6/13 14:49
     */
    @GetMapping("/listschoolbypopularity")
    public ResponseResult listSchoolByPopularity(Integer page, Integer limit, String text) {
        List<BksSchool> list = new ArrayList<>();
        if (text == null || text.equals("all")) {
            QueryWrapper<BksSchool> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("popularity");
            list = bksSchoolService.list(queryWrapper);
            if (list == null) {
                return ResponseResult.app(1, ResultCode.PT_ERROR, "查询高校信息失败", null);
            } else {
                list = bksSchoolService.listSchoolByPopularity();
            }
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (BksSchool school : list) {
            Map<String, Object> schoolMap = new HashMap<>();
            schoolMap.put("schoolName", school.getSchoolName());
            schoolMap.put("address", school.getAddress());
            schoolMap.put("collegesId", school.getCollegesId());
            schoolMap.put("popularity", school.getPopularity());
            maps.add(schoolMap);
        }
        return ResponseResult.app(0, ResultCode.PT_OK, "查询高校信息成功", maps);
    }


    /**
     * 查询高校专业（按照人气值排名）
     *
     * @author C
     * @date 2019/6/13 15:40
     */
    @GetMapping("/listMajorByRanking")
    public ResponseResult listMajorByRanking() {
        List<BksMajorCategory> list = bksSchoolService.listMajorByRanking();
        if (list == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询高校专业失败", null);
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (BksMajorCategory majorCategory : list) {
            Map<String, Object> majorMap = new HashMap<>();
            majorMap.put("majorCategoryName", majorCategory.getMajorCategoryName());
            majorMap.put("parentId", majorCategory.getParentId());
            majorMap.put("majorLevel", majorCategory.getMajorLevel());
            majorMap.put("majorCategoryHeatRanking", majorCategory.getMajorCategoryHeatRanking());
            maps.add(majorMap);
        }
        return ResponseResult.app(0, ResultCode.PT_OK, "查询高校信息成功", maps);
    }

    /**
     * 查询学校概况 接口
     *
     * @param schoolId
     * @author FC
     * @date 2019/6/19 18:47
     */
    @GetMapping("/getSchoolProfile")
    public ResponseResult getSchoolProfile(String schoolId) {
        QueryWrapper<BksSchool> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school_id", schoolId).select("school_profile");
        BksSchool profile = bksSchoolService.getOne(queryWrapper);
        if (profile == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询失败", null);
        } else {
            return ResponseResult.app(0, ResultCode.PT_OK, "查询成功", profile.getSchoolProfile());
        }

    }


    /**
     * 查询学校所开设专业 接口
     *
     * @param schoolId
     * @return ResponseResult -- list
     * @author FC
     */
    @GetMapping("/getSchoolMajors")
    public ResponseResult getSchoolMajors(String schoolId, String key) {
        if (key == null || key.equals("")) {
            key = "1";
        } else {
            key = "2";
        }
        List<BksMajor> list = majorService.list(new QueryWrapper<BksMajor>().eq("type", key).eq("school_id", schoolId).select("major_name", "major_id"));
        if (list != null) {
            List<Map<String, String>> majors = new ArrayList<>();
            for (BksMajor major : list) {
                Map<String, String> map = new HashMap<>();
                map.put("majorId", major.getMajorId());
                map.put("majorName", major.getMajorName());
                majors.add(map);
            }
            return ResponseResult.app(0, ResultCode.PT_OK, "查询成功", majors);
        } else {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询失败", null);
        }
    }

    /**
     * 查询学校风景图 接口
     *
     * @param schoolId
     * @author FC
     * @date 2019/6/19 19:12
     */
    @GetMapping("/getSchoolScenery")
    public ResponseResult getSchoolScenery(String schoolId) {

        List<BksScenery> list = sceneryService.list(new QueryWrapper<BksScenery>().eq("school_id", schoolId));
        List<Map<String, String>> pictures = new ArrayList<>();
        for (BksScenery scenery : list) {
            Map<String, String> map = new HashMap<>();
            map.put("name", scenery.getName());
            map.put("pic", scenery.getPic());
            pictures.add(map);
        }
        if (list == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "查询失败", null);
        } else {
            return ResponseResult.app(0, ResultCode.PT_OK, "查询成功", pictures);
        }
    }

    @GetMapping("/getHighSchool")
    public List<BksHighschool> getHighSchool(@RequestParam("provinceId") String provinceId, String cityId, String distinctId) {
        QueryWrapper<BksHighschool> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("province_id", provinceId);

        if (cityId != null && !cityId.equals("")) {
            queryWrapper.eq("city_id", cityId);
        }
        if (distinctId != null && !distinctId.equals("")) {
            queryWrapper.eq("district_id", distinctId);
        }

        List<BksHighschool> list = bksHighschoolService.list(queryWrapper);
        return list;

    }

}