package com.ohasss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ohasss.dtoes.EmployeeDtoCred;
import com.ohasss.dtoes.EmployeeDtoReg;
import com.ohasss.dtoes.ProblemEmpHistDto;
import com.ohasss.exceptions.EmployeeException;
import com.ohasss.exceptions.EngineerException;
import com.ohasss.exceptions.ProblemException;
import com.ohasss.model.Problem;
import com.ohasss.utility.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String registerEmployee(EmployeeDtoReg dtoReg) throws EmployeeException {
		String message = "Fill Your Details Correctly...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO EMPLOYEE (name, email, password, status) VALUES(?,?,?,?)");
			
			ps.setString(1, dtoReg.getName());
			ps.setString(2, dtoReg.getEmail());
			ps.setString(3, dtoReg.getPassword());
			ps.setString(4, dtoReg.getStatus());
			
			int x = ps.executeUpdate();
			
			if(x > 0) 
				message = "Employee Registered Successfully...\nWait For HOD Approval...";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public int loginEmployee(EmployeeDtoCred cred) throws EmployeeException {
		int empID = 0;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT empID, STATUS FROM EMPLOYEE WHERE email = ? AND password = ?");
			
			ps.setString(1, cred.getEmail());
			ps.setString(2, cred.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String status = rs.getString("status");
				if(status.equals("UNVERIFIED")) {
					System.out.println("\nYour Account Is Not Verified By HOD...\nPlease Wait For Approval...");
				}
				else if(status.equals("VERIFIED")) {
					System.out.println("Logged In Successfully...");
					empID = rs.getInt("empID");
				}
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		return empID;
	}


	
	public boolean checkEmployee(int empID, String password) throws EmployeeException {
		boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT STATUS FROM EMPLOYEE WHERE empID = ? AND password = ?");
			
			ps.setInt(1, empID);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		return flag;
	}
	
	public int getComplaintID(int empID, int probID) throws ProblemException {
		int logID = 0;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT logID FROM PROBLEM_LOG WHERE empID = ? AND probID = ? AND status = 'OPEN' ORDER BY LaunchedOn DESC LIMIT 1");
			
			ps.setInt(1, empID);
			ps.setInt(2, probID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				logID = rs.getInt("logID");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		return logID;
	}
	
	@Override
	public String registerAComplain(int empID, String password, int probID) throws ProblemException {
		String message = "Complaint Not Registered...\\nWrong Password Entered or Other";
		
		try {
			if(checkEmployee(empID, password)) {
				try(Connection conn = DBUtil.provideConnection()) {
					PreparedStatement ps = conn.prepareStatement("INSERT INTO PROBLEM_LOG (probID, empID, status, LaunchedOn) VALUES (?, ?, 'OPEN', CURRENT_TIMESTAMP)");
					
					ps.setInt(1, probID);
					ps.setInt(2, empID);
					
					int x = ps.executeUpdate();
					
					if(x > 0) {
						int logID = getComplaintID(empID, probID);
						message = "Complaint Registered Successfully...\nComplaint ID : " + logID;
					}
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new EmployeeException(e.getMessage());
				}
			}
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}
	
	public List<Problem> getProblems(String category) throws ProblemException {
		List<Problem> problems = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM PROBLEM WHERE category = ? ORDER BY priority");
			
			ps.setString(1, category);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int probID = rs.getInt("probID");
			    String probDesc = rs.getString("probDesc");
			    String categ = rs.getString("category");
			    String priority = rs.getString("priority");
			    
			    Problem problem = new Problem(probID, probDesc, categ, priority);
			    
			    problems.add(problem);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		if(problems.size() == 0) {
			throw new ProblemException("No Problems Found...");
		}
		
		return problems;
	}
	


	@Override
	public String showComplaintStatus(int complaintID, int empID) throws ProblemException {
		String message = "No Complaints Found...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT status FROM PROBLEM_LOG WHERE logID = ? AND empID = ?");
			
			ps.setInt(1, complaintID);
			ps.setInt(2, empID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				message = "Status : " + rs.getString("status");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		return message;
	}
	


	@Override
	public String changePassword(int empID, String newPassword, String oldPassword) throws EmployeeException {
		String message = "Wrong Password Provided...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("UPDATE employee SET password = ? WHERE empID = ? AND password = ?");
			
			ps.setString(1, newPassword);
			ps.setInt(2, empID);
			ps.setString(3, oldPassword);
			
			int x = ps.executeUpdate();
			
			if(x > 0) 
				message = "Password Changed Successfully...";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String registerANewComplain(int empID, String password, String probDesc, String category) throws ProblemException {
		String message = "Complaint Not Registered...\nWrong Password Entered or Other";
		
		try {
			if(checkEmployee(empID, password)) {
				try(Connection conn = DBUtil.provideConnection()) {
					PreparedStatement ps = conn.prepareStatement("INSERT INTO PROBLEM_TEMP (probDesc, category, empID) VALUES (?, ?, ?)");
					
					ps.setString(1, probDesc);
					ps.setString(2, category);
					ps.setInt(3, empID);
					
					int x = ps.executeUpdate();
					
					if(x > 0) {
						message = "Complaint Registered Successfully...\nWait for HOD Approval...";
					}
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new EmployeeException(e.getMessage());
				}
			}
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<ProblemEmpHistDto> getComplaintHistory(int empID) throws ProblemException {
		List<ProblemEmpHistDto> dtos = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT logID, probID, empID, LaunchedOn, status FROM PROBLEM_LOG WHERE empID = ? ORDER BY status DESC");
			
			ps.setInt(1, empID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int logID = rs.getInt("logID");
				int probID = rs.getInt("probID");
				int empId = rs.getInt("empID");
				Timestamp LaunchedOn = rs.getTimestamp("LaunchedOn");
				String status = rs.getString("status");
				
				PreparedStatement ps1 = conn.prepareStatement("SELECT probDesc, category FROM problem WHERE probID = ?");
				
				ps1.setInt(1, probID);
				
				ResultSet rs1 = ps1.executeQuery();
				
				String probDesc = "";
				
				String category = "";
				
				if(rs1.next()) {
					probDesc = rs1.getString("probDesc");
					category = rs1.getString("category");
				}
				
				ProblemEmpHistDto dto = new ProblemEmpHistDto(logID, empId, probDesc, LaunchedOn, category, status);
				
				dtos.add(dto);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		if(dtos.size() == 0) {
			throw new ProblemException("No Problems Found...");
		}
		
		return dtos;
	}

}
