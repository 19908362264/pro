package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/22 14:15
 */
@Data
public class BksUserExam implements Serializable {

    private static final long serialVersionUID = 6480515407570096563L;
    private Integer id;
    private String userId;
    private String examId;
    private String examName;
    private String userExamId;
    /**
     * 录入时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 操作员代码
     */
    private String operatorId;

    /**
     * 操作员姓名
     */
    private String operatorName;

    /**
     * 备注
     */
    private String remark;
}
