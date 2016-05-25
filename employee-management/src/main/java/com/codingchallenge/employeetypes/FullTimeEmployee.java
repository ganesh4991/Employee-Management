package com.codingchallenge.employeetypes;

public class FullTimeEmployee extends EmploymentType{
	private String compensationPlan;
	private String pay;
	private String benefitPlan;

	
	public String getBenefitPlan() {
		return benefitPlan;
	}


	public void setBenefitPlan(String benefitPlan) {
		this.benefitPlan = benefitPlan;
	}


	public FullTimeEmployee() {
		// TODO Auto-generated constructor stub
		super();
	}


	public String getCompensationPlan() {
		return compensationPlan;
	}


	public void setCompensationPlan(String compensationPlan) {
		this.compensationPlan = compensationPlan;
	}


	public String getPay() {
		return pay;
	}


	public void setPay(String pay) {
		this.pay = pay;
	}


	@Override
	String saveEmploymentType() {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	
}
