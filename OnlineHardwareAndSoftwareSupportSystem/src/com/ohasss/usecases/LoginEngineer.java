package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.model.Engineer;
import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class LoginEngineer {

	public static boolean main() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter engineer email: ");
		String email = sc.next();
		
		System.out.print("Enter engineer password: ");
		String pass = sc.next();
		
		Engineer eng = new Engineer(email,pass);
		
		EngineerDao dao = new EngineerDaoImpl();
		String mString = "";
		String loginStatus = "";
		try {
			loginStatus = dao.login(eng);
			if(loginStatus.equalsIgnoreCase("\nWrong credentials..!\n")) {
				System.out.println(loginStatus);
				return false;
			}
			System.out.println(loginStatus);
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
}