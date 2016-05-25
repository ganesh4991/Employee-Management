package com.codingchallenge.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.employeetypes.Employee;
import com.codingchallenge.repository.EmployeeRepository;

@RestController
public class AddEmployeeApplication {

	@Autowired
	private EmployeeRepository employees;
		
	@RequestMapping(value = "/employee", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)//,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee>addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee=employees.save(employee);
		return new ResponseEntity<Employee>(savedEmployee,HttpStatus.OK);
	}
}
