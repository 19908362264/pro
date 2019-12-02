package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BksRemark implements Serializable {

    private static final long serialVersionUID = -5115006712232294469L;
    private Integer id;
    private Integer remark;
    private String title;
    private Integer type;

}
