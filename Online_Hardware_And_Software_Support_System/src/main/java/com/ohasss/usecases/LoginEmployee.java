package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.dtoes.EmployeeDtoCred;
import com.ohasss.exceptions.EmployeeException;

public class LoginEmployee {
	public static int loginEmp() {
        Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Email : ");
		String email = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		EmployeeDtoCred cred = new EmployeeDtoCred(email, password);
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		int empID = 0;
		
		try {
			empID = dao.loginEmployee(cred);
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
		return empID;
	}
	/*
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Email : ");
		String email = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		EmployeeDtoCred cred = new EmployeeDtoCred(email, password);
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		try {
			String result = dao.loginEmployee(cred);
			System.out.println(result);
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
		
	}
	*/
}
