package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BksSchool implements Serializable {
    private static final long serialVersionUID = -6174980397792740665L;
    /** 学校编号ID */
    private Integer id;

    /** 学校代码 */
    private String schoolId;

    /** 学校名称 */
    private String schoolName;

    /** 省市区代码 */
    private String provinceId;

    private  int f211;
    private  int f985;
    private  int department;
    private  int admissions;
    private  int central;
    private  int is_seal;
    private String city_id;
    private String city_name;
    private String county_id;
    private String town_name;
    private String postcode;
    private String logo;
    private String email;
    private String site;
    private String phone;
    private String school_nature_name;

    /** 所在地 */
    private String address;

    /** 办学类型代码 */
    private String campusId;

    /** 高校类型代码 */
    private String collegesId;

    /** 全国热度排名 */
    private Integer nationwideHeatRanking;

    /** 类别热度排名 */
    private Integer categoryHeatRanking;

    /** 报考热度（人气值） */
    private Integer popularity;

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

    /** 学校概况 */
    private String schoolProfile;


}