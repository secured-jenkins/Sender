package com.example.services;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.EmployeeDTO;
import com.example.persistence.entities.Car;

@Service
public class RabbitSender {
	final static String topicExchangeName = "spring-boot-exchange";
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void sendCar(Car car) {
		rabbitTemplate.convertAndSend(topicExchangeName, "spring.car", car);
	}

	public void sendAllEmployees(List<EmployeeDTO> employees) {
		rabbitTemplate.convertAndSend(topicExchangeName, "summer.employee", employees);
	}

}
