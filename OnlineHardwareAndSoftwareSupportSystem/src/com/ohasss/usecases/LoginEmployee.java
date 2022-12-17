package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.model.Employee;
import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.exceptions.EmployeeException;

public class LoginEmployee {

	public static void main() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter employee email: ");
		String email = sc.next();
		
		System.out.print("Enter employee password: ");
		String pass = sc.next();
		
		Employee emp = new Employee(email, pass);
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		try {
			String loginStatus = dao.loginEmployee(emp);
			System.out.println(loginStatus);
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}