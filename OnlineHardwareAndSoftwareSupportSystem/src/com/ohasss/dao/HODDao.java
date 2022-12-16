package com.ohasss.dao;

import java.util.List;

import com.ohasss.exceptions.ComplainException;
import com.ohasss.exceptions.EngineerException;
import com.ohasss.exceptions.HODException;
import com.ohasss.model.Engineer;
import com.ohasss.model.Problem;
import com.ohasss.model.Problem_Engineer;

public interface HODDao {
	
public String loginHOD(String username,String pass) throws HODException;
	
	public String registerNewEngineer(Engineer eng) throws EngineerException;
	
	public List<Engineer> listAllRegisteredEngineers() throws EngineerException;
	
	public String removeEngineer(int engid) throws EngineerException;
	
	public List<Problem_Engineer> viewAllProblems() throws ComplainException;

	public String assignComplainToEngineer(Problem prob, int engid) throws ComplainException, EngineerException;

}
