package com.benwunet.mws.model.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表
 * @author xiangkaihong
 * @date 2019/4/26 15:21
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PubLogSysLog implements Serializable {

    private static final long serialVersionUID = -1770357395840712398L;
    /** id主键 */
    private Long id;
    /** 用户名 */
    private  String userName;
    /**模块名*/
    private  String moduleName;
    /**参数*/
    private  String params;
    /**标志*/
    private Boolean isFlag;
    /** 录入时间 */
    private Date gmtCreate;
    /** 备注 */
    private String remark;
}
