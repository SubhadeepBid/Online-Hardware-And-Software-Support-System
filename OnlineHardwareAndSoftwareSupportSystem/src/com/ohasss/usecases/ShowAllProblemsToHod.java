package com.ohasss.usecases;

import java.util.List;

import com.ohasss.model.Problem_Engineer;
import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.ComplainException;

public class ShowAllProblemsToHod {

	public static void main() {
		
		
		HODDao dao = new HODDaoImpl();
		
		
		try {
			List<Problem_Engineer> pList = dao.viewAllProblems();
			
			pList.forEach(p -> System.out.println(p));
			
		} catch (ComplainException e) {
			System.out.println(e.getMessage());
		}
		
	}
}