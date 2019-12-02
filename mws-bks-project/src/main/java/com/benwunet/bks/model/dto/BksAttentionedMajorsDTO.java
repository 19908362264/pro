package com.benwunet.bks.model.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户中心查询关注的专业及详情接口 返回值接收类
 * @author FC
 * @date 2019/6/22 16:28
 */
@Data
public class BksAttentionedMajorsDTO implements Serializable {
    private static final long serialVersionUID = 5824235718444443685L;
    private String majorId;
    private String code;
    private String bachelorDegree;


    private String majorCategoryId;
    private String majorName;
    private String majorCategoryName;
    private String Level1Name;
    private String Level2Name;
    private String Level3Name;
    private String course;
    private String majorCategoryHeatRanking;
    private int universityCount;


}
