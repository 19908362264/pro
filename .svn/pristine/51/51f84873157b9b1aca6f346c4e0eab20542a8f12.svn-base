package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.constants.Constants;
import com.benwunet.bks.model.BksMajorCategory;
import com.benwunet.bks.model.BksUserSchoolMajor;
import com.benwunet.bks.model.dto.BksAttentionedMajorsDTO;
import com.benwunet.bks.model.dto.BksSchoolDTO;
import com.benwunet.bks.service.BksMajorCategoryService;
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
 * @date 2019/7/25 16:58
 */
@RestController
@RequestMapping
@Api(value = "专业热度", tags = "专业热度")
public class majorController {
    @Autowired
    private BksMajorCategoryService bksMajorCategoryService;
    @Autowired
    private BksUserSchoolMajorService bksUserSchoolMajorService;
    @Autowired
    private BksSchoolService bksSchoolService;




    /**
     * 查询专业热度
     *
     * @param
     * @return
     */
    @ApiOperation(value = "专业热度", notes = "专业热度")
    @GetMapping("/majorlHeat")
    public ResponseResult getmajorlHeat(@ApiParam(value = "当前页", required = true) Integer page,@ApiParam(value = "每页数", required = true) Integer size) {
        if (page == null || page == 0) {
            page = 1;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        List<BksAttentionedMajorsDTO> majorHeat = bksMajorCategoryService.getMajorHeat((page - 1) * size, size);
        int i = (page - 1) * size + 1;
        for (BksAttentionedMajorsDTO bksAttentionedMajorsDTO : majorHeat) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", i++);
            dataMap.put("major_category_id", bksAttentionedMajorsDTO.getMajorCategoryId());
            dataMap.put("major_category_name", bksAttentionedMajorsDTO.getMajorCategoryName());
            dataMap.put("level2_name", bksAttentionedMajorsDTO.getLevel2Name());
            dataMap.put("level1_name", bksAttentionedMajorsDTO.getLevel1Name());
            dataMap.put("major_category_heat_ranking", bksAttentionedMajorsDTO.getMajorCategoryHeatRanking());
            dataMap.put("majorClick", Constants.SCHOOL_CLICK);
            dataMap.put("lookClick", Constants.MAJOR_CLICK);
            list.add(dataMap);
        }
        int count = bksMajorCategoryService.count(new QueryWrapper<BksMajorCategory>().eq("parent_id", "2").orderByDesc("major_category_heat_ranking"));
        return ResponseResult.app(0, 0, String.valueOf(count), list);
    }


}
