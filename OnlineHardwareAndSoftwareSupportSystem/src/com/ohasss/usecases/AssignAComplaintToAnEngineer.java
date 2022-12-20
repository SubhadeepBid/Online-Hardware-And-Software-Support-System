package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.ProblemException;

public class AssignAComplaintToAnEngineer {
	
	public static void assignAComplToAnEngg() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Engineer ID : ");
		int engID = sc.nextInt();
		
		System.out.print("Enter Complaint ID : ");
		int logID = sc.nextInt();
		
		System.out.print("Enter Your Password : ");
		String password = sc.next();
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String result = dao.assignAProblemToAnEngineer(engID, logID, password);
			System.out.println("\n" + result);
		} catch (ProblemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
