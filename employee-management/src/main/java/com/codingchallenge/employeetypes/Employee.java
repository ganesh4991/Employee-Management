package com.codingchallenge.employeetypes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

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
	
	Employee(String firstName,String middleName,String lastName){
		
		this.firstName=firstName;
		this.middleName=middleName;
		this.lastName=lastName;	
		
	}
	
	@Override
    public String toString() {
        return String.format(
                "[id=%d, firstName='%s', middleName,lastName='%s']",
                employeeId, firstName, middleName,lastName);
    }
	
}
