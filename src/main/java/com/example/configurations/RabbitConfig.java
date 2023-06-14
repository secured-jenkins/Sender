package com.example.configurations;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitConfig {
	final static String queueName = "spring-boot";
	final static String queueName2 = "summer-boot";
//	final static String topicExchangeName = "spring-boot-exchange";
	
	@Value("${application.exchanger}")
	private String topicExchangeName;

	@Bean(name = "firstQueue")
	Queue queue() {
		Queue x = new Queue(queueName, false);
//		x.addArgument("x-max-length", 5);
//		x.addArgument("x-message-ttl", Integer.parseUnsignedInt("1000"));
		return x;
	}

	@Bean(name = "secondQueue")
	Queue queueTwo() {
		Queue x = new Queue(queueName2, false);
//		x.addArgument("x-max-length", 5);
//		x.addArgument("x-message-ttl", Integer.parseUnsignedInt("1000"));
		return x;
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean(name = "firstBind")
	@Primary
	Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with("spring.#");
//		return BindingBuilder.bind(queue()).to(exchange());
	}

	@Bean(name = "secondBind")
	Binding bindingTwo() {
		return BindingBuilder.bind(queueTwo()).to(exchange()).with("summer.#");
//		return BindingBuilder.bind(queueTwo()).to(exchange());
	}

	/*
	 * Declarables can be used in order to define the beans defined here
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean(name = "myRabbitTemplate")
	public AmqpTemplate myRabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
