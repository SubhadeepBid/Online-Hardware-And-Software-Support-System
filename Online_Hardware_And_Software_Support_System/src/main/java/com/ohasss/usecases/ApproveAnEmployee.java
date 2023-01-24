package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.EmployeeException;

public class ApproveAnEmployee {
	
	public static void approveAnEmp() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Employee ID : ");
		int empID = sc.nextInt();
		
		System.out.print("Enter Your Password : ");
		String password = sc.next();
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String result = dao.approveAnEmployee(empID, password);
			System.out.println("\n" + result);
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
