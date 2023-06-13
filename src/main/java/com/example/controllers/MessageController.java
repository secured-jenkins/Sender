package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.CarService;
import com.example.services.EmpService;
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

	@GetMapping("/car/{id}")
	public void sendCars(@PathVariable Long id) {
		rabbitSender.sendCar(carService.getCar(id));
	}

	@GetMapping("/employeeList")
	public void sendEmployees() {
		rabbitSender.sendAllEmployees(empService.getAll());
	}
}
