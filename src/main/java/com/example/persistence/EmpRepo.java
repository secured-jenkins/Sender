package com.example.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.persistence.entities.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {
	@Query("SELECT t FROM Employee t WHERE t.firstName LIKE %:pattern%")
	public List<Employee> findByFirstNameLike(@Param("pattern") String pattern);

//	public Optional<Employee> findByuserName(String userName);
	Employee findByUsername(String username);

}