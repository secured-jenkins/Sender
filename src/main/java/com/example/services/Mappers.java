package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DTO.CarDTO;
import com.example.DTO.EmployeeDTO;
import com.example.persistence.entities.Car;
import com.example.persistence.entities.Employee;

@Service
public class Mappers {
	@Autowired
	private PasswordEncoder bcrypt;

	public Employee mapEmployeeDTOToEntity(EmployeeDTO employee) {
		return new Employee(employee.getFirstName(), employee.getLastName(), employee.getAddress(),
				bcrypt.encode(employee.getPassword()));
	}

	public EmployeeDTO mapEmployeeToDTO(Employee employee) {
		EmployeeDTO temp = new EmployeeDTO();
		temp.setFirstName(employee.getFirstName());
		temp.setLastName(employee.getLastName());
		temp.setAddress(employee.getAddress());
		temp.setUsername(employee.getUsername());
		return temp;
	}
	
	public CarDTO mapCarToDTO(Car car) {
		return new CarDTO(car.getModel(), car.getId());
	}

}