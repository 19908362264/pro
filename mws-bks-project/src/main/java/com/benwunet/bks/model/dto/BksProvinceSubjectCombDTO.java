package com.benwunet.bks.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户中心查询关注的学校及详情接口 返回值接收类
 * @author FC
 * @date 2019/6/22 13:46
 */
@Data
public class BksProvinceSubjectCombDTO implements Serializable {
    private static final long serialVersionUID = 7000550511787133430L;
    private String subjectComb;
    private String total;
    private Double recruit;

}
