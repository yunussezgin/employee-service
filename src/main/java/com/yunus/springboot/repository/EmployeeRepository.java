package com.yunus.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yunus.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
