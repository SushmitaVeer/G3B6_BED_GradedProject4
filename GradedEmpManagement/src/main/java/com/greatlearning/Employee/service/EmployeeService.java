package com.greatlearning.Employee.service;

import java.util.List;

import com.greatlearning.Employee.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployee();

	Employee createEmp(Employee employee);

	void updateEmp(Employee employee);

	Employee getEmpById(int empId);

	void deleteEmpById(int empId);

	List<Employee> searchEmpByFirstName(String firstname);

	List<Employee> getAllEmployeesSorted(String order);
}
