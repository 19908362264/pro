package com.benwunet.bks.model;


import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author C
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksProfessorArea implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 专家用户id
     */
    private String userId;

    /**
     * 省份id
     */
    private String provinceId;
    private String provinceName;


}
