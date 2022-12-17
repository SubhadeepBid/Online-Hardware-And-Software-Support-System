package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class DeleteAnEngineer {

	public static void main() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter engineer id: ");
		int engid = sc.nextInt();
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String removeStatus = dao.removeEngineer(engid);
			System.out.println(removeStatus);
		} catch (EngineerException e) {
			System.out.println("\nNo such Engineer found..!\n");
		}
		
		
	}
}