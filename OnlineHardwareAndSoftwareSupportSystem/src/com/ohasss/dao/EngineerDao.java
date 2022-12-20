package com.ohasss.dao;

import java.util.List;

import com.ohasss.dtoes.EngineerDtoCred;
import com.ohasss.dtoes.ProblemEnggDto;
import com.ohasss.exceptions.EngineerException;
import com.ohasss.exceptions.ProblemException;

public interface EngineerDao {
	
	public int loginEngineer(EngineerDtoCred cred) throws EngineerException;
	
	public List<ProblemEnggDto> showProblemsAssigned(int engID, String status) throws ProblemException;
	
	public String updateProblemStatus(int complainID, String status, int engID, String password) throws ProblemException;
	
	public String changePassword(int engID, String newPassword, String oldPassword) throws EngineerException;

}
