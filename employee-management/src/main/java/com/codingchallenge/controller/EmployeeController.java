package com.codingchallenge.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingchallenge.employeetypes.Employee;
import com.codingchallenge.repository.EmployeeRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employees;

	// for raw json values
	@RequestMapping(value = "/employee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employees.save(employee);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	// Adding employee via html page
	@RequestMapping(value = "/addemployee", method = RequestMethod.POST)
	public String addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("address") String address, @RequestParam("middleName") String middleName,
			@RequestParam("emailId") String emailId, @RequestParam("dob") String dob, @RequestParam("ssn") String ssn,
			@RequestParam("gender") String gender, @RequestParam("employmentType") String employmentType) {

		// Date format conversion
		DateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(dob);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Employee e = new Employee();
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setDob(date);
		e.setEmailId(emailId);
		e.setEmploymentType(employmentType);
		e.setGender(gender);
		e.setSsn(ssn);
		e.setAddress(address);
		employees.save(e);
		return "Employee added";
	}

	// adding value via file
	@RequestMapping(method = RequestMethod.POST, value = "/fileupload")
	public String jsonFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		String json = "";
		try {
			InputStream inputStream = file.getInputStream();
			Scanner o = new Scanner(inputStream);
			while (o.hasNextLine())
				json += o.nextLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TypeToken<List<Employee>> token = new TypeToken<List<Employee>>() {
		};
		Gson gson = new Gson();
		List<Employee> newEmployees = gson.fromJson(json, token.getType());
		for (Employee e : newEmployees) {
			employees.save(e);
		}

		return "File Uploaded";
	}

	// json output of all employees in the database
	@RequestMapping(value = "/getemployees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getAllEmployees() {

		return new ResponseEntity<Collection<Employee>>(employees.findAll(), HttpStatus.OK);
	}

	// Fetch an existing employee based on employeeId.
	@RequestMapping(value = "/editemployee", method = RequestMethod.GET)
	public String getEmployeeById(@RequestParam("id") long id) {

		Employee e = employees.findOne(id);
		if (e==null)
			return "Invalid Employee Id";
		String html = "<html><body><form method=\"POST\" enctype=\"html/text\" action=\"/editemployee\"><table><tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\" value=\""
				+ e.getEmployeeId()
				+ "\" readonly=\"readonly\"/></td></tr><tr><td>First Name:</td><td><input type=\"text\" name=\"firstName\" \" value=\""
				+ e.getFirstName()
				+ "\"/></td></tr><tr><td>Last Name:</td><td><input type=\"text\" name=\"lastName\" value=\""
				+ e.getLastName()
				+ "\"/></td></tr><tr><td></td><td><input type=\"submit\" value=\"Save\" /></td></tr></table></form></body></html>";
		return html;
	}

	
	// function to modify the details of an employee. yet to be completed.
	@RequestMapping(value = "/editemployee", method = RequestMethod.POST)
	public String editEmployeeById(@RequestParam("id") long id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		Employee e=employees.findOne(id);
		e.setFirstName(firstName);
		e.setLastName(lastName);
		employees.save(e);
		return "Employee details updated";
	}

	// Delete employee by Id
	@RequestMapping(value = "/deleteemployee", method = RequestMethod.GET)
	public String deleteEmployeeById(@RequestParam("id") long id) {

		Employee e = employees.findOne(id);
		if (e==null)
			return "Invalid Employee Id";
		String html = "<html><body><form method=\"POST\" enctype=\"html/text\" action=\"/deleteemployee\"><table><tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\" value=\""
				+ e.getEmployeeId()
				+ "\" readonly=\"readonly\"/></td></tr><tr><td>First Name:</td><td><input type=\"text\" name=\"firstName\" \" value=\""
				+ e.getFirstName()
				+ "\" readonly=\"readonly\" /></td></tr><tr><td>Last Name:</td><td><input type=\"text\" name=\"lastName\" value=\""
				+ e.getLastName()
				+ "\" readonly=\"readonly\"/></td></tr><tr><td></td><td><input type=\"submit\" value=\"Delete\" /></td></tr></table></form></body></html>";
		return html;
	}
	
	// Removing employee from repository
	@RequestMapping(value = "/deleteemployee", method = RequestMethod.POST)
	public String deleteEmployeeFromRepository(@RequestParam("id") long id) {
		Employee e=employees.findOne(id);
		employees.delete(e);
		return "Employee "+e.getEmployeeId()+" deleted ";
	}
	

}