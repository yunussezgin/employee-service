package com.yunus.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.yunus.springboot.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	
	@BeforeEach
	public void setup() {
		employee = Employee.builder()
				.firstName("Yunus")
				.lastName("Sezgin")
				.email("yunussezgin8@gmail.com")
				.build();
	}

	// JUnit test for save employee operation
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();

		// when - action or the behavior that we are going test
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
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunusssezgin8@gmail.com")
//				.build();
		
		Employee employee1 = Employee.builder()
				.firstName("Yunus")
				.lastName("Zengin")
				.email("yunusszengin8@gmail.com")
				.build();
		
		employeeRepository.save(employee);
		employeeRepository.save(employee1);
		
		// when - action or the behavior that we are going test
		List<Employee> employeelist = employeeRepository.findAll();
		
		// then - verify the output
		assertThat(employeelist).isNotNull();
		assertThat(employeelist.size()).isEqualTo(2);
	
	}
	
	// JUnit test for get employee by id operation
	@DisplayName("JUnit test for get employee by id operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		
		// when - action or the behavior that we are going test
		Employee employeeDB = employeeRepository.findById(employee.getId()).get();
		
		// then - verify the output
		assertThat(employeeDB).isNotNull();
	}
	
	// JUnit test for get employee by email operation
	@DisplayName("JUnit test for get employee by email operation")
	@Test
	public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		
		// when - action or the behavior that we are going test
		Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();
				
		// then - verify the output
		assertThat(employeeDB).isNotNull();	
	}
	
	// JUnit test for update employee operation
	@DisplayName("JUnit test for update employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		
		// when - action or the behavior that we are going test
		Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
		savedEmployee.setEmail("yns@gmail.com");
		savedEmployee.setFirstName("Yns");
		Employee updatedEmployee = employeeRepository.save(savedEmployee);
		
		// then - verify the output
		assertThat(updatedEmployee.getEmail()).isEqualTo("yns@gmail.com");
		assertThat(updatedEmployee.getFirstName()).isEqualTo("Yns");
	}
	
	// JUnit test for delete employee operation
	@DisplayName("JUnit test for delete employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		
		// when - action or the behavior that we are going test
		employeeRepository.deleteById(employee.getId());
		Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
		
		// then - verify the output
		assertThat(employeeOptional).isEmpty();
	}
	
	// JUnit test for custom query using JPQL with index
	@DisplayName("JUnit test for custom query using JPQL with index")
	@Test
	public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		String firstName = "Yunus";
		String lastName = "Sezgin";
		
		// when - action or the behavior that we are going test
		List<Employee> employees = employeeRepository.findByJPQL(firstName, lastName);
		
		// then - verify the output
		assertThat(employees).isNotEmpty();
	}
	
	// JUnit test for custom query using JPQL with named params
	@DisplayName("JUnit test for custom query using JPQL with named params")
	@Test
	public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		String firstName = "Yunus";
		String lastName = "Sezgin";
		
		// when - action or the behavior that we are going test
		List<Employee> employees = employeeRepository.findByJPQLNamedParams(firstName, lastName);
		
		// then - verify the output
		assertThat(employees).isNotEmpty();
	}
	
	// JUnit test for custom query using native SQL with index
	@DisplayName("JUnit test for custom query using native SQL with index")
	@Test
	public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		
		// when - action or the behavior that we are going test
		List<Employee> employees = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());
		
		// then - verify the output
		assertThat(employees).isNotEmpty();
	}
	
	// JUnit test for custom query using native SQL with named params
	@DisplayName("JUnit test for custom query using native SQL with named params")
	@Test
	public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject() {
		// given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Yunus")
//				.lastName("Sezgin")
//				.email("yunussezgin8@gmail.com")
//				.build();
		employeeRepository.save(employee);
		
		// when - action or the behavior that we are going test
		List<Employee> employees = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());
		
		// then - verify the output
		assertThat(employees).isNotEmpty();
	}
}
