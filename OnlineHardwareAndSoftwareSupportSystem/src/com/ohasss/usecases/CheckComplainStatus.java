package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.exceptions.ComplainException;

public class CheckComplainStatus {

	public static void main() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter problem id: ");
		int pid = scanner.nextInt();
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		try {
			String problemStatus = dao.checkComplainStatus(pid);
			System.out.println(problemStatus);
		} catch (ComplainException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}