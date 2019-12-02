package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author C
 * @since 2019-07-01
 */
@Data
public class BksExamUpload implements Serializable {

private static final long serialVersionUID=1L;

    private Integer id;

    private String examName;

    private Integer rowCount;

    private Integer status;

    private String sta;

    /**
     * 学级
     */
    private String schoolYears;

    /**
     * 录入时间
     */
    private LocalDateTime gmtCreate;

    private String gmt;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

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

    /**
     * 是否为新高考
     */
    private String isNew;

    /**
     * 单位
     */
    private String schoolName;
}
