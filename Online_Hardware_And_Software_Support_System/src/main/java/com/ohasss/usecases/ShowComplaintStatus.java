package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.exceptions.ProblemException;

public class ShowComplaintStatus {
	
	public static void showCompStat(int empID) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Complaint ID : ");
		int logID = sc.nextInt();
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		try {
			String result = dao.showComplaintStatus(logID, empID);
			System.out.println("\n" + result);
		} catch (ProblemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
