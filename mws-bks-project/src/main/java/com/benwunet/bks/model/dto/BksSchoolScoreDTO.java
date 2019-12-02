package com.benwunet.bks.model.dto;

import lombok.Data;

@Data
public class BksSchoolScoreDTO {
    private String schoolId;
    private String schoolName;
    private String collegesName;
    private String campusName;
    private String provinceName;
    private String year;
    private String min;
    private String avg;
    private String batch;
    private String type;
    private String max;
    private String provinceScore;
    private String major;
    private String level;
    private String isAttention;

}
