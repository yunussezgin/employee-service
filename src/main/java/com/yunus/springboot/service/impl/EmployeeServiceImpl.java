package com.yunus.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunus.springboot.exception.ResourceNotFoundException;
import com.yunus.springboot.model.Employee;
import com.yunus.springboot.repository.EmployeeRepository;
import com.yunus.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
		if (savedEmployee.isPresent()) {
			throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
		}

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee updateEmployee(Employee updateEmployee) {
		return employeeRepository.save(updateEmployee);
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.deleteById(id);
	}

}
