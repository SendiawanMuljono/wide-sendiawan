package com.wide.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wide.app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
