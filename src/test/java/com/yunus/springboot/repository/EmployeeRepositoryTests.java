package com.yunus.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;
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
	//@DisplayName("JUnit test for save employee operation")
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
}
