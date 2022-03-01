package com.amdocs.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amdocs.employee.model.Employee;

@Service
public interface EmployeeService {

	public Employee createEmployee(Employee e);
	
	public Employee getEmployeeById(int emp_id);

	public List<Employee> getEmployees();

	public String deleteEmployeeById(int emp_id);
	
	public String updateEmployee(Employee emp, int id);
	
	public List<Employee> getEmployeesByName(String name, String operator) ;

}
