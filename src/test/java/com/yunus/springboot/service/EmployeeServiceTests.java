package com.yunus.springboot.service;



import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

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
		given(employeeRepository.findByEmail(employee.getEmail()))
				.willReturn(Optional.of(employee));
		
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		// when - action or behavior that we are going test
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			employeeService.saveEmployee(employee);
		});
		
		// then - verify the output
		verify(employeeRepository, never()).save(any(Employee.class));
	}


}
