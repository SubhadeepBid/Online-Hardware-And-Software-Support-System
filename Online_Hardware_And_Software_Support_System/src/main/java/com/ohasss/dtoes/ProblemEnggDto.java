package com.ohasss.dtoes;

public class ProblemEnggDto {
	
	private int logID;
	private String probDesc;
	private String priority;
	private int empID;
	private String status;
	
	public ProblemEnggDto() {
		// TODO Auto-generated constructor stub
	}

	public ProblemEnggDto(int logID, String probDesc, String priority, int empID, String status) {
		super();
		this.logID = logID;
		this.probDesc = probDesc;
		this.priority = priority;
		this.empID = empID;
		this.status = status;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProblemEnggDto [logID=" + logID + ", probDesc=" + probDesc + ", priority=" + priority + ", empID="
				+ empID + ", status=" + status + "]";
	}

}
