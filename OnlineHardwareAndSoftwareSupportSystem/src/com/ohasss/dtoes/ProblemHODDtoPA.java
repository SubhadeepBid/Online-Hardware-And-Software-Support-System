package com.ohasss.dtoes;

import java.sql.Timestamp;

public class ProblemHODDtoPA {
	
	private int logID;
	private String probDesc;
	private int empID;
	private Timestamp launchedOn;
	private String priority;
	private int engID;
	private String status;
	private Timestamp startedWorking;
	private Timestamp closedOn;
	
	public ProblemHODDtoPA() {
		// TODO Auto-generated constructor stub
	}

	public ProblemHODDtoPA(int logID, String probDesc, int empID, Timestamp launchedOn, String priority, int engID,
			String status, Timestamp startedWorking, Timestamp closedOn) {
		super();
		this.logID = logID;
		this.probDesc = probDesc;
		this.empID = empID;
		this.launchedOn = launchedOn;
		this.priority = priority;
		this.engID = engID;
		this.status = status;
		this.startedWorking = startedWorking;
		this.closedOn = closedOn;
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

	public int getEngID() {
		return engID;
	}

	public void setEngID(int engID) {
		this.engID = engID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStartedWorking() {
		return startedWorking;
	}

	public void setStartedWorking(Timestamp startedWorking) {
		this.startedWorking = startedWorking;
	}

	public Timestamp getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Timestamp closedOn) {
		this.closedOn = closedOn;
	}

	@Override
	public String toString() {
		return "ProblemHODDtoPA [logID=" + logID + ", probDesc=" + probDesc + ", empID=" + empID + ", launchedOn="
				+ launchedOn + ", priority=" + priority + ", engID=" + engID + ", status=" + status
				+ ", startedWorking=" + startedWorking + ", closedOn=" + closedOn + "]";
	}

}
