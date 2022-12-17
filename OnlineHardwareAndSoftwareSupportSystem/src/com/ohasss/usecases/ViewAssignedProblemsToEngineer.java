package com.ohasss.usecases;

import java.util.List;
import java.util.Scanner;

import com.ohasss.model.Problem_Engineer;
import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class ViewAssignedProblemsToEngineer {

	public static void main() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter engineer id: ");
		int engid = scanner.nextInt();
		
		EngineerDao dao = new EngineerDaoImpl();
		
		try {
			List<Problem_Engineer> list = dao.viewProblemsAssigned(engid);
			
			
			list.forEach(p -> System.out.println(p));
			
			
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
	}
}