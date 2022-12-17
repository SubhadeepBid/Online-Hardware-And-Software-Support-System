package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.model.Engineer;
import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class RegisterNewEngineer {
	
	public static void main() {		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter engineer email: ");
		String email = sc.next();
		
		System.out.print("Enter engineer password: ");
		String password = sc.next();
		
		System.out.print("Enter engineer name: ");
		String name = sc.next();

		System.out.print("Enter engineer category: ");
		String category = sc.next();
		
		Engineer eng = new Engineer(name,email,password,category);
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String registerEngineerStatus = dao.registerNewEngineer(eng);
			System.out.println(registerEngineerStatus);
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
		
	}
}