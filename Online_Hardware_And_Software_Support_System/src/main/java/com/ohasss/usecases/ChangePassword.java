package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class ChangePassword {
	public static void changePass(int engID) {
        Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter New Password : ");
		String newPassword = sc.next();
		
		System.out.print("Enter Old Password : ");
		String oldPassword = sc.next();
		
		EngineerDao dao = new EngineerDaoImpl();
		
		try {
			String result = dao.changePassword(engID, newPassword, oldPassword);
			System.out.println("\n" + result);
		} catch (EngineerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	/*
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Engineer ID : ");
		int engID = sc.nextInt();
		
		System.out.print("Enter New Password : ");
		String newPassword = sc.next();
		
		EngineerDao dao = new EngineerDaoImpl();
		
		try {
			String result = dao.changePassword(engID, newPassword);
			System.out.println(result);
		} catch (EngineerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
