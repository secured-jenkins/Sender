package com.example.controllers;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.CarService;
import com.example.services.EmpService;
import com.example.services.Mappers;
import com.example.services.RabbitSender;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private EmpService empService;

	@Autowired
	private CarService carService;

	@Autowired
	private RabbitSender rabbitSender;
	
	@Autowired
	private Mappers mapper;

	@GetMapping("/car/{id}")
	public void sendCars(@PathVariable Long id) {
		rabbitSender.sendCar( mapper.mapCarToDTO(carService.getCar(id)));
	}

	@GetMapping("/employeeList")
	public void sendEmployees() {
		System.out.println(empService.getAll().get(0).getUsername());
		rabbitSender.sendAllEmployees(empService.getAll());
	}
}
