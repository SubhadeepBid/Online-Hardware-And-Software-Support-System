package com.ohasss.usecases;

import java.util.List;

import com.ohasss.model.Engineer;
import com.ohasss.dao.HODDao;
import com.ohasss.dao.HODDaoImpl;
import com.ohasss.exceptions.EngineerException;

public class ShowListOfAllEngineers {

     public static void main() {
    	 
    	 HODDao dao = new HODDaoImpl();
    	 
    	 try {
			List<Engineer> engineersList = dao.listAllRegisteredEngineers();
			
			engineersList.forEach(e -> System.out.println(e));
		} catch (EngineerException e) {
			
			System.out.println(e.getMessage());
		}
    	 
     }
     
}