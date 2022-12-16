package com.ohasss.model;

public class Employee {
	
	private int empid;
	private String empname;
	private String empusername;
	private String emppassword;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empid, String empname, String empusername, String emppassword) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empusername = empusername;
		this.emppassword = emppassword;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpusername() {
		return empusername;
	}

	public void setEmpusername(String empusername) {
		this.empusername = empusername;
	}

	public String getEmppassword() {
		return emppassword;
	}

	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", empusername=" + empusername + ", emppassword="
				+ emppassword + "]";
	}
	
}
