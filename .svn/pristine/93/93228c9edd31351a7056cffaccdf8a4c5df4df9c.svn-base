package com.benwunet.mws.demo.config;

import com.benwunet.mws.model.base.constants.UserCenterMq;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;



/**
 * rabbitmq配置
 * @author xiangkaihong
 * @data 2019-04-28 13:17
 */

@Configuration
public class RabbitmqConfig {

	@Bean
	public TopicExchange topicExchange() {

		return new TopicExchange(UserCenterMq.MQ_EXCHANGE_USER);
	}

	/*@Bean
	public Queue myQueue() {
		return new Queue("test-client",true);


	}
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setHost("local.rabbitmq.com");
		cachingConnectionFactory.setUsername("mws-dev");
		cachingConnectionFactory.setPassword("bwwl@123");
		cachingConnectionFactory.setPort(5672);
		cachingConnectionFactory.setPublisherConfirms(true);
		return cachingConnectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}
*/


}
