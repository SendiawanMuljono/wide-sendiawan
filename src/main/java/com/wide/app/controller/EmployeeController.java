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

import com.wide.app.model.Employee;
import com.wide.app.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private final EmployeeRepository employeeRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		try {
			Employee createdEmployee = employeeRepository.save(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
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
	public ResponseEntity<List<Employee>> getAllEmployees(){
		try {
			List<Employee> employees = employeeRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id){
		try {
			Optional<Employee> employee = employeeRepository.findById(id);
			if(employee.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(employee.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable String id){
		try {
			Optional<Employee> existedEmployee = employeeRepository.findById(id);
			
			if(existedEmployee.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			existedEmployee.get().setEmployeeName(employee.getEmployeeName());
			existedEmployee.get().setDepartment(employee.getDepartment());
			employeeRepository.save(existedEmployee.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(existedEmployee.get());
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
	public ResponseEntity<Employee> deleteEmployee(@PathVariable String id){
		try {
			Optional<Employee> existedEmployee = employeeRepository.findById(id);
			
			if(existedEmployee.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			employeeRepository.delete(existedEmployee.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
