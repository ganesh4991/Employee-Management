package com.codingchallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenge.employeetypes.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByLastName(String lastName);
}