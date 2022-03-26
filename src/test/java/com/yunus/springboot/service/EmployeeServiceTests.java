package com.yunus.springboot.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yunus.springboot.exception.ResourceNotFoundException;
import com.yunus.springboot.model.Employee;
import com.yunus.springboot.repository.EmployeeRepository;
import com.yunus.springboot.service.impl.EmployeeServiceImp;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImp employeeService;
	
	Employee employee;
	
	@BeforeEach
	public void setup() {
//		employeeRepository = Mockito.mock(EmployeeRepository.class);
//		employeeService = new EmployeeServiceImp(employeeRepository);
		employee = Employee.builder()
				.id(1L)
				.firstName("Yunus")
				.lastName("Sezgin")
				.email("yunussezgin8@gmail.com")
				.build();
	}
	
	// JUnit test for saveEmployee method
	@DisplayName("JUnit test for saveEmployee method")
	@Test
	public void givenEmployeeEmail_whenSaveEmployee_thenReturnEmployeeObject() {
		// given - precondition or setup
		given(employeeRepository.findByEmail(employee.getEmail()))
				.willReturn(Optional.empty());
				
		given(employeeRepository.save(employee)).willReturn(employee);
		
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		// when - action or behavior that we are going test
		Employee savedEmployee = employeeService.saveEmployee(employee);
		
		System.out.println(savedEmployee);
		
		// then - verify the output
		Assertions.assertThat(savedEmployee).isNotNull();
	}
	
	// JUnit test for saveEmployee method
	@DisplayName("JUnit test for saveEmployee method which throws exception")
	@Test
	public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {
		// given - precondition or setup
		given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
		
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		// when - action or behavior that we are going test
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			employeeService.saveEmployee(employee);
		});
		
		// then - verify the output
		verify(employeeRepository, never()).save(any(Employee.class));
	}

	// JUnit test for getAllEmployees method
	@DisplayName("JUnit test for getAllEmployees method")
	@Test
	public void givenEmployeesList_whenAllEmployees_thenReturnEmployeesList() {
		// given - precondition or setup
		Employee employee1 = Employee.builder()
					.id(2L)
					.firstName("Emre")
					.lastName("Sezgin")
					.email("emresezgingmail.com")
					.build();
		
		given(employeeRepository.findAll()).willReturn(Arrays.asList(employee, employee1));
		
		// when - action or behavior that we are going test
		List<Employee> employeeList = employeeService.getAllEmployees();
		
		// then - verify the output
		Assertions.assertThat(employeeList).isNotNull();
		Assertions.assertThat(employeeList.size()).isEqualTo(2);
	}
	
	// JUnit test for getAllEmployees method
	@DisplayName("JUnit test for getAllEmployees method (negative scenario)")
	@Test
	public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList() {
		// given - precondition or setup
		given(employeeRepository.findAll()).willReturn(Collections.emptyList());
		
		// when - action or behavior that we are going test
		List<Employee> employeeList = employeeService.getAllEmployees();
		
		// then - verify the output
		Assertions.assertThat(employeeList).isNotNull();
		Assertions.assertThat(employeeList.size()).isEqualTo(0);
	}


}
