package com.greatlearning.Employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.Employee.model.Employee;
import com.greatlearning.Employee.model.Role;
import com.greatlearning.Employee.model.User;
import com.greatlearning.Employee.service.EmployeeService;
import com.greatlearning.Employee.service.RoleService;
import com.greatlearning.Employee.service.UserService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService service;
	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;

	@GetMapping("/list")
	@ResponseBody
	public List<Employee> getAllEmployees() {
		return service.getAllEmployee();
	}

	@PostMapping("/addEmployees")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = service.createEmp(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	@PostMapping("/addRole")
	public ResponseEntity<Object> addRole(@RequestBody Role role) {
		Role savedRole = roleService.createRole(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
	}

	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		for (Role role : user.getRoles()) {
			roleService.createRole(role);
		}
		User users = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(users);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable int id) {
		Employee employee = service.getEmpById(id);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
	}

	@PutMapping("/updateEmp")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee updatedEmployee) {
		Employee existingEmployee = service.getEmpById(updatedEmployee.getId());
		if (existingEmployee != null) {
			Employee savedEmployee = service.createEmp(updatedEmployee);
			return ResponseEntity.ok(savedEmployee);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
	}

	@DeleteMapping("/deleteEmp/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable int id) {
		Employee existingEmployee = service.getEmpById(id);
		if (existingEmployee != null) {
			service.deleteEmpById(id);
			return ResponseEntity.ok("Deleted employee id - " + id);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchEmployeesByFirstName(@PathVariable String firstName) {
		return service.searchEmpByFirstName(firstName);
	}

	@GetMapping("/employees/sort")
	public List<Employee> getAllEmployeesSorted(@RequestParam String order) {
		return service.getAllEmployeesSorted(order);
	}

}
