package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.EmployeeDTO;
import com.example.persistence.entities.Employee;
import com.example.services.EmpService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmpService empService;

	@GetMapping
	public Iterable<EmployeeDTO> getAllEmployees() {
		return empService.getAll();
	}

	@GetMapping(path = "/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable Long id) {
		return empService.findById(id);
	}

	@GetMapping(path = "/all/{id}")
	public Employee getEverything(@PathVariable Long id) {
		return empService.getEverything(id);
	}

	@PatchMapping("/{empId}/{carId}")
	public Employee attachCar(@PathVariable Long empId, @PathVariable Long carId) {
		return empService.attachCar(empId, carId);
	}

	@PostMapping
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
		return empService.addEmployee(employee);
	}

	@PutMapping
	public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employee) {
		return empService.updateEmployee(id, employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		empService.deleteEmp(id);
	}
}