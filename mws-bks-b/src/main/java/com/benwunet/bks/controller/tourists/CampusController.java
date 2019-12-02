package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.benwunet.bks.constants.Constants;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.dto.BksProvinceSubjectCombDTO;
import com.benwunet.bks.service.*;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@Api(value = "热度分析", tags = "热度分析")
public class CampusController {

    @Autowired
    private BksSubjectCombService subjectCombService;

    @Autowired
    private BksCourseCombinationService bksCourseCombinationService;

    @Autowired
    private  BksExamUploadService bksExamUploadService;



    @ApiOperation(value = "学科组合热度", notes = "学科组合热度")
    @GetMapping("/courseCombination")
    public ResponseResult courseCombination(@ApiParam(value = "1:学校；2：区县；3：省市", required = false) String key,
                                            @ApiParam(value = "考试批次", required = false) String examName,
                                            @ApiParam(value = "学校名称", required = false) String schoolName) {
        QueryWrapper<BksExamUpload> queryWrapper = new QueryWrapper<>();
        //排序默认
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.eq("school_name", schoolName);
        queryWrapper.eq("status", 1);
        List<BksExamUpload> list = bksExamUploadService.list(queryWrapper);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return ResponseResult.app(0, 0, "考试批次不存在", "");
        }
        if (examName == null || examName.equals("")) {
            examName = list.get(0).getExamName();
        }
        QueryWrapper<BksSubjectComb> query = new QueryWrapper<>();
        query.eq("school_name", schoolName);
        query.eq("school_year", examName);

        BksSubjectComb bksSubjectComb = subjectCombService.list(query).get(0);
        String districtName = bksSubjectComb.getDistrictName();

      //  queryWrapper.eq("school_year", examName);
        int i = 1;
        if (key == null || "".equals(key) || key.equals("1")) {
//            QueryWrapper<BksSubjectComb> query = new QueryWrapper<>();
//            query.eq("school_year", examName);
//            query.eq("school_name", schoolName);
            each(query, examName, i, mapList);

        } else if (key.equals("2")) {
            QueryWrapper<BksSubjectComb> query2 = new QueryWrapper<>();
            query2.eq("district_name", districtName);
            query2.eq("school_year", examName);
            each(query2, examName, i, mapList);

        } else if (key.equals("3")) {
            QueryWrapper<BksSubjectComb> query3 = new QueryWrapper<>();
            query3.eq("school_year", examName);
            each(query3, examName, i, mapList);
        }
        return ResponseResult.app(0, 0, "", mapList);
    }

    //每一种学科组合的数据封装出来
    private void each(QueryWrapper<BksSubjectComb> query, String examName, Integer i, List<Map<String, Object>> mapList) {
        List<BksCourseCombination> combinations = bksCourseCombinationService.list();
        List<String> collect = combinations.stream().map(BksCourseCombination::getCourseCombinationCode).collect(Collectors.toList());
        query.in("subject_comb", collect);
        List<BksSubjectComb> combList = subjectCombService.list(query);
        for (BksCourseCombination combination : combinations) {
            double totalNum = combList.stream().filter(a -> a.getSubjectComb().equals(combination.getCourseCombinationCode())).mapToDouble(BksSubjectComb::getTotalNum).sum();
            Map<String, Object> map = new HashMap<>(10);
            map.put("examName", examName);
            map.put("heat", totalNum * Constants.COMBNATION_HEAT);
            map.put("combinationName", combination.getCourseCombinationName());
            map.put("proportion", combination.getProportion());
            map.put("id", i++);
            mapList.add(map);
        }
    }

}