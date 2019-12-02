package com.benwunet.bks.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.service.BksProfessorService;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/professor")
public class BksProfessorController {
    @Autowired
    BksProfessorService professorService;
    @GetMapping("/list")
    @ApiOperation("专家信息展示")
    public ResponseResult professorList(@ApiParam(value = "专家姓名", example = "祝老师") @RequestParam(defaultValue = "") String professorName,
                                        @ApiParam(value = "页码", required = true, example = "1") @RequestParam Integer page,
                                        @ApiParam(value = "单页条数", required = true, example = "10") @RequestParam Integer limit){
        Page<BksProfessor> pageQuery = new Page<>(page,limit);

        QueryWrapper<BksProfessor> query = new QueryWrapper<>();
        query.like("professor_name",professorName);
        query.orderByDesc("gmt_create");

        IPage<BksProfessor> data = professorService.page(pageQuery, query);
        return new ResponseResult(0,"",(int)data.getTotal(), data.getRecords());
    }
}

