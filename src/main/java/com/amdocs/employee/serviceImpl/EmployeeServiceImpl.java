package com.amdocs.employee.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amdocs.employee.dao.EmployeeRepository;
import com.amdocs.employee.model.Employee;
import com.amdocs.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository emprepo;

	@Autowired
	private Environment env;

	// Creating Employee
	public Employee createEmployee(Employee e) {
		return emprepo.save(e);
	}

	public Employee getEmployeeById(int emp_id) {
		return emprepo.findByEmpId(emp_id);
	}

	public List<Employee> getEmployees() {
		return emprepo.findAll();
	}

	public String deleteEmployeeById(int emp_id) {
		emprepo.deleteById(emp_id);
		return "Employee got deleted";
	}

	public String updateEmployee(Employee emp, int id) {
		Employee empold = emprepo.getById(id);
		if (empold != null) {
			empold.setSalary(emp.getSalary());
			emprepo.save(empold);
		} else {
			return "Employee not found";
		}
		return "Employee Updated with given Salary" + empold.getSalary();
	}

	public List<Employee> getEmployeesByName(String name, String operator) {
		List<Employee> employees = new ArrayList<>();
		Employee emp = null;
		if (operator.equalsIgnoreCase(env.getProperty("user.operator.equals"))) {
			emp = emprepo.findByName(name);
			employees.add(emp);
		} else if (operator.equalsIgnoreCase(env.getProperty("user.operator.startWith"))) {
			employees = emprepo.findByNameStartsWith(name);
		}else if(operator.equalsIgnoreCase(env.getProperty("user.operator.endsWith"))) {
			employees = emprepo.findByNameEndsWith(name);
		}
		return employees;
	}
}
