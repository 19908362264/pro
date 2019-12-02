package com.benwunet.bks.controller.consult;

import com.benwunet.bks.model.BksQuestionAnswerPraise;
import com.benwunet.bks.model.dto.BksQuestionAnswerDTO;
import com.benwunet.bks.model.dto.BksQuestionDTO;
import com.benwunet.bks.model.vo.QueryVO;
import com.benwunet.bks.service.BksQuestionService;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zfy
 * @date 2019/8/21
 */
@RestController
@Api(value = "备考生专家咨询", tags = "问题相关")
public class QuestionController {

    @Autowired
    private BksQuestionService service;

    /**
     * 服务列表
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @PostMapping("/bks-anon/types")
    @ApiOperation(value = "服务列表", notes = "服务列表")
    public ResponseResult getServiceTypes(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getServiceTypes(vo);
    }

    /**
     * 在线问答
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @PostMapping("/bks-anon/question-list")
    @ApiOperation(value = "在线问答", notes = "在线问答")
    public ResponseResult getQuestionList(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getQuestionList(vo);
    }

    /**
     * 问题列表
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @PostMapping("/bks-anon/questions")
    @ApiOperation(value = "问题列表", notes = "问题列表")
    public PageResult getQuestions(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getQuestions(vo);
    }


    /**
     * 获取问题详情
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @PostMapping("/bks-anon/question-info")
    @ApiOperation(value = "获取问题详情", notes = "获取问题详情")
    public ResponseResult getQuestion(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getQuestion(vo);
    }

    /**
     * 回答问题
     *
     * @param dto 请求参数
     * @return ResponseResult
     */
    @PostMapping("/answer")
    @ApiOperation(value = "回答问题", notes = "回答问题")
    public ResponseResult addAnswer(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody BksQuestionAnswerDTO dto) {
        return service.addAnswer(dto);
    }

    /**
     * 提问
     *
     * @param dto 请求参数
     * @return ResponseResult
     */
    @PostMapping("/question")
    @ApiOperation(value = "提问", notes = "提问")
    public ResponseResult addQuestion(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody BksQuestionDTO dto) {
        return service.addQuestion(dto);
    }

    /**
     * 点赞
     *
     * @param dto 点赞
     * @return ResponseResult
     */
    @PostMapping("/praise")
    @ApiOperation(value = "点赞", notes = "点赞")
    public ResponseResult updateLike(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody BksQuestionAnswerPraise dto) {
        return service.updateLike(dto);
    }

    /**
     * 采纳答案
     * 仅提问者可以采纳答案
     *
     * @param vo 答案主键和用户id
     * @return ResponseResult
     */
    @PutMapping("/accept")
    @ApiOperation(value = "采纳答案", notes = "采纳答案")
    public ResponseResult updateAccept(@RequestBody QueryVO vo) {
        return service.updateAccept(vo);
    }

    /**
     * 首页数据
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @PostMapping("/bks-anon/home")
    @ApiOperation(value = "首页数据", notes = "首页数据")
    public ResponseResult getHome(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getHome(vo);
    }

    /**
     * 高悬赏
     *
     * @param vo 请求参数
     * @return ResponseResult
     */
    @PostMapping("/bks-anon/reward")
    @ApiOperation(value = "高悬赏问题", notes = "高悬赏问题")
    public ResponseResult getRewards(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getRewards(vo);
    }

    /**
     * 上传文件
     *
     * @param file 请求参数
     * @return ResponseResult
     */
    @PostMapping("/bks-anon/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
    public ResponseResult uploadFile(@RequestPart(value = "files") MultipartFile[] file) {
        return service.uploadFile(file);
    }

    /**
     * 提问中专家列表
     *
     * @param vo 请求参数
     * @return PageResult
     */
    @PostMapping("/bks-anon/question-professors")
    @ApiOperation(value = "提问中专家列表", notes = "提问中专家列表")
    public PageResult getProfessors(@ApiParam(name = "请求参数对象", value = "传入json格式", required = true) @RequestBody QueryVO vo) {
        return service.getProfessors(vo);
    }


}
