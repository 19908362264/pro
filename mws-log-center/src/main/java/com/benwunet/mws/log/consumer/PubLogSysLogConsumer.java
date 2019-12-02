package com.benwunet.mws.log.consumer;

import com.benwunet.mws.log.service.PubLogSysLogService;
import com.benwunet.mws.model.log.PubLogSysLog;
import com.benwunet.mws.model.log.constants.SysLogQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 从mq队列消费日志数据
 * @author xiangkaihong
 * @date 2019/4/27 11:31 *
 */
@Component
/**监听队列*/
@RabbitListener(queues = SysLogQueue.SYS_LOG_QUEUE)
public class PubLogSysLogConsumer {

	private static final Logger logger = LoggerFactory.getLogger(PubLogSysLogConsumer.class);

	@Autowired
	private PubLogSysLogService service;

	/**
	 * 处理消息
	 * @param pubLogSysLog
	 */
	@RabbitHandler
	public void logHandler(PubLogSysLog pubLogSysLog) {
		try {
			service.save(pubLogSysLog);

		} catch (Exception e) {
			logger.error("保存日志失败，日志：{}，异常：{}", pubLogSysLog, e);
		}

	}
}
