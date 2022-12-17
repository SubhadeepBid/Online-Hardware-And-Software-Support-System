package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;

public class Main {
	
	public static void m() {

		System.out.println("________________________________________\n"
						+ "|                                       |\n"
						+ "|    ---Welcome to OHS System---        |\n"
						+ "|  Select a user to proceed:            |\n"
						+ "|  1. HOD                               |\n"
						+ "|  2. Engineer                          |\n"
						+ "|  3. Employee                          |\n"
						+ "|  4. Exit                              |\n"
						+ "|_______________________________________|\n");
		
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			m();
			int n = sc.nextInt();
			
				// 1. HOD
				if(n==1) {
					System.out.println("\nPlease login to continue...\n");
					
					if(LoginHOD.main()) { //boolean
						while(true) {
							
							System.out.println("________________________________________\n"
											+ "|                                       |\n"
											+ "|  1. Register a new Engineer           |\n"
											+ "|  2. Show List of Engineers            |\n"
											+ "|  3. Remove an Engineer                |\n"
											+ "|  4. Show All Problems                 |\n"
											+ "|  5. Assign a Problem to An Engineer   |\n"
											+ "|  6. Exit                              |\n"
											+ "|_______________________________________|\n");
							
							
							int nhod = sc.nextInt(); 
							if(nhod==1) {	
								RegisterNewEngineer.main();
							}else if(nhod==2) {
								ShowListOfAllEngineers.main();
							}else if(nhod==3) {
								DeleteAnEngineer.main();
							}else if(nhod==4) {
								ShowAllProblemsToHod.main();
							}else if(nhod==5) {
								AssignProblemToEngineer.main();
							}else if(nhod==6) {
								break;
							}else {
								System.out.println("\nInvalid input..!\n");
							}
							
						}
					}
				}
				
				// 2. Engineer
				else if(n==2){
					
					System.out.println("\nPlease login to continue...\n");
					
					if(LoginEngineer.main()){
						while(true) {
							
							System.out.println("________________________________________\n"
											+ "|                                       |\n"
											+ "|  1. View assigned problems by HOD     |\n"
											+ "|  2. Update the status of a problem    |\n"
											+ "|  3. Change password                   |\n"
											+ "|  4. Exit                              |\n"
											+ "|_______________________________________|\n");
							
							int nhod = sc.nextInt(); 
							if(nhod==1) {	
								ViewAssignedProblemsToEngineer.main();
							}else if(nhod==2) {
								EngineerUpdateProblemStatus.main();
							}else if(nhod==3) {
								EngineerUpdatePassword.main();
							}else if(nhod==4) {
								break;
							}else {
								System.out.println("\nInvalid input..!\n");
							}
							
						}
					}
				}
				
				// 3. Employee
				else if(n==3){
					while(true) {
						System.out.println("________________________________________\n"
										+ "|                                       |\n"
										+ "|  1. Register a new Employee           |\n"
										+ "|  2. Login a new Employee              |\n"
										+ "|  3. Exit                              |\n"
										+ "|_______________________________________|\n");
						int nhod = sc.nextInt();
						if(nhod==1) {	
							RegisterEmployee.main();
						}else if(nhod==2) {
							LoginEmployee.main();
							while(true) {
								sc.nextLine();
								System.out.println("________________________________________\n"
												+ "|                                       |\n"
												+ "|  1. Register a new complaint          |\n"
												+ "|  2. Check status of a problem         |\n"
												+ "|  3. View all complaint history        |\n"
												+ "|  4. Change password                   |\n"
												+ "|  5. Exit                              |\n"
												+ "|_______________________________________|\n");
								
								int nemp = sc.nextInt();
								if(nemp==1) {
									RegisterNewProblem.main();
								}else if(nemp==2) {
									CheckComplainStatus.main();
								}else if(nemp==3) {
									CheckComplainHistory.main();
								}else if(nemp==4) {
									EmployeeUpdatePassword.main();
								}else if(nemp==5) {
									break;
								}else {
									System.out.println("\nInvalid Input..!\n");
								}
							}
							
							
						}else if(nhod==3){
							break;
						}else {
							System.out.println("\nInvalid Input..!\n");;
						}
						
						
					}
				}
				
				else if(n==4){
					
					System.out.println("\nThank you..!\n");
					break;
				}
				else {
					System.out.println("\nInvalid Input..!\n");
				}
				
			}
			
		}
		
		
	
}