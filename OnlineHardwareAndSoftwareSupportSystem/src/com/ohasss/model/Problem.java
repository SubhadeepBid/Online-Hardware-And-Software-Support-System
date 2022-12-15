package com.ohasss.model;

public class Problem {
	
	private int problemId;
	private int empId;
	private String category;
	private String pname;
	
	public Problem() {
		// TODO Auto-generated constructor stub
	}

	public Problem(int problemId, int empId, String category, String pname) {
		super();
		this.problemId = problemId;
		this.empId = empId;
		this.category = category;
		this.pname = pname;
	}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String toString() {
		return "Problem [problemId=" + problemId + ", empId=" + empId + ", category=" + category + ", pname=" + pname
				+ "]";
	}

}
