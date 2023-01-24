package com.ohasss.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.ProblemException;
import com.ohasss.model.Problem_Temp;

public class GetAndSetNewProblem {
	public static void setPriorityOfAComp() {
		Scanner sc = new Scanner(System.in);
		
		List<Problem_Temp> temps = new ArrayList<>();
		
		HODDao dao = new HODDaoImpl();
		
		try {
			temps = dao.getNewProblemDetails();
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf("%-20s%-20s%-20s%-20s%15s\n", "ProblemID", "Description", "Category", "LaunchedOn", "Launched By");
			System.out.println("------------------------------------------------------------------------------------------------");
			
			Map<Integer, Problem_Temp> map = new HashMap<Integer, Problem_Temp>();
			
			temps.forEach(t -> {
				System.out.printf("%-20d%-20s%-20s%-20s%15s\n", t.getProb_temp_id(), t.getProbDesc(), t.getCategory(), t.getLaunchedOn(), t.getEmpID());
				map.put(t.getProb_temp_id(), t);
			});
			
			System.out.print("\n\nDo You Want To Add The Problem To The List (Y / N) : ");
			Character ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				Map<Character, String> map2 = new HashMap<Character, String>();
				
				map2.put('H', "HIGH");
				map2.put('M', "MED");
				map2.put('L', "LOW");
				
				System.out.print("Enter ProblemID : ");
				int ch1 = sc.nextInt(); 
				
				System.out.print("Enter Priority (H - HIGH / M - MED / L - LOW): ");
//				String ch2 = sc.next();
				Character ch2 = sc.next().toUpperCase().charAt(0);
				
				System.out.print("Enter Your Password : ");
				String password = sc.next(); 
				if(ch2 == 'H' || ch2 == 'M' || ch2 == 'L') {
				    String result = dao.setPriorityOfANewProblemLaunched(map.get(ch1), map2.get(ch2), password);
				    System.out.println("\n" + result);
				}
				else {
					System.out.println("Wrong Choice...");
				}
			}
			else if(ch != 'N') {
				System.out.println("...");
			}
			else {
				System.out.println("Wrong Choice...");
			}
		} catch (ProblemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
