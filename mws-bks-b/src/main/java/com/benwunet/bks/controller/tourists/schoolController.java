package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.constants.Constants;
import com.benwunet.bks.model.BksSchool;
import com.benwunet.bks.model.BksUserSchoolMajor;
import com.benwunet.bks.model.dto.BksSchoolDTO;
import com.benwunet.bks.service.BksSchoolService;
import com.benwunet.bks.service.BksUserSchoolMajorService;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/7/25 17:00
 */
@RestController
@RequestMapping
@Api(value = "院校热度",tags = "院校热度")
public class schoolController {

    @Autowired
    private BksSchoolService bksSchoolService;
    @Autowired
    private BksUserSchoolMajorService bksUserSchoolMajorService;


    /**
     * 查询学校热度
     *
     * @param
     * @return
     */
    @ApiOperation(value = "院校热度",notes = "院校热度")
    @GetMapping("/schoolHeat")
    public ResponseResult getSchoolHeat(@ApiParam(value = "页数",required = true) Integer page,
                                        @ApiParam(value = "每页数量",required = true) Integer size) {
        if (page == null || page == 0) {
            page = 1;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        List<BksSchoolDTO> schoolHeat = bksSchoolService.getSchoolHeat((page - 1) * size,size);
        int i = (page - 1) * size + 1;
        for (BksSchoolDTO bksSchoolDTO : schoolHeat) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", i++);
            dataMap.put("school_id", bksSchoolDTO.getSchoolId());
            dataMap.put("school_name", bksSchoolDTO.getSchoolName());
            dataMap.put("province_name", bksSchoolDTO.getProvinceName());
            dataMap.put("campus_name", bksSchoolDTO.getCampusName());
            dataMap.put("popularity", bksSchoolDTO.getPopularity());
            dataMap.put("schoolclick", Constants.CLICK_SCHOOL);
            dataMap.put("majorclick",Constants.CLICK_MAJOR);
            list.add(dataMap);
        }
        int popularity = bksSchoolService.getSchoolHeatcount();
        return ResponseResult.app(0, 0, String.valueOf(popularity), list);
    }


}
