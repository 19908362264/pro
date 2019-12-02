package com.benwunet.mws.model.vo;

import com.benwunet.mws.model.base.PubBaseDept;
import lombok.Data;


@Data
public class DeptQueryVo extends PubBaseDept {

    private Integer page;

    private Integer limit;

}
