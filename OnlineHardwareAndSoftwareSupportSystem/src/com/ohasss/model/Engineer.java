package com.ohasss.model;

public class Engineer {
	
	private int engid;
	private String engname;
	private String enguser;
	private String engpass;
	private String category;
	
	public Engineer() {
		// TODO Auto-generated constructor stub
	}

	public Engineer(int engid, String engname, String enguser, String engpass, String category) {
		super();
		this.engid = engid;
		this.engname = engname;
		this.enguser = enguser;
		this.engpass = engpass;
		this.category = category;
	}

	public int getEngid() {
		return engid;
	}

	public void setEngid(int engid) {
		this.engid = engid;
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public String getEnguser() {
		return enguser;
	}

	public void setEnguser(String enguser) {
		this.enguser = enguser;
	}

	public String getEngpass() {
		return engpass;
	}

	public void setEngpass(String engpass) {
		this.engpass = engpass;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Engineer [engid=" + engid + ", engname=" + engname + ", enguser=" + enguser + ", engpass=" + engpass
				+ ", category=" + category + "]";
	}
	
}