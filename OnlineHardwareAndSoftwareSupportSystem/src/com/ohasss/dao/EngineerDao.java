package com.ohasss.dao;

import java.util.List;

import com.ohasss.exceptions.EngineerException;
import com.ohasss.model.Engineer;
import com.ohasss.model.Problem_Engineer;

public interface EngineerDao {
	
    public String login(Engineer e) throws EngineerException;
	
	public List<Problem_Engineer> viewProblemsAssigned(int engid) throws EngineerException;
	
	public String updateComplainStatus(int pid,int engid) throws EngineerException;
	
	public String changePassword(String email,String password) throws EngineerException;

}
