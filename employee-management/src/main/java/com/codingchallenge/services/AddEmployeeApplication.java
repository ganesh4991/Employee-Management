package com.codingchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/employee",method=RequestMethod.POST)
	public boolean addEmployee(@RequestParam(value="firstName", required=true, defaultValue="") String firstName,@RequestParam(value="middleName", required=false, defaultValue="") String middleName,@RequestParam(value="lastName", required=true, defaultValue="") String lastName){
		employees.save(new Employee(firstName,middleName,lastName));
		return false;
	}
}
