package com.codingchallenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.codingchallenge.employeetypes.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}