package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.dtoes.EngineerDtoCred;
import com.ohasss.exceptions.EngineerException;

public class LoginEngineer {
	public static int loginEngg() {
        Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Your Email : ");
		String email = sc.next();
		
		System.out.print("Enter Your Password : ");
		String password = sc.next();
		
		EngineerDtoCred cred = new EngineerDtoCred(email, password);
		
		EngineerDao dao = new EngineerDaoImpl();
		
		int engID = 0;
		
		try {
			engID = dao.loginEngineer(cred);
			if(engID != 0)
				System.out.println("Logged In Successfully...");
			
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
		
		return engID;
	}
	/*
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Your Email : ");
		String email = sc.next();
		
		System.out.print("Enter Your Password : ");
		String password = sc.next();
		
		EngineerDtoCred cred = new EngineerDtoCred(email, password);
		
		EngineerDao dao = new EngineerDaoImpl();
		
		int engID = 0;
		
		try {
			engID = dao.loginEngineer(cred);
			if(engID != 0)
				System.out.println("Logged In Successfully...");
			
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
		
		return engID;
	}
	*/
}
