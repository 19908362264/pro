package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BksRemark implements Serializable {

    private static final long serialVersionUID = -5115006712232294469L;
    private Integer id;
    private int remark1;
    private int remark2;
    private int remark3;
    private String title;
    private String majorId;

}
