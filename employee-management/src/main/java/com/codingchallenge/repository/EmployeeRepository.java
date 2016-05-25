package com.codingchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenge.employeetypes.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}