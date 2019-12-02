package com.benwunet.bks.model.vo;

import com.benwunet.bks.model.BksSimulationBatch;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * <p>
 * 模拟填报批次表VO
 * </p>
 *
 * @author zhoux
 * @since 2019-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksSimulationBatchVO extends BksSimulationBatch {

    /**
     * 模拟填报大学表VO List
     */
    private List<BksSimulationSchoolVO> bksSimulationSchoolVOList;
}
