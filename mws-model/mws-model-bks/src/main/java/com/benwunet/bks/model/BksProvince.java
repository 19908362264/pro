package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BksProvince implements Serializable {
    private static final long serialVersionUID = -8917928944940688467L;
    /** 省市区ID */
    private Integer id;

    /** 省市区代码 */
    private String provinceId;

    /** 省市区上级代码，0：父节点（省） */
    private String parentId;

    /** 省市区名称 */
    private String provinceName;



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