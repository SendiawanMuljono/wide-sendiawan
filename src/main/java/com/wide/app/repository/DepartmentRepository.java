package com.wide.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wide.app.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, String>{

}
