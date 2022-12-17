package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class EngineerUpdatePassword {

	public static void main() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter engineer username: ");
		String email= sc.next();
		
		System.out.print("Enter new password: ");
		String newPass = sc.next();
		
		
		EngineerDao dao = new EngineerDaoImpl();
		
		try {
			String passwordUpdate = dao.changePassword(email,newPass);
			System.out.println(passwordUpdate);
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}