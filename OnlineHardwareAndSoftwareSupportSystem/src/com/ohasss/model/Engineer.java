package com.ohasss.model;

public class Engineer {
	
	private int engID;
	private String name;
	private String email;
	private String password;
	private String category;
	
	public Engineer() {
		// TODO Auto-generated constructor stub
	}

	public Engineer(int engID, String name, String email, String password, String category) {
		super();
		this.engID = engID;
		this.name = name;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Engineer [engID=" + engID + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", category=" + category + "]";
	}

}

/*
mysql> desc engineer;
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| engID    | int          | NO   | PRI | NULL    | auto_increment |
| name     | varchar(255) | NO   |     | NULL    |                |
| email    | varchar(255) | NO   | UNI | NULL    |                |
| password | varchar(255) | NO   |     | NULL    |                |
| category | varchar(255) | NO   |     | NULL    |                |
+----------+--------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)

mysql> select * from engineer;
+-------+-------------+---------------+----------+----------+
| engID | name        | email         | password | category |
+-------+-------------+---------------+----------+----------+
|  1000 | SUMAN SINGH | suman@abc.com | suman123 | SOFTWARE |
+-------+-------------+---------------+----------+----------+
1 row in set (0.00 sec)
*/