package com.benwunet.bks.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zfy
 * @date 2019/7/30
 */
@Data
@ApiModel(value = "教师请求参数",description = "教师新增/修改请求参数")
public class TeacherDTO implements Serializable {

    private static final long serialVersionUID = 8643034265500558863L;

    /**
     * 用户代码
     */
    @ApiModelProperty(value = "用户代码，修改用户时必传")
    private String userId;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "姓名",required = true)
    private String userName;

    /**
     * 职务说明
     */
    @ApiModelProperty(value = "职务说明",required = true)
    private String post;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号",required = true)
    private String mobile;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码,修改时不传",required = true)
    private String password;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "角色权限 0-后台管理用户,1-b端使用者",required = true)
    private String userType;

    /**
     * 当前状态 0：否；1：是
     */
    @ApiModelProperty(value = "当前状态 0：否；1：是")
    private Boolean isUse;

    @ApiModelProperty(value = "学校Id",required = true)
    private String schoolId;

    @ApiModelProperty(value = "学校名称",required = true)
    private String schoolName;

}
