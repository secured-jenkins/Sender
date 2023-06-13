package com.example.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.persistence.entities.Car;
@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
	@Query(value = "SELECT * FROM car t WHERE t.employee_id =  :emp_id", nativeQuery = true)
	public List<Car> findCarsByEmployeeId(@Param("emp_id") Long employeeId);
	
}
