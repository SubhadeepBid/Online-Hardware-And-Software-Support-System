package com.ohasss.dao;

import java.util.List;

import com.ohasss.dtoes.EmployeeHODDto;
import com.ohasss.dtoes.EngineerDto;
import com.ohasss.dtoes.HODDto;
import com.ohasss.dtoes.ProblemHODDtoPA;
import com.ohasss.dtoes.ProblemHODDtoPNA;
import com.ohasss.exceptions.EmployeeException;
import com.ohasss.exceptions.EngineerException;
import com.ohasss.exceptions.HODException;
import com.ohasss.exceptions.ProblemException;
import com.ohasss.model.Engineer;
import com.ohasss.model.Problem_Temp;

public interface HODDao {
	
	public String loginHOD(HODDto hodDto) throws HODException;
	
	public String registerAnEngineer(Engineer engineer) throws EngineerException;
	
	public List<EngineerDto> showAllEngineers() throws EngineerException;
	
	public String removeAnEngineer(int engID) throws EngineerException;
	
	// GET ALL PROBLEMS OPEN - IN PROGRESS - CLOSED
	public List<ProblemHODDtoPNA> showAllProblemsNotAssignedToEngineers(String category) throws ProblemException;
	
	public List<ProblemHODDtoPA> showAllProblemsAssignedToEngineers(String category) throws ProblemException;
	
	// ASSIGN ANY PROBLEM TO ANY ENGINEER [NOTE SOFTWARE PROBLEMS TO SOFTWARE ENGG. & HARDWARE PROBLEMS TO HARDWARE ENGG.]
	public String assignAProblemToAnEngineer(int engID, int logID, String password) throws ProblemException;
	
	public List<EmployeeHODDto> showAllUnVerifiedEmployee() throws EmployeeException;
	// APPROVE AN EMPLOYEE
	public String approveAnEmployee(int empID, String password) throws EmployeeException;
	
	// SET THE PRIORITY OF A PROBLEM THAT IS NOT LISTED
	public String setPriorityOfANewProblemLaunched(Problem_Temp pTemp, String priority, String password) throws ProblemException;
	
	public List<Problem_Temp> getNewProblemDetails() throws ProblemException;

}
