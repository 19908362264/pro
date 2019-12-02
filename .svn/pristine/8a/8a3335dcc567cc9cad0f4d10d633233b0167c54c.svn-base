package com.benwunet.bks.controller.tourists;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwunet.bks.model.BksExamSentimentAnalysis;
import com.benwunet.bks.service.BksExamSentimentAnalysisService;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 考情分析 前端控制器
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-27
 */
@RestController
@Api("考情分析")
@RequestMapping("/sentimentAnalysis")
public class BksExamSentimentAnalysisController {
    @Autowired
    BksExamSentimentAnalysisService sentimentAnalysisService;
    @GetMapping("/list")
    public ResponseResult listSentimentAnalysis(String subject, String examBatch, Integer page,Integer limit){
        Page<BksExamSentimentAnalysis> pageQuery=new Page<>(page,limit);
        QueryWrapper<BksExamSentimentAnalysis> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("exam_batch",examBatch);
        queryWrapper.eq("exam_subject",subject);
        queryWrapper.orderByAsc("remark");
        IPage<BksExamSentimentAnalysis> pages = sentimentAnalysisService.page(pageQuery, queryWrapper);
        return new ResponseResult(0,"",(int)pages.getTotal(),pages.getRecords());
    }

}

