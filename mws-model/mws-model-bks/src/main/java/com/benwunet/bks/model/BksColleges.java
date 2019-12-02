package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BksColleges implements Serializable {
    private static final long serialVersionUID = 3634631446147037975L;
    /** 高校类型ID */
    private Integer id;

    /** 高校类型代码 */
    private String collegesId;

    /** 高校类型名称 */
    private String collegesName;

    /** 录入时间 */
    private Date gmtCreate;

    /** 修改时间 */
    private Date gmtModified;

    /** 操作员代码 */
    private String operatorId;

    /** 操作员姓名 */
    private String operatorName;

    /** 备注 */
    private String remark;


}