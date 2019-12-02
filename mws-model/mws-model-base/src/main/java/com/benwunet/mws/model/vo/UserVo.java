package com.benwunet.mws.model.vo;

import com.benwunet.mws.model.base.PubSysUser;
import lombok.Data;

@Data
public class UserVo extends PubSysUser {

    private Integer page;

    private Integer limit;
}
