package com.ohasss.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.dtoes.ProblemEnggDto;
import com.ohasss.exceptions.ProblemException;

public class ShowProblemsAssigned {

	public static void showProbAssign(int engID) {
		Scanner sc = new Scanner(System.in);
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		map.put(1, "OPEN");
		map.put(2, "IN PROGRESS");
		map.put(3, "CLOSED");
		map.put(4, "ALL");
		
		System.out.println("\n\n"
				+ "________________________________\n"
				+ "|                              |\n"
				+ "| Please Select Correct Option |\n"
				+ "|   1 - OPEN                   |\n"
				+ "|   2 - IN PROGRESS            |\n"
				+ "|   3 - CLOSED                 |\n"
				+ "|   4 - ALL                    |\n"
				+ "|   5 - Exit                   |\n"
				+ "|______________________________|\n");
		System.out.print("Enter Your Choice : ");
		int ch = sc.nextInt();
		
		if(ch >= 1 && ch < 5) {
			List<ProblemEnggDto> dtos = new ArrayList<>();
			
			EngineerDao dao = new EngineerDaoImpl();
			
			try {
				dtos = dao.showProblemsAssigned(engID, map.get(ch));
				System.out.println("--------------------------------------------------------------------------");
				System.out.printf("%-18s%-25s%-20s%-20s\n", "ComplainID", "Description", "Priority", "Launched By");
				System.out.println("--------------------------------------------------------------------------");
				dtos.forEach(i -> {
					System.out.printf("%-18d%-25s%-20s%-20d\n", i.getLogID(), i.getProbDesc(), i.getPriority(), i.getEmpID());
				});
			} catch (ProblemException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		else if(ch != 5) {
			System.out.println("Wrong Choice...");
			showProbAssign(engID);
		}
		
	}

}