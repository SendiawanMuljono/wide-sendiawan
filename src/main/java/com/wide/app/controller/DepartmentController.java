package com.wide.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wide.app.model.Department;
import com.wide.app.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	private final DepartmentRepository departmentRepository;
	
	public DepartmentController(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department){
		try {
			Department createdDepartment = departmentRepository.save(department);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Department>> getAllDepartments(){
		try {
			List<Department> departments = departmentRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(departments);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable String id){
		try {
			Optional<Department> department = departmentRepository.findById(id);
			if(department.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(department.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable String id){
		try {
			Optional<Department> existedDepartment = departmentRepository.findById(id);
			
			if(existedDepartment.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			existedDepartment.get().setDepartmentAddress(department.getDepartmentAddress());
			existedDepartment.get().setDepartmentCode(department.getDepartmentCode());
			existedDepartment.get().setDepartmentName(department.getDepartmentName());
			departmentRepository.save(existedDepartment.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(existedDepartment.get());
		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<Department> deleteDepartment(@PathVariable String id){
		try {
			Optional<Department> existedDepartment = departmentRepository.findById(id);
			
			if(existedDepartment.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			departmentRepository.delete(existedDepartment.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
