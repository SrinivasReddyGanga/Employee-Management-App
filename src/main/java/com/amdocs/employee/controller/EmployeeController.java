package com.amdocs.employee.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.employee.model.Employee;
import com.amdocs.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee") // class level RequestMapping to redude using "/employee" on each method
public class EmployeeController {

	@Autowired
	private EmployeeService empservice;

	// Create Employee handler
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee emp = null;
		try {
			emp = empservice.createEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(emp);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// get all the employees handler
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> empList = empservice.getEmployees();
		if (empList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(empList);
	}

	// get Employee by {Emp_Id} handler
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee emp = empservice.getEmployeeById(id);
		if (emp == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(emp);
	}

	// delete an Employee by {Emp_Id} handler
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeEmployee(@PathVariable int id) {
		try {
			String msg = empservice.deleteEmployeeById(id);
			return ResponseEntity.status(HttpStatus.OK).body(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// update an Employee Salary handler
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp, @PathVariable int id) {
		try {
			String msg = empservice.updateEmployee(emp, id);
			return ResponseEntity.status(HttpStatus.OK).body(msg);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Method to get Employee based on name and operator(equals|startWith|endsWith)
	@GetMapping("/search")
	public ResponseEntity<List<Employee>> retrieveEmployeesByName(@RequestParam(name = "name") String name,
			@RequestParam(name = "operator") String operator) {
		List<Employee> empList = empservice.getEmployeesByName(name, operator);
		if (empList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(empList);
	}
}
