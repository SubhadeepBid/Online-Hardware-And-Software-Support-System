package com.ohasss.dtoes;

import java.sql.Timestamp;

public class ProblemHODDtoPNA {
	
	private int logID;
	private String probDesc;
	private int empID;
	private Timestamp launchedOn;
	private String priority;
	
	public ProblemHODDtoPNA() {
		// TODO Auto-generated constructor stub
	}

	public ProblemHODDtoPNA(int logID, String probDesc, int empID, Timestamp launchedOn, String priority) {
		super();
		this.logID = logID;
		this.probDesc = probDesc;
		this.empID = empID;
		this.launchedOn = launchedOn;
		this.priority = priority;
	}

	public int getLogID() {
		return logID;
	}

	public void setLogID(int logID) {
		this.logID = logID;
	}

	public String getProbDesc() {
		return probDesc;
	}

	public void setProbDesc(String probDesc) {
		this.probDesc = probDesc;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public Timestamp getLaunchedOn() {
		return launchedOn;
	}

	public void setLaunchedOn(Timestamp launchedOn) {
		this.launchedOn = launchedOn;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "ProblemHODDtoPNA [logID=" + logID + ", probDesc=" + probDesc + ", empID=" + empID + ", launchedOn="
				+ launchedOn + ", priority=" + priority + "]";
	}

}
