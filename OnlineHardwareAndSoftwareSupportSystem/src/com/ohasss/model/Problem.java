package com.ohasss.model;

public class Problem {
	
	private int probID;
	private String probDesc;
	private String category;
	private String priority;
	
	public Problem() {
		// TODO Auto-generated constructor stub
	}

	public Problem(int probID, String probDesc, String category, String priority) {
		super();
		this.probID = probID;
		this.probDesc = probDesc;
		this.category = category;
		this.priority = priority;
	}

	public int getProbID() {
		return probID;
	}

	public void setProbID(int probID) {
		this.probID = probID;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Problem [probID=" + probID + ", probDesc=" + probDesc + ", category=" + category + ", priority="
				+ priority + "]";
	}

}

/*
mysql> desc problem_temp;
+------------+--------------+------+-----+---------+-------+
| Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| probDesc   | varchar(255) | YES  |     | NULL    |       |
| category   | varchar(255) | YES  |     | NULL    |       |
| LaunchedOn | timestamp    | YES  |     | NULL    |       |
| empID      | int          | YES  | MUL | NULL    |       |
+------------+--------------+------+-----+---------+-------+
4 rows in set (0.00 sec)



mysql> desc problem;
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| probID   | int          | NO   | PRI | NULL    | auto_increment |
| probDesc | varchar(255) | NO   |     | NULL    |                |
| category | varchar(255) | NO   |     | NULL    |                |
| priority | varchar(255) | NO   |     | NULL    |                |
+----------+--------------+------+-----+---------+----------------+
4 rows in set (0.00 sec)*/