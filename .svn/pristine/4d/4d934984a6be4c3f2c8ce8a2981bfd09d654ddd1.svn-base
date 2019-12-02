package com.benwunet.bks.model;


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
 * @author zuoli
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksQuestionBanner implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 图片地址
     */
    private String bannerUrl;

    /**
     * 图片详细地址
     */
    private String detailUrl;

    /**
     * 类别 0-首页 1-在线问答
     */
    private Boolean type;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String remark;


}
