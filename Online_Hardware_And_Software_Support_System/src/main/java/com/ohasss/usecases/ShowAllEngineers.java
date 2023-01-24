package com.ohasss.usecases;

import java.util.List;

import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.dtoes.EngineerDto;
import com.ohasss.exceptions.EngineerException;

public class ShowAllEngineers {
	public static void showAllEngg() {
        HODDao dao = new HODDaoImpl();
		
		try {
			System.out.println("------------------------------------------------------------");
			System.out.printf("%-12s%-20s%-20s%-20s\n", "engID", "Name", "Email", "Category");
			System.out.println("------------------------------------------------------------");
			
			List<EngineerDto> engineerDtos = dao.showAllEngineers();
			engineerDtos.forEach(i -> {
				System.out.printf("%-12d%-20s%-20s%-20s\n", i.getEngID(), i.getName(), i.getEmail(), i.getCategory());
			});
		} catch (EngineerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	/*
	public static void main(String[] args) {
		HODDao dao = new HODDaoImpl();
		
		try {
			System.out.println("------------------------------------------------------------");
			System.out.printf("%-12s%-20s%-20s%-20s\n", "engID", "Name", "Email", "Category");
			System.out.println("------------------------------------------------------------");
			
			List<EngineerDto> engineerDtos = dao.showAllEngineers();
			engineerDtos.forEach(i -> {
				System.out.printf("%-12d%-20s%-20s%-20s\n", i.getEngID(), i.getName(), i.getEmail(), i.getCategory());
			});
		} catch (EngineerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	*/

}
