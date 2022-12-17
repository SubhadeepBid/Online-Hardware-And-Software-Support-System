package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.EngineerDao;
import com.ohasss.dao.EngineerDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class EngineerUpdateProblemStatus {

	public static void main() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter problem id: ");
		int pid = scanner.nextInt();
		
		System.out.print("Enter engineer id: ");
		int engid = scanner.nextInt();
		
		EngineerDao dao = new EngineerDaoImpl();
		
		try {
			String updatedProblemStatus = dao.updateComplainStatus(pid, engid);
			System.out.println(updatedProblemStatus);
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}