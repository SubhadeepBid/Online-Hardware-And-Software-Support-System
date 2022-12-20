package com.ohasss.dtoes;

public class EmployeeHODDto {
	
	private int empID;
	private String name;
	private String email;
	
	public EmployeeHODDto() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeHODDto(int empID, String name, String email) {
		super();
		this.empID = empID;
		this.name = name;
		this.email = email;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeHODDto [empID=" + empID + ", name=" + name + ", email=" + email + "]";
	}

}
