package com.codingchallenge.employeetypes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long employeeId;
	private String firstName;
	private String middleName;
	private String lastName;
	
	/*
	private String ssn;
	private Date dob;
	private Date employeeJoinDate;
	private Date employeeEndDate;
	private String gender;
	private String address;
	private String emailId;
	private String employementType;
	*/
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Employee(){
		
	}
		
	@Override
    public String toString() {
        return String.format(
                "[id=%d, firstName='%s', middleName,lastName='%s']",
                employeeId, firstName, middleName,lastName);
    }
	
}
