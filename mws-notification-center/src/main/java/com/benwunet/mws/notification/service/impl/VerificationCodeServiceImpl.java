package com.benwunet.mws.notification.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.benwunet.mws.model.notification.PubSmsNotification;
import com.benwunet.mws.model.notification.VerificationCode;
import com.benwunet.mws.notification.service.PubSmsNotificationService;
import com.benwunet.mws.notification.service.VerificationCodeService;
import com.benwunet.mws.notification.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代码检验实现类
 * @author xiangkaihong
 * @date 2019/5/12 11:13
 */

@Slf4j
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

	/**
	 * 短信验证码有效期（单位：分钟）
	 */
	@Value("${sms.expire-minute:15}")
	private Integer expireMinute;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private PubSmsNotificationService pubSmsNotificationService;

	@Transactional
	@Override
	public VerificationCode generateCode(String mobile) {
		String uuid = UUID.randomUUID().toString();
		String code = Util.randomCode(6);

		Map<String, String> map = new HashMap<>(2);
		map.put("code", code);
		map.put("mobile", mobile);

		stringRedisTemplate.opsForValue().set(smsRedisKey(uuid), JSONObject.toJSONString(map), expireMinute,
				TimeUnit.MINUTES);
		log.info("缓存验证码：{}", map);

		saveSmsAndSendCode(mobile, code);
		stringRedisTemplate.opsForValue().set("sms_" + mobile, mobile,60, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间

		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setKey(uuid);
		return verificationCode;
	}

	/**
	 * 保存短信记录，并发送短信
	 * @param mobile
	 * @param code
	 */
	private void saveSmsAndSendCode(String mobile, String code) {
		checkTodaySendCount(mobile);

		PubSmsNotification pubSmsNotification = new PubSmsNotification();
		pubSmsNotification.setMobile(mobile);

		Map<String, String> params = new HashMap<>();
		params.put("code", code);
		pubSmsNotificationService.saves(pubSmsNotification, params);

		pubSmsNotificationService.sendSmsMsg(pubSmsNotification);

		/**当天发送验证码次数+1*/
		String countKey = countKey(mobile);
		stringRedisTemplate.opsForValue().increment(countKey, 1L);
		stringRedisTemplate.expire(countKey, 1, TimeUnit.DAYS);
	}

	@Value("${sms.day-count:30}")
	private Integer dayCount;

	/**
	 * 获取当天发送验证码次数
	 * @param mobile
	 * @return
	 */
	private void checkTodaySendCount(String mobile) {
		String value = stringRedisTemplate.opsForValue().get(countKey(mobile));
		if (value != null) {
			Integer count = Integer.parseInt(value);
			if (count > dayCount) {
				throw new IllegalArgumentException("已超过当天最大次数");
			}
		}

	}

	private String countKey(String mobile) {
		return "sms:count:" + LocalDate.now().toString() + ":" + mobile;
	}

	/**
	 * redis中key加上前缀
	 *
	 * @param str
	 * @return
	 */
	private String smsRedisKey(String str) {

		return "sms:" + str;
	}

	@Override
	public String matcheCodeAndGetMobile(String key, String code, Boolean delete, Integer second) {
		key = smsRedisKey(key);

		String value = stringRedisTemplate.opsForValue().get(key);
		if (value != null) {
			JSONObject json = JSONObject.parseObject(value);
			if (code != null && code.equals(json.getString("code"))) {
				log.info("验证码校验成功：{}", value);

				if (delete == null || delete) {
					stringRedisTemplate.delete(key);
				}

				if (delete.equals(Boolean.FALSE) && second != null && second > 0) {
					stringRedisTemplate.expire(key, second, TimeUnit.SECONDS);
				}

				return json.getString("mobile");
			}

		}

		return null;
	}
}
