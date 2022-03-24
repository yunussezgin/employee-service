package com.yunus.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.yunus.springboot.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	// JUnit test for save employee operation
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

		// given - precondition or setup
		Employee employee = Employee.builder()
				.firstName("Yunus")
				.lastName("Sezgin")
				.email("yunussezgin8@gmail.com")
				.build();

		// when - action or the behavior that we are goint test
		Employee savedEmployee = employeeRepository.save(employee);

		// then - verify the output
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
		
	}
	
	// JUnit test for get all employees operation
	@DisplayName("JUnit test for get all employees operation")
	@Test
	public void givenEmployeesList_whenFindAll_thenEmployeesList() {
		// given - precondition or setup
		Employee employee = Employee.builder()
				.firstName("Yunus")
				.lastName("Sezgin")
				.email("yunusssezgin8@gmail.com")
				.build();
		
		Employee employee1 = Employee.builder()
				.firstName("Yunus")
				.lastName("Zengin")
				.email("yunusszengin8@gmail.com")
				.build();
		
		employeeRepository.save(employee);
		employeeRepository.save(employee1);
		
		// when - action or the behaviour that we are going test
		List<Employee> employeelist = employeeRepository.findAll();
		
		// then - verify the output
		assertThat(employeelist).isNotNull();
		assertThat(employeelist.size()).isEqualTo(2);
	
	}
	
	
}
