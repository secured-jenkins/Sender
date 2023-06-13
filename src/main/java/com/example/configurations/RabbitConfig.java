package com.example.configurations;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {
	final static String queueName = "spring-boot";
	final static String queueName2 = "summer-boot";
	final static String topicExchangeName = "spring-boot-exchange";

	@Bean(name = "firstQueue")
	Queue queue() {
		Queue x = new Queue(queueName, false);
		x.addArgument("x-max-length", 5);
		x.addArgument("x-message-ttl", Integer.parseUnsignedInt("1000"));
		return x;
	}

	@Bean(name = "secondQueue")
	Queue queueTwo() {
		Queue x = new Queue(queueName2, false);
		x.addArgument("x-max-length", 5);
		x.addArgument("x-message-ttl", Integer.parseUnsignedInt("1000"));
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
	}

	@Bean(name = "secondBind")
	Binding bindingTwo() {
		return BindingBuilder.bind(queueTwo()).to(exchange()).with("summer.#");
	}

	/*
	 * Declarables can be used in order to define the beans defined here
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
