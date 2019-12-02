package com.benwunet.bks.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zfy
 * @date 2019/8/27
 */
@Data
@Accessors(chain = true)
public class ProfessorVO  implements Serializable {

    private static final long serialVersionUID = 4835790052903278072L;

    private Integer id;

    /**
     * 关联bks_user
     */
    private String userId;

    /**
     * 名称
     */
    private String professorName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 擅长领域
     */
    private String skilledField;

    /**
     * 状态 0-未认证 1-已认证
     */
    private String status;

    /**
     * 费用
     */
    private Double typeCost;

}
