package com.ohasss.model;

public class Problem {
	
	private int problemid;
	private int empid;
	private String category;
	private String name;
	
	public Problem() {
		// TODO Auto-generated constructor stub
	}

	public Problem(int problemid, int empid, String category, String name) {
		super();
		this.problemid = problemid;
		this.empid = empid;
		this.category = category;
		this.name = name;
	}

	public int getProblemid() {
		return problemid;
	}

	public void setProblemid(int problemid) {
		this.problemid = problemid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Problem [problemid=" + problemid + ", empid=" + empid + ", category=" + category + ", name=" + name
				+ "]";
	}

}
