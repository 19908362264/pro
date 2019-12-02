package com.benwunet.bks.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zfy
 * @date 2019/8/27
 */
@Data
@Accessors(chain = true)
public class HomeVO implements Serializable {

    private static final long serialVersionUID = -3693416189804521381L;

    /**
     * 轮播图
     */
    private List<BannerVO> bannerList;

    /**
     * 推荐专家
     */
    private List<ProfessorVO> professorList;

     /**
     * 高悬赏问题
     */
    private List<RewardVO> rewardList;

    /**
     * 热门问答
     */
    private List<PopularVO> popularList;

}
