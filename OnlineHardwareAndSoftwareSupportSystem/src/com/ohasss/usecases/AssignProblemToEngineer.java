package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.model.Problem;
import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.ComplainException;
import com.ohasss.exceptions.EngineerException;

public class AssignProblemToEngineer {

	public static void main() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter problem id: ");
		int pid = scanner.nextInt();
		
		System.out.print("Enter engineer id: ");
		int eid = scanner.nextInt();
		
		Problem problem = new Problem(pid);
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String assignStatus = dao.assignComplainToEngineer(problem, eid);
			System.out.println(assignStatus);
		} catch (EngineerException | ComplainException e) {
			System.out.println(e.getMessage());
		} 
		
	}
}
