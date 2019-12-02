package com.benwunet.bks.model.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @author zuoli
 */

@Data
public class BksSchoolDTO implements Serializable {
    private String schoolId;
    private String schoolName;
    private String collegesName;
    private String campusName;
    private String provinceName;
    private String majorName;
    private Integer total;
    private  Integer f211;
    private  Integer f985;
    private  Integer department;
    private  Integer admissions;
    private  Integer central;
    private  Integer isSeal;
    private String cityId;
    private String cityName;
    private String countyId;
    private String townName;
    private String postcode;
    private String logo;
    private String email;
    private String site;
    private String phone;
    private String schoolNatureName;
    private String popularity;
}
