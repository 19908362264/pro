package com.benwunet.bks.model.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.springframework.security.core.parameters.P;

import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/30 8:45
 */
@Data
public class BksProfessorStandardVo implements Serializable {

    /**
     * 专家id
     */
    private Integer professorId;

    /**
     * 1-图文 2-电话 3-视频 4-指定专家
     */
    private String type;

    /**
     * 费用
     */
    private Double typeCost;



}
