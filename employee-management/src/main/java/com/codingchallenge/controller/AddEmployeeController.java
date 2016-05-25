package com.codingchallenge.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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
public class AddEmployeeController {

	@Autowired
	private EmployeeRepository employees;

	@RequestMapping(value = "/employee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employees.save(employee);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/fileupload")
	public String jsonFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
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
		

		TypeToken<List<Employee>> token = new TypeToken<List<Employee>>(){};
		Gson gson=new Gson();
		List<Employee> newEmployees = gson.fromJson(json, token.getType());	
		for (Employee e : newEmployees) {
			employees.save(e);
		}

		return json;
	}
}
