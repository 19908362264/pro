package com.benwunet.bks.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksSchool implements Serializable {

    private static final long serialVersionUID = -6174980397792740665L;

    /**
     * 学校编号ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学校代码
     */
    private String schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 省市区代码
     */
    private String provinceId;

    /**
     * 所在地
     */
    private String address;

    /**
     * 学校概况
     */
    private String schoolProfile;

    /**
     * 办学类型代码
     */
    private String campusId;

    /**
     * 高校类型代码
     */
    private String collegesId;

    /**
     * 1,肯定；2否定
     */
    private Integer f211;

    /**
     * 1,肯定；2否定
     */
    private Integer f985;

    /**
     * 1,肯定；2否定
     */
    private Integer department;

    /**
     * 1,肯定；2否定
     */
    private Integer admissions;

    /**
     * 1,肯定；2否定
     */
    private Integer central;

    /**
     * 1,肯定；2否定
     */
    private Integer isSeal;

    /**
     * 市代码
     */
    private String cityId;

    /**
     * 市名字
     */
    private String cityName;

    /**
     * 区代码
     */
    private String countyId;

    /**
     * 区名字
     */
    private String townName;

    /**
     * 学校代码
     */
    private String postcode;

    /**
     * 全国热度排名
     */
    private Integer nationwideHeatRanking;

    /**
     * 类别热度排名
     */
    private Integer categoryHeatRanking;

    /**
     * 学校logo
     */
    private String logo;

    /**
     * 报考热度（人气值）
     */
    private Integer popularity;

    /**
     * 学校邮箱
     */
    private String email;

    /**
     * 学校招办网址
     */
    private String site;

    /**
     * 学校联系电话
     */
    private String phone;

    /**
     * 公办
     */
    private String schoolNatureName;

    /**
     * 录入时间
     */
    private LocalDateTime gmtCreate;

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
     * These are the previous fields and I am not responsible
     */
    private int is_seal;
    private String city_id;
    private String city_name;
    private String county_id;
    private String town_name;
    private String school_nature_name;

}
