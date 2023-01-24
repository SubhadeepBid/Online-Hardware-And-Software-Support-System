package com.ohasss.dtoes;

public class HODDto {
	
	private String email;
	private String password;
	
	public HODDto() {
		// TODO Auto-generated constructor stub
	}

	public HODDto(String email, String password) {
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
		return "HODDto [email=" + email + ", password=" + password + "]";
	}
	
}
