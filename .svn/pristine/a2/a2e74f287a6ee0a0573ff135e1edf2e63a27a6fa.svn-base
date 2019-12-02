package com.benwunet.mws.notification.service;

import com.benwunet.mws.model.notification.VerificationCode;

/**
 * 代码检验Service
 * @author xiangkaihong
 * @date 2019/5/12 11:05
 */

public interface VerificationCodeService {

	VerificationCode generateCode(String mobile);

	String matcheCodeAndGetMobile(String key, String code, Boolean delete, Integer second);
}
