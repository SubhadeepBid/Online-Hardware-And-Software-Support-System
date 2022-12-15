package com.ohasss.model;

public class Problem_Engineer {
	
	private int problemId;
	private int engId;
	private int empId;
	private String pName;
	private String category;
	private String status;
	
	public Problem_Engineer() {
		// TODO Auto-generated constructor stub
	}

	public Problem_Engineer(int problemId, int engId, int empId, String pName, String category, String status) {
		super();
		this.problemId = problemId;
		this.engId = engId;
		this.empId = empId;
		this.pName = pName;
		this.category = category;
		this.status = status;
	}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public int getEngId() {
		return engId;
	}

	public void setEngId(int engId) {
		this.engId = engId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Problem_Engineer [problemId=" + problemId + ", engId=" + engId + ", empId=" + empId + ", pName=" + pName
				+ ", category=" + category + ", status=" + status + "]";
	}

}
