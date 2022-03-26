package com.yunus.springboot.service;

import java.util.List;

import com.yunus.springboot.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	
	List<Employee> getAllEmployees();

}
