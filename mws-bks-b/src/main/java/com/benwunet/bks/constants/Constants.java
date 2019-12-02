package com.benwunet.bks.constants;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/15 10:34
 */
public interface Constants {
    /**
     * 热度值计算
     */
    Integer COMBNATION_HEAT = 113;
    /**
     * 专业热度点击跳转专业介绍
      */
    String  SCHOOL_CLICK ="http://10.10.0.34:8081/specialty_introduce?id=";
    /**
     * 专业热度点击跳转详情
     */
    String MAJOR_CLICK ="http://10.10.0.34:8081/specialty_info?id=";
    /**
     * 院校热度跳转院校介绍
     */
    String CLICK_SCHOOL="http://10.10.0.34:8081/school-info?id=";
    /**
     * 院校热度跳转开始专业
     */
    String CLICK_MAJOR ="http://10.10.0.34:8081/school-info?key=kszy&id=";
    /**
     * 语文
     */
    String SUBJECT_YW="1001";
    /**
     * 数学
     */
    String SUBJECT_SX="1002";
    /**
     * 英语
     */
    String SUBJECT_YY="1003";
    /**
     * 生物
     */
    String SUBJECT_SW="1004";
    /**
     * 地理
     */
    String SUBJECT_DL="1005";
    /**
     * 政治
     */
    String SUBJECT_ZZ="1006";
    /**
     * 化学
     */
    String SUBJECT_HX="1007";
    /**
     * 物理
     */
    String SUBJECT_WL="1008";
    /**
     * 历史
     */
    String SUBJECT_LS="1009";


}
