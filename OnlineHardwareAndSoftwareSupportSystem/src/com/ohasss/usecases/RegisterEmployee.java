package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.model.Employee;
import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.exceptions.EmployeeException;

public class RegisterEmployee {

	public static void main() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter employee name: ");
		String name = sc.next();
		
		System.out.print("Enter employee email: ");
		String email = sc.next();
		
		System.out.print("Enter employee password: ");
		String password = sc.next();
		
		Employee emp = new Employee(name,email,password);
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		try {
			String registrationStatus = dao.registerEmployee(emp);
			System.out.println(registrationStatus);
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
		
	}
}