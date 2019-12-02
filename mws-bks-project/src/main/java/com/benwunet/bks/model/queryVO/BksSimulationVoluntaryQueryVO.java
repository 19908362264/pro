package com.benwunet.bks.model.queryVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 模拟填报志愿表QueryVO
 * </p>
 *
 * @author zhoux
 * @since 2019-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksSimulationVoluntaryQueryVO extends BaseQueryVO{

    /**
     * 用户id
     */
    private Integer userId;
}
