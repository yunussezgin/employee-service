package com.yunus.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yunus.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	// define custom query using JPQL with index params
	@Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
	List<Employee> findByJPQL(String firstName, String lastName);

	// define custom query using JPQL with named params
	@Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
	List<Employee> findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

	// define custom query using Native SQL with index params
	@Query(value = "select * from employees e where e.first_name =?1 and e.last_name =?2", nativeQuery = true)
	List<Employee> findByNativeSQL(String firstName, String lastName);

}
