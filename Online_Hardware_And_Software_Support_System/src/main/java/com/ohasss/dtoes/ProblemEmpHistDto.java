package com.ohasss.dtoes;

import java.sql.Timestamp;

public class ProblemEmpHistDto {
	
	private int logID; 
	private int empID;
	private String probDesc;
	private Timestamp LaunchedOn; 
	private String category; 
	private String status;
	
	public ProblemEmpHistDto() {
		// TODO Auto-generated constructor stub
	}

	public ProblemEmpHistDto(int logID, int empID, String probDesc, Timestamp launchedOn, String category, String status) {
		super();
		this.logID = logID;
		this.empID = empID;
		this.probDesc = probDesc;
		LaunchedOn = launchedOn;
		this.category = category;
		this.status = status;
	}

	public int getLogID() {
		return logID;
	}

	public void setLogID(int logID) {
		this.logID = logID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getProbDesc() {
		return probDesc;
	}

	public void setProbDesc(String probDesc) {
		this.probDesc = probDesc;
	}

	public Timestamp getLaunchedOn() {
		return LaunchedOn;
	}

	public void setLaunchedOn(Timestamp launchedOn) {
		LaunchedOn = launchedOn;
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
		return "ProblemEmpHistDto [logID=" + logID + ", empID=" + empID + ", probDesc=" + probDesc + ", LaunchedOn="
				+ LaunchedOn + ", category=" + category + ", status=" + status + "]";
	}

}
