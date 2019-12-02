package com.benwunet.mws.log.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.benwunet.mws.model.log.constants.SysLogQueue;

/**
 * rabbitmq配置
 * 
 * @author xiangkaihong
 * @data 2019-04-27 9:10
 *
 */
@Configuration
public class RabbitmqConfig {

	/**
	 * 声明队列
	 * 
	 * @return
	 */
	@Bean
	public Queue logQueue() {
		Queue queue = new Queue(SysLogQueue.SYS_LOG_QUEUE);

		return queue;
	}
}
