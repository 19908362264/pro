package com.benwunet.bks.model.vo;

import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.model.BksQuestionChoiceness;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zfy
 * @date 2019/8/22
 */
@Data
@Accessors(chain = true)
public class OnlineVO implements Serializable {
    private static final long serialVersionUID = 2798476013654609219L;

    /**
     * 轮播
     */
    private List<BannerVO> bannerList;

    /**
     * 精选问题
     */
    private List<BksQuestionChoiceness> choiceness;

    /**
     * 高悬赏问题
     */
    private List<RewardVO> highPrices;

    /**
     * 问题列表
     */
    private List<QuestionVO> questions;

    /**
     * 推荐专家
     */
    private List<BksProfessor> professors;

    /**
     * 已有多少人找到答案
     */
    private Long num;
}
