package com.benwunet.mws.commons.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  @author WangLin
 *  @Date 2019/4/28 16:27
 */
@Data
public class VerificationCode implements Serializable {
    private String mobile;
    private String code;
    private LocalDateTime time;

    public VerificationCode(String mobile, String code, LocalDateTime time) {
        this.mobile = mobile;
        this.code = code;
        this.time = time;
    }

}
