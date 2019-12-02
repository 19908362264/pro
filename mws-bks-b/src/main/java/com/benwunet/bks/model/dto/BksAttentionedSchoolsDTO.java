package com.benwunet.bks.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户中心查询关注的学校及详情接口 返回值接收类
 * @author FC
 * @date 2019/6/22 13:46
 */
@Data
public class BksAttentionedSchoolsDTO implements Serializable {
    private static final long serialVersionUID = 1963452859012879429L;
    private String schoolName;
    private String schoolId;

    private String campusName;
    private String provinceName;
    private String year;
    private String average;
    private String min;
    private String majors;

}
