package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BksStudentTestscore implements Serializable {

    private static final long serialVersionUID = -8385297608675251746L;
   // private Integer id;
    private String studentId;
    private String name;
    private String district;
    private String school;
    private String examName;
    private Double subjectYuwen;
    private Double subjectShuxue;
    private Double subjectYingyu;
    private Double subjectWuli;
    private Double subjectLishi;
    private Double subjectDili;
    private Double subjectDiliNew;
    private Double subjectHuaxue;
    private Double subjectHuaxueNew;
    private Double subjectShengwu;
    private Double subjectShengwuNew;
    private Double subjectZhenzhi;
    private Double subjectZhenzhiNew;
    private String subjectComb;
    private  String schoolYears;
    private String className;
    private  Double myScore;

}
