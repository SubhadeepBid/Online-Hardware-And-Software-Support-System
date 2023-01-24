package com.ohasss.dtoes;

public class EngineerDtoCred {
	
	private String email;
	private String password;
	
	public EngineerDtoCred() {
		// TODO Auto-generated constructor stub
	}

	public EngineerDtoCred(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EngineerDtoCred [email=" + email + ", password=" + password + "]";
	}

}
