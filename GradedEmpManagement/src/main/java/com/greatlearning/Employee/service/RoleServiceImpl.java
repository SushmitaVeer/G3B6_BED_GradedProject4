package com.greatlearning.Employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.Employee.model.Role;
import com.greatlearning.Employee.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository repository;

	@Override
	public Role createRole(Role role) {
		return repository.save(role);
	}

}
