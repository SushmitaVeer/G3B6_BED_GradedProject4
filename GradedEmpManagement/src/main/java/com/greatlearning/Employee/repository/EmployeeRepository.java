package com.greatlearning.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.Employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstname);
}
