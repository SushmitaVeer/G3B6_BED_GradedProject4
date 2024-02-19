package com.greatlearning.Employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greatlearning.Employee.model.Employee;
import com.greatlearning.Employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Override
	public List<Employee> getAllEmployee() {
		return repository.findAll();
	}

	@Override
	public Employee createEmp(Employee employee) {
		return repository.save(employee);

	}

	@Override
	public void updateEmp(Employee employee) {
		repository.save(employee);

	}

	@Override
	public Employee getEmpById(int empId) {
		return repository.findById(empId).orElse(null);
	}

	@Override
	public void deleteEmpById(int empId) {
		repository.deleteById(empId);

	}

	@Override
	public List<Employee> searchEmpByFirstName(String firstname) {
		return repository.findByFirstNameContainsAllIgnoreCase(firstname);
	}

	@Override
	public List<Employee> getAllEmployeesSorted(String order) {
		Sort sort = Sort.by(order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "firstName");
		return repository.findAll(sort);
	}

}
