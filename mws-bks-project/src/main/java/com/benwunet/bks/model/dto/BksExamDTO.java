package com.benwunet.bks.model.dto;

import lombok.Data;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/22 16:17
 */
@Data
public class BksExamDTO {
    private String userExamId;
    //private String examId;
    private String examName;
    private String userScore ;
    private String courseName ;
    private Double subjectYuwen;
    private Double subjectShuxue;
    private Double subjectYingyu;
    private Double subjectWuli;
    private Double subjectLishi;
    private Double subjectDili;
    private Double subjectHuaxue;
    private Double subjectShengwu;
    private Double subjectZhenzhi;
    private String subjectComb;

}
