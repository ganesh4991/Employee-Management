package com.codingchallenge.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.employeetypes.Employee;
import com.codingchallenge.repository.EmployeeRepository;

@RestController 
public class GetAllEmployeesController {
		
		@Autowired
		private EmployeeRepository employees;
			
		@RequestMapping(value = "/employee", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Collection<Employee>>getEmployee() {
			
			return new ResponseEntity<Collection<Employee>>(employees.findAll(),HttpStatus.OK);
		}
	
}
