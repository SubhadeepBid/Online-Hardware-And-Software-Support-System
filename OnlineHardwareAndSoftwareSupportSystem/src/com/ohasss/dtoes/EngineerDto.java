package com.ohasss.dtoes;

public class EngineerDto {
	
	private int engID;
	private String name;
	private String email;
	private String category;
	
	public EngineerDto() {
		// TODO Auto-generated constructor stub
	}

	public EngineerDto(int engID, String name, String email, String category) {
		super();
		this.engID = engID;
		this.name = name;
		this.email = email;
		this.category = category;
	}

	public int getEngID() {
		return engID;
	}

	public void setEngID(int engID) {
		this.engID = engID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "EngineerDto [engID=" + engID + ", name=" + name + ", email=" + email + ", category=" + category + "]";
	}

}
