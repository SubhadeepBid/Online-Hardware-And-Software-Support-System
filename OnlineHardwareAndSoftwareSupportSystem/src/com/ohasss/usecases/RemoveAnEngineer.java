package com.ohasss.usecases;

import java.util.Scanner;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class RemoveAnEngineer {
	public static void removeAnEngg() {
        Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Engineer ID : ");
		int engID = sc.nextInt();
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String result = dao.removeAnEngineer(engID);
			System.out.println(result);
		} catch (EngineerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	/*
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Engineer ID : ");
		int engID = sc.nextInt();
		
		HODDao dao = new HODDaoImpl();
		
		try {
			String result = dao.removeAnEngineer(engID);
			System.out.println(result);
		} catch (EngineerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	*/
}
