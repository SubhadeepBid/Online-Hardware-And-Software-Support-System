package com.ohasss.dao;

import java.util.List;

import com.ohasss.exceptions.ComplainException;
import com.ohasss.exceptions.EmployeeException;
import com.ohasss.model.Employee;
import com.ohasss.model.Problem_Engineer;

public interface EmployeeDao {
	
	public String registerEmployee(Employee emp) throws EmployeeException;

	public String loginEmployee(Employee emp) throws EmployeeException;

	public String changePassword(String email, String password) throws EmployeeException;
	
	public String registerComplain(int empid,String category,String name) throws ComplainException;

	public List<Problem_Engineer> checkComplainHistory(Employee emp) throws EmployeeException, ComplainException;

	public String checkComplainStatus(int problemid) throws ComplainException;

}
