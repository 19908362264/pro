package com.benwunet.bks.controller.tourists;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.controller.base.BaseController;
import com.benwunet.bks.model.EnrollmentBatchControl;
import com.benwunet.bks.model.queryVO.ExamPredictionQueryVO;
import com.benwunet.bks.service.EnrollmentBatchControlService;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/enrollmentBatch")
@Api("考试情况")
public class EnrollmentBatchControlController extends BaseController {
    @Autowired
    EnrollmentBatchControlService batchControlService;

    @ApiOperation("上一批考试情况")
    @GetMapping("/lastExam")
    public ResponseResult lastExam(@RequestBody ExamPredictionQueryVO examPredictionQueryVo) {

//        String lastYear = batchControlService.getLastExamYear();
        QueryWrapper<EnrollmentBatchControl> query = new QueryWrapper<>();
        query.eq("enrollment_year", "2019")
                .eq("admitted", examPredictionQueryVo.getAdmitted())
                .eq("province_id", examPredictionQueryVo.getProvinceId());
        List<Map<String, Object>> maps = batchControlService.listMaps(query);
        for (Map<String, Object> map : maps) {
            if (examPredictionQueryVo.getBatch().equals(map.get("enrollment_batch"))) {
                map.put("score_difference", examPredictionQueryVo.getScore() - Double.parseDouble(map.get("enrollment_control_score").toString()));
            }
        }
        return new ResponseResult(0, 0, "", maps);
    }

    /**
     * @author zhoux
     * @since 2019-11-25
     */
    @PostMapping("/queryLastYearBatchAdmissionLine")
    @ApiOperation(value = "查询去年批次录取线", notes = "{查询去年分数录取线}")
    public ResponseResult queryLastYearBatchAdmissionLine(@RequestBody EnrollmentBatchControl enrollmentBatchControl) {
        QueryWrapper<EnrollmentBatchControl> enrollmentBatchControlQueryWrapper = new QueryWrapper<>();
        enrollmentBatchControlQueryWrapper.eq("province_id", enrollmentBatchControl.getProvinceId());
        enrollmentBatchControlQueryWrapper.eq("admitted", enrollmentBatchControl.getAdmitted());
        enrollmentBatchControlQueryWrapper.eq("enrollment_year", LocalDate.now().getYear() - 1);
        List<EnrollmentBatchControl> enrollmentBatchControlList = batchControlService.list(enrollmentBatchControlQueryWrapper);
        return success(batchControlService.count(enrollmentBatchControlQueryWrapper), enrollmentBatchControlList);
    }

}

