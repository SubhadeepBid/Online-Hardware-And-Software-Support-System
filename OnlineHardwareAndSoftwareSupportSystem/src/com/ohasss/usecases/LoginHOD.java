package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.HODException;

public class LoginHOD {
	
	public static boolean main() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter hod username: ");
		String user = sc.next();
		
		System.out.print("Enter hod password: ");
		String pass = sc.next();
		
		HODDao dao = new HODDaoImpl();
		
		String loginStatus="";
		
		try {
			loginStatus = dao.loginHOD(user,pass);
			System.out.println(loginStatus);
		} catch (HODException e) {
			System.out.println(e.getMessage());
		}
		
		if(loginStatus.equalsIgnoreCase("\nWrong credentials!\n")) {
			return false;
		}
		return true;
	}
}