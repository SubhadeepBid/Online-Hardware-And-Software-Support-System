package com.ohasss.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.exceptions.ProblemException;
import com.ohasss.model.Problem;

public class RegisterAComplaint {
	
	public static void registerAComp(int empID) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Category (HARDWARE - H / SOFTWARE - S) : ");
		String ch = sc.next();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("H", "HARDWARE");
		map.put("S", "SOFTWARE");
		
		List<Problem> problems = new ArrayList<>();
		
		EmployeeDaoImpl daoImpl = new EmployeeDaoImpl();
		
		try {
			problems = daoImpl.getProblems(map.get(ch));
			
//			int n = problems.size();
			
			Map<Integer, Problem> map2 = new HashMap<Integer, Problem>();
			int j = 0;
			
			System.out.println("\n");
			for(Problem p : problems) {
				map2.put(++j, p);
				System.out.println("  " + j + " - " + p.getProbDesc());
			}
			
			System.out.println("  " + ++j + " - OTHERS");
			System.out.print("Choose The Complain : ");
			int ch1 = sc.nextInt();
			
			System.out.print("Enter Your Password : ");
			String password = sc.next();
			
			EmployeeDao dao = new EmployeeDaoImpl();
			
			if(ch1 == j) {
				System.out.print("Enter Problem Description : ");
				String probDesc = sc.next();
				String message = dao.registerANewComplain(empID, password, probDesc, map.get(ch));
				System.out.println("\n" + message);
			}
			else if(ch1 >= 0 && ch1 < j) {
				String message = dao.registerAComplain(empID, password, map2.get(ch1).getProbID());
				System.out.println("\n" + message);
			}
			else {
				System.out.println("\nWrong Choice...");
			}
			
		} catch (ProblemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
