package com.benwunet.bks.model.dto;

import com.benwunet.bks.model.BksProfessorArea;
import com.benwunet.bks.model.BksProfessorServe;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/21 13:59
 */
@Data
public class BksProfessorDTO implements Serializable {
    private static final long serialVersionUID = 8538961690570615513L;
    private Integer id;

    /**
     * 关联bks_user
     */
    private String userId;

    /**
     * 名称
     */


    private String professorName;
    private String headImg;

    /**
     * 类型 1-特约专家 2-首席专家  3-明星专家 4-金牌专家
     */
    private Integer professorType;

    /**
     * 擅长领域
     */
    private String skilledField;

    /**
     * 背景简介
     */
    private String introduction;

    /**
     * 指导年数
     */
    private Integer guideYear;

    /**
     * 服务类别，多个类别用逗号“，”分割
     */
    private String serviceType;

    /**
     * 计费标准
     */
    private String chargingStandard;

    /**
     * 回答被采纳数
     */
    private Integer acceptNum;

    /**
     * 状态 0-未认证 1-已认证
     */
    private String status;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer operatorId;

    private String operatorName;

    private String remark;

    private Integer provinceId;
    private Integer serviceId;
    private String provinceName;

    /**
     * 服务类别列表
     */
    private List<BksProfessorServe> services;

    /**
     * 服务地区列表
     */
    private List<BksProfessorArea> provinces;




}
