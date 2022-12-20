package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.dtoes.HODDto;
import com.ohasss.exceptions.HODException;

public class LoginHOD {
	public static boolean logHOD() {
		boolean flag = false;
        Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter email : ");
		String email = sc.next();
		
		System.out.print("Enter password : ");
		String password = sc.next();
		
		HODDto hodDto = new HODDto(email, password);
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String result = dao.loginHOD(hodDto);
			System.out.println("\n" + result);
			if(result == "Logged In Successfully...")
				flag = true;
		} catch (HODException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return flag;
	}
	/*
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter email : ");
		String email = sc.next();
		
		System.out.print("Enter password : ");
		String password = sc.next();
		
		HODDto hodDto = new HODDto(email, password);
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String result = dao.loginHOD(hodDto);
			System.out.println(result);
		} catch (HODException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	*/

}
