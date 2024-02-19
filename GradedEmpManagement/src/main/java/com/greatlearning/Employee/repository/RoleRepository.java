package com.greatlearning.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.Employee.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
