package com.ohasss.usecases;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.exceptions.ProblemException;

public class UpdateProblemStatus {
	
	public static void updateProbStat(int engID) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter ComplaintID : ");
		int complainID = sc.nextInt();
		
		EngineerDaoImpl daoImpl = new EngineerDaoImpl();
		
		try {
			String res = daoImpl.getProblemStatus(complainID, engID);
			
			if(!(res.equals("No Problems Found..."))) {
				EngineerDao dao = new EngineerDaoImpl();
				
				if(res.equals("OPEN")) {
					System.out.print("Enter The Status (1 - IN PROGRESS / 2 - CLOSED) : ");
					int stat = sc.nextInt();
					
					System.out.print("Enter Your Password : ");
					String password = sc.next();
					
					Map<Integer, String> map = new HashMap<Integer, String>();
					
					map.put(1, "IN PROGRESS");
					map.put(2, "CLOSED");
					
					try {
						String result = dao.updateProblemStatus(complainID, map.get(stat), engID, password);
						System.out.println("\n" + result);
					} catch (ProblemException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
			    else if(res.equals("IN PROGRESS")) {
					System.out.print("Do You Want To Close The Complaint? (Y / N) : ");
					String s = sc.next();
					
					if(s.equals("Y") || s.equals("y")) {
						System.out.print("Enter Your Password : ");
						String password = sc.next();
						
						try {
							String result = dao.updateProblemStatus(complainID, "CLOSED", engID, password);
							System.out.println("\n" + result);
						} catch (ProblemException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
					}
				}
				else if(res.equals("CLOSED")) {
					System.out.println("\nComplaint Is Closed...");
				}
			}
		} catch (ProblemException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
	}

}

/*
public String updateProblemStatus(int complainID, String status, int engID, String password) throws ProblemException;
*/