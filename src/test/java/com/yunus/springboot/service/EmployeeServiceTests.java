package com.yunus.springboot.service;



import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;

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
	public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
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

}
