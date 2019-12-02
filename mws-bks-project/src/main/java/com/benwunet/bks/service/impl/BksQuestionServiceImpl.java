package com.benwunet.bks.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksQuestionDao;
import com.benwunet.bks.feign.FileClient;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.dto.BksQuestionAnswerDTO;
import com.benwunet.bks.model.dto.BksQuestionDTO;
import com.benwunet.bks.model.vo.*;
import com.benwunet.bks.service.BksQuestionAnswerPraiseService;
import com.benwunet.bks.service.BksQuestionOrderService;
import com.benwunet.bks.service.BksQuestionService;
import com.benwunet.bks.util.OrderCodeFactory;
import com.benwunet.mws.model.file.PubFileInfo;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin.javascript.navig.Array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zfy
 * @date 2019/8/21
 */
@Service
public class BksQuestionServiceImpl extends ServiceImpl<BksQuestionDao, BksQuestion> implements BksQuestionService {

    @Autowired
    private BksQuestionDao questionDao;

    @Autowired
    private FileClient fileClient;

    @Autowired
    private BksQuestionOrderService orderService;

    @Autowired
    private BksQuestionAnswerPraiseService praiseService;

    @Override
    public ResponseResult getServiceTypes(QueryVO vo) {
        List<BksServiceType> list = questionDao.getServiceTypes(vo);
        return list == null ? ResponseResult.app(1, ResultCode.PT_ERROR, "1", null) : ResponseResult.app(0, ResultCode.PT_OK, "0", list);
    }

    @Override
    public PageResult getQuestions(QueryVO vo) {
        vo.setManners(Arrays.asList(1,3));
        List<QuestionVO> list = questionDao.getQuestions(vo);
        Long total = questionDao.getQuestionCount(vo);
        return new PageResult(vo.getPageCurrent(), vo.getPageSize(), total, list);
    }

    @Override
    public ResponseResult getQuestion(QueryVO vo) {
        QuestionInfoVO question = questionDao.getQuestion(vo.getQuestionId());
        List<String> questionPics = questionDao.getQuestionPics(vo.getQuestionId());
        List<AnswerVO> answers = questionDao.getAnswers(vo.getQuestionId());
        answers.forEach(b -> {
            List<String> answerPics = questionDao.getAnswerPics(b.getId());
            AnswerVO answer = questionDao.getAnswer(b.getUserId());
            //是否已点赞
            int num = questionDao.getPraise(vo.setAnswerId(b.getId()));
            b.setAcceptedNum(answer.getAcceptedNum()).setAnswerNum(answer.getAnswerNum()).setUrls(answerPics).setPraiseType(num > 0 ? 1 : 0);
        });
        question.setUrls(questionPics).setAnswers(answers);

        //查看详情后浏览量+1
        long num = question.getNum() + 1L;
        questionDao.updateNum(num, vo.getQuestionId());
        return ResponseResult.app(0, ResultCode.PT_OK, "获取数据成功", question);
    }

    @Override
    public ResponseResult addAnswer(BksQuestionAnswerDTO dto) {
        //同一个用户只能对同一个问题回答一次
        int num = questionDao.checkAnswer(dto);
        if (num > 0) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "你已回答过该问题，请勿重复回答!", null);
        }
        int result = questionDao.addAnswer(dto);
        //存图片
        List<String> urls = dto.getPicUrls();
        if (result > 0 && !urls.isEmpty()) {
            questionDao.addPic(null, dto.getId(), urls);
        }
        return result > 0 ? ResponseResult.app(0, ResultCode.PT_OK, "回答成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "回答失败", null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult addQuestion(BksQuestionDTO dto) {
        int result = questionDao.addQuestion(dto);
        //存图片
        List<String> urls = dto.getPicUrls();
        if (result > 0 && !urls.isEmpty()) {
            questionDao.addPic(dto.getId(), null, urls);
            //悬赏问题和指定专家时，生成订单
            if (dto.getManner() != 3) {
                //预约完成生成订单
                BksQuestionOrder order = new BksQuestionOrder();
                order.setOrderId(OrderCodeFactory.getOrderCode(Long.parseLong(dto.getUserId())));
                order.setQuestionId(String.valueOf(dto.getId()));
                order.setActualAmount(String.valueOf(dto.getMoney()));
                order.setUserId(dto.getUserId());
                order.setStatus(0);
                order.setType(dto.getManner());
                orderService.saveOrUpdate(order);
            }
        }
        return result > 0 ? ResponseResult.app(0, ResultCode.PT_OK, "提问成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "提问失败", null);
    }

    @Override
    public ResponseResult getQuestionList(QueryVO vo) {

        //轮播
        List<BannerVO> bannerList = questionDao.getBanners(vo.getTypeId());
        //精选问题
        List<BksQuestionChoiceness> list1 = questionDao.getEssential();
        //高悬赏问题
        List<RewardVO> list2 = questionDao.getHighPrice();
        //问题列表
        List<QuestionVO> list = questionDao.getQuestions(vo);
        //推荐专家
        List<BksProfessor> list3 = questionDao.getProfessors();
        Long num = questionDao.getAnswerNum();

        OnlineVO onlineVO = new OnlineVO();
        onlineVO.setBannerList(bannerList).setChoiceness(list1).setHighPrices(list2).setQuestions(list).setProfessors(list3).setNum(num);

        return ResponseResult.app(0, ResultCode.PT_OK, "获取数据成功", onlineVO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult updateLike(BksQuestionAnswerPraise dto) {
        //加入点赞
        praiseService.save(dto);
        //问题点赞数量+1
        long num = questionDao.getAnswerById(dto.getAnswerId());
        long num1 = num + 1L;
        int result = questionDao.updateLikeNum(num1, dto.getAnswerId());
        return result > 0 ? ResponseResult.app(0, ResultCode.PT_OK, "点赞成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "点赞失败", null);
    }

    @Override
    public ResponseResult getHome(QueryVO vo) {

        //轮播
        List<BannerVO> bannerList = questionDao.getBanners(vo.getTypeId());
        //推荐专家
        List<ProfessorVO> professorList = questionDao.getProfessorList();
        //高悬赏问题
        List<RewardVO> rewardList = questionDao.getHighPrice();
        //热门问答,相关问题
        List<PopularVO> popularList = questionDao.getPopulars(vo);

        HomeVO homeVO = new HomeVO();
        homeVO.setBannerList(bannerList).setPopularList(popularList).setProfessorList(professorList).setRewardList(rewardList);
        return ResponseResult.app(0, ResultCode.PT_OK, "获取数据成功", homeVO);
    }

    @Override
    public ResponseResult getRewards(QueryVO vo) {
        vo.setManners(Arrays.asList(1,3));
        List<RewardVO> rewardList = questionDao.getRewards(vo);
        return ResponseResult.app(0, ResultCode.PT_OK, "获取数据成功", rewardList);
    }

    @Override
    public ResponseResult uploadFile(MultipartFile[] file) {
        List<PubFileInfo> upload = fileClient.upload(file);
        List<String> urls = upload.stream().map(PubFileInfo::getFileNetUrl).collect(Collectors.toList());
        return ResponseResult.app(0, ResultCode.PT_OK, "上传成功", urls);
    }

    @Override
    public ResponseResult updateAccept(QueryVO vo) {
        String userId = questionDao.getQuestionUserId(vo.getAnswerId());
        if (!vo.getUserId().equals(userId)) {
            return ResponseResult.app(0, ResultCode.PT_ERROR, "只有提问者才能采纳答案", null);
        }
        int result = questionDao.updateAccept(vo.getAnswerId());
        //采纳答案后，问题状态更改为“已解决”
        questionDao.updateById(new BksQuestion().setId(vo.getQuestionId()).setStatus(1));
        return result > 0 ? ResponseResult.app(0, ResultCode.PT_OK, "采纳成功", null) : ResponseResult.app(1, ResultCode.PT_ERROR, "采纳失败", null);
    }

    @Override
    public PageResult getProfessors(QueryVO vo) {
        List<ProfessorVO> list = questionDao.getQuestionProfessors(vo);
        return list.isEmpty() ? new PageResult(vo.getPageCurrent(), vo.getPageSize(), 0, null) : new PageResult(vo.getPageCurrent(), vo.getPageSize(), list.size(), list) ;
    }
}
