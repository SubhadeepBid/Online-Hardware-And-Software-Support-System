package com.ohasss.model;

import java.sql.Timestamp;

public class Problem_Temp {
	
	private int prob_temp_id;
	private String probDesc;
	private String category;
	private Timestamp launchedOn;
	private int empID;
	
	public Problem_Temp() {
		// TODO Auto-generated constructor stub
	}

	public Problem_Temp(int prob_temp_id, String probDesc, String category, Timestamp launchedOn, int empID) {
		super();
		this.prob_temp_id = prob_temp_id;
		this.probDesc = probDesc;
		this.category = category;
		this.launchedOn = launchedOn;
		this.empID = empID;
	}

	public int getProb_temp_id() {
		return prob_temp_id;
	}

	public void setProb_temp_id(int prob_temp_id) {
		this.prob_temp_id = prob_temp_id;
	}

	public String getProbDesc() {
		return probDesc;
	}

	public void setProbDesc(String probDesc) {
		this.probDesc = probDesc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getLaunchedOn() {
		return launchedOn;
	}

	public void setLaunchedOn(Timestamp launchedOn) {
		this.launchedOn = launchedOn;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	@Override
	public String toString() {
		return "Problem_Temp [prob_temp_id=" + prob_temp_id + ", probDesc=" + probDesc + ", category=" + category
				+ ", launchedOn=" + launchedOn + ", empID=" + empID + "]";
	}

}
