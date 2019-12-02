package com.benwunet.bks.model.queryVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * BaseQueryVO
 * </p>
 *
 * @author zhoux
 * @since 2019-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseQueryVO {

    /**
     * 当前页码
     */

    private Long pageCurrent;

    /**
     * 每页数量
     */
    private Long pageSize;
}
