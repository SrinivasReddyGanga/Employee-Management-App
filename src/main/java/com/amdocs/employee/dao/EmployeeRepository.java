package com.amdocs.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amdocs.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByEmpId(int empId);
	public Employee findByName(String name);
 	
	@Query("FROM Employee e WHERE e.name LIKE :name%")
	List<Employee> findByNameStartsWith(@Param("name") String name);
	
	@Query("FROM Employee e WHERE e.name LIKE %:name")
	List<Employee> findByNameEndsWith(@Param("name") String name);
}
