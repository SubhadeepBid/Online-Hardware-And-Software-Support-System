package com.ohasss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ohasss.dtoes.EngineerDtoCred;
import com.ohasss.dtoes.ProblemEnggDto;
import com.ohasss.exceptions.EngineerException;
import com.ohasss.exceptions.ProblemException;
import com.ohasss.utility.DBUtil;

public class EngineerDaoImpl implements EngineerDao {

	@Override
	public int loginEngineer(EngineerDtoCred cred) throws EngineerException {
		int engID = 0;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT engID FROM engineer WHERE email = ? AND password = ?");
			
			ps.setString(1, cred.getEmail());
			ps.setString(2, cred.getPassword());
			
            ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				engID = rs.getInt("engID");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EngineerException(e.getMessage());
		}
		
		return engID;
	}
	
	public boolean checkEngineer(int engID, String password) throws EngineerException {
		boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ENGINEER WHERE engID = ? AND password = ?");
			
			ps.setInt(1, engID);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				flag = true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EngineerException(e.getMessage());
		}
		
		return flag;
	}
	
	public String getProblemStatus(int complaintID, int engID) throws ProblemException {
		String result = "No Problems Found...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT status FROM PROBLEM_LOG WHERE logID = ? AND engID = ?");
			
			ps.setInt(1, complaintID);
			ps.setInt(2, engID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("status");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public String updateProblemStatus(int complainID, String status, int engID, String password) throws ProblemException {
		String message = "No Complaint Found...";
		
		try {
			if(checkEngineer(engID, password)) {
				
				try(Connection conn = DBUtil.provideConnection()) {
					int x = 0;
					if(status == "IN PROGRESS") {
						PreparedStatement ps = conn.prepareStatement("UPDATE problem_log SET status = ?, StartedWorking = CURRENT_TIMESTAMP WHERE logID = ?");
						
						ps.setString(1, status);
						ps.setInt(2, complainID);
						
						x = ps.executeUpdate();
					}
					else {
                        PreparedStatement ps = conn.prepareStatement("UPDATE problem_log SET status = ?, ClosedOn = CURRENT_TIMESTAMP WHERE logID = ?");
						
						ps.setString(1, status);
						ps.setInt(2, complainID);
						
						x = ps.executeUpdate();
					}
					
					if(x > 0)
						message = "Status Updated...";
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new ProblemException(e.getMessage());
				}
				
			}
			else {
				message = "Wrong Password Entered...";
			}
		} catch (EngineerException | ProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}

	@Override
	public String changePassword(int engID, String newPassword, String oldPassword) throws EngineerException {
		String message = "Wrong Password Provided...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("UPDATE engineer SET password = ? WHERE engID = ? AND password = ?");
			
			ps.setString(1, newPassword);
			ps.setInt(2, engID);
			ps.setString(3, oldPassword);
			
			int x = ps.executeUpdate();
			
			if(x > 0)
				message = "Password Changed Successfully...";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EngineerException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<ProblemEnggDto> showProblemsAssigned(int engID, String status) throws ProblemException {
		List<ProblemEnggDto> dtos = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			if(status == "ALL") {
				PreparedStatement ps = conn.prepareStatement("SELECT pl.logID, p.probDesc, p.priority, pl.empID, pl.status FROM problem p INNER JOIN problem_log pl ON p.probID = pl.probID AND pl.engID = ? ORDER BY pl.status");
				
				ps.setInt(1, engID);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int logID = rs.getInt("logID");
					String probDesc = rs.getString("probDesc");
					String priority = rs.getString("priority");
					int empID = rs.getInt("empID");
					String stat = rs.getString("status");
					
					ProblemEnggDto dto = new ProblemEnggDto(logID, probDesc, priority, empID, stat);
					
					dtos.add(dto);
				}
			}
			else {
				PreparedStatement ps = conn.prepareStatement("SELECT pl.logID, p.probDesc, p.priority, pl.empID, pl.status FROM problem p INNER JOIN problem_log pl ON p.probID = pl.probID AND pl.engID = ? AND pl.status = ?");
				
                ps.setInt(1, engID);
                ps.setString(2, status);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int logID = rs.getInt("logID");
					String probDesc = rs.getString("probDesc");
					String priority = rs.getString("priority");
					int empID = rs.getInt("empID");
					String stat = rs.getString("status");
					
					ProblemEnggDto dto = new ProblemEnggDto(logID, probDesc, priority, empID, stat);
					
					dtos.add(dto);
				}
			}
			
			if(dtos.size() == 0)
				throw new ProblemException("No Records Found...");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		return dtos;
	}

}
