package com.ohasss.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.dtoes.ProblemHODDtoPA;
import com.ohasss.dtoes.ProblemHODDtoPNA;
import com.ohasss.exceptions.ProblemException;

public class ShowAllComplaints {
	
	public static void showAllCompl() {
		Scanner sc = new Scanner(System.in);
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("H", "HARDWARE");
		map.put("S", "SOFTWARE");
		
		System.out.println("\n\n"
				+ "______________________________________________\n"
				+ "|                                            |\n"
				+ "| Please Select Correct Option               |\n"
				+ "|   1 - Complaints Assigned To Engineers     |\n"
				+ "|   2 - Complaints Not Assigned To Engineers |\n"
				+ "|   3 - Exit                                 |\n"
				+ "|____________________________________________|\n");
		System.out.print("Enter Your Choice : ");
		int ch = sc.nextInt();
		
		if(ch == 1) {
			System.out.print("\nEnter Category (H - HARDWARE / S - SOFTWARE) : ");
			String s = sc.next();
			
			HODDao dao = new HODDaoImpl();
			List<ProblemHODDtoPA> dtoPAs = new ArrayList<>();
			
			try {
				dtoPAs = dao.showAllProblemsAssignedToEngineers(map.get(s));
				
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%-20s%-30s%-20s%-30s%-20s%-30s%-20s%-30s%-20s\n", "ComplainID", "Description", "Launched By", "Launched On", "Priority", "Engineer Assigned", "Status", "StartedWorking", "ClosedOn");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				
				dtoPAs.forEach(d -> {
					System.out.printf("%-20d%-30s%-20d%-30s%-20s%-30d%-20s%-30s%-20s\n", d.getLogID(), d.getProbDesc(), d.getEmpID(), d.getLaunchedOn(), d.getPriority(), d.getEngID(), d.getStatus(), d.getStartedWorking(), d.getClosedOn());
				});
			} catch (ProblemException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		else if(ch == 2) {
			System.out.print("\nEnter Category (H - HARDWARE / S - SOFTWARE) : ");
			String s = sc.next();
			
			HODDao dao = new HODDaoImpl();
			List<ProblemHODDtoPNA> dtoPNAs = new ArrayList<>();
			
			try {
				dtoPNAs = dao.showAllProblemsNotAssignedToEngineers(map.get(s));
				
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.printf("%-12s%-20s%-20s%-20s%-20s\n", "ComplainID", "Description", "Launched By", "Priority", "Launched On");
				System.out.println("---------------------------------------------------------------------------------------------");
				
				dtoPNAs.forEach(i -> {
					System.out.printf("%-12d%-20s%-20d%-20s%-20s\n", i.getLogID(), i.getProbDesc(), i.getEmpID(), i.getPriority(), i.getLaunchedOn());
				});
			} catch (ProblemException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		else if(ch != 3) {
			System.out.println("Wrong Choice...");
			showAllCompl();
		}
		
	}
	
//	public static void main(String[] args) {
//		showAllCompl();
//		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s\n", "ComplainID", "Description", "Launched By", "Launched On", "Priority", "Engineer Assigned", "Status", "StartedWorking", "ClosedOn");
//		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		
//	}

}
