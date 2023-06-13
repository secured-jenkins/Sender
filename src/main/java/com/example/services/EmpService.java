package com.example.services;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.DTO.EmployeeDTO;
import com.example.exceptions.NotFoundException;
import com.example.persistence.CarRepo;
import com.example.persistence.EmpRepo;
import com.example.persistence.entities.Car;
import com.example.persistence.entities.Employee;

@Service
public class EmpService implements UserDetailsService {
	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private CarRepo carRepo;

	@Autowired
	private Mappers converter;

	public EmployeeDTO addEmployee(EmployeeDTO employee) {
		return converter.mapEmployeeToDTO(empRepo.save(converter.mapEmployeeDTOToEntity(employee)));
	}

//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Employee toTest = empRepo.findByUsername(username);
//		if (toTest == null) {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		} else {
//			return new User(toTest.getUsername(), toTest.getPassword(),
//					toTest.getAuthorities());
//		}
//	}

	public EmployeeDTO findById(Long id) {
		return converter
				.mapEmployeeToDTO(empRepo.findById(id).orElseThrow(() -> new NotFoundException("employee", id)));
	}

	public List<EmployeeDTO> getAll() {
		return empRepo.findAll().stream().map(x -> converter.mapEmployeeToDTO(x)).collect(Collectors.toList());
	}

	public Long findCount() {
		return empRepo.count();
	}

	public void deleteEmp(Long id) {
		empRepo.deleteById(id);
	}

	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employee) {
		Employee temp = empRepo.findById(id).orElse(null);
		if (temp != null) {
			temp.setAddress(employee.getAddress());
			temp.setFirstName(employee.getFirstName());
			temp.setLastName(employee.getLastName());
			temp.setUsername(employee.getFirstName() + employee.getLastName());
			empRepo.save(temp);
		}
		return converter.mapEmployeeToDTO(temp);
	}

	public List<Employee> getLikeEmp(String pattern) {
		return empRepo.findByFirstNameLike(pattern);
	}

	public Employee getEverything(Long id) {
		return empRepo.findById(id).orElseThrow(() -> new NotFoundException("employee", id));
	}

	public Employee attachCar(Long empId, Long carId) {
		Employee tenp = empRepo.findById(empId).orElseThrow(() -> new NotFoundException("employee", empId));
		Car car = carRepo.findById(carId).orElseThrow(() -> new NotFoundException("car", carId));
		tenp.addCar(car);
//		System.out.println("list size: " + tenp.getCars().size());
		return empRepo.save(tenp);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee x = empRepo.findByUsername(username);
		if (x == null) {
			throw new UsernameNotFoundException(username);
		} else {
			return x;
		}
	}
}