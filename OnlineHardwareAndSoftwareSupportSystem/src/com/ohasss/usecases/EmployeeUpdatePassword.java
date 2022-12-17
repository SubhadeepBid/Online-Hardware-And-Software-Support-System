package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.exceptions.EmployeeException;


public class EmployeeUpdatePassword {

public static void main() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter employee username: ");
		String email= sc.next();
		
		System.out.print("Enter new password: ");
		String newPass = sc.next();
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		try {
			String passwordUpdate = dao.changePassword(email,newPass);
			System.out.println(passwordUpdate);
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}