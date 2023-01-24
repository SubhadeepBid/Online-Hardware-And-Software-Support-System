package com.ohasss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.ohasss.utility.DBUtil;

public class HODDaoImpl implements HODDao {

	@Override
	public String loginHOD(HODDto hodDto) throws HODException {
        String message = "Invalid Username or Password...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM HOD WHERE email = ? AND password = ?");
			
			ps.setString(1, hodDto.getEmail());
			ps.setString(2, hodDto.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) 
				message = "Logged In Successfully...";
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new HODException(e.getMessage());
		}
		
		return message;
	}

	public boolean checkHOD(String password) throws HODException {
		boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM HOD WHERE password = ?");
			
			ps.setString(1, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new HODException(e.getMessage());
		}
		
		return flag;
	}
	
	@Override
	public String registerAnEngineer(Engineer engineer) throws EngineerException {
		String message = "Engineer Not Registered...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ENGINEER (name, email, password, category) VALUES (?,?,?,?)");
			
			ps.setString(1, engineer.getName());
			ps.setString(2, engineer.getEmail());
			ps.setString(3, engineer.getPassword());
			ps.setString(4, engineer.getCategory());
			
			int x = ps.executeUpdate();
			
			if(x > 0)
				message = "Engineer Added Successfully...";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EngineerException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<EngineerDto> showAllEngineers() throws EngineerException {
		List<EngineerDto> engineerDtos = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ENGINEER ORDER BY category");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int engID = rs.getInt("engID");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String category = rs.getString("category");
				
				EngineerDto engineerDto = new EngineerDto(engID, name, email, category);
				
				engineerDtos.add(engineerDto);
			}
			
			if(engineerDtos.size() == 0)
				throw new EngineerException("No Engineers Found...");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EngineerException(e.getMessage());
		}
		
		return engineerDtos;
	}

	@Override
	public String removeAnEngineer(int engID) throws EngineerException {
		String message = "No Engineer Found...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ENGINEER WHERE engID = ?");
			ps.setInt(1, engID);
			
			int x = ps.executeUpdate();
			if(x > 0) 
				message = "Engineer Deleted Successfully...";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EngineerException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<ProblemHODDtoPNA> showAllProblemsNotAssignedToEngineers(String category) throws ProblemException {
		List<ProblemHODDtoPNA> dtoPNAs = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT pl.logID, p.probDesc, pl.empID, pl.launchedOn, p.priority FROM problem p INNER JOIN problem_log pl ON p.probID = pl.probID AND p.category = ? AND pl.engID IS NULL");
			
			ps.setString(1, category);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int logID = rs.getInt("logID");
				String probDesc = rs.getString("probDesc");
				int empID = rs.getInt("empID");
				Timestamp timestamp = rs.getTimestamp("launchedOn");
				String priority = rs.getString("priority");
				
				ProblemHODDtoPNA dtoPNA = new ProblemHODDtoPNA(logID, probDesc, empID, timestamp, priority);
				
				dtoPNAs.add(dtoPNA);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		if(dtoPNAs.size() == 0) {
			throw new ProblemException("No Records Found...");
		}
		
		return dtoPNAs;
	}

	@Override
	public List<ProblemHODDtoPA> showAllProblemsAssignedToEngineers(String category) throws ProblemException {
		List<ProblemHODDtoPA> dtoPAs = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT pl.logID, p.probDesc, pl.empID, pl.launchedOn, p.priority, pl.engID, pl.status, pl.startedWorking, pl.closedOn FROM problem p INNER JOIN problem_log pl ON p.probID = pl.probID AND p.category = ? AND pl.engID IS NOT NULL ORDER BY pl.status DESC");
			
			ps.setString(1, category);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int logID = rs.getInt("logID");
				String probDesc = rs.getString("probDesc");
				int empID = rs.getInt("empID");
				Timestamp launchedOn = rs.getTimestamp("launchedOn");
				String priority = rs.getString("priority");
				int engID = rs.getInt("engID");
				String status = rs.getString("status");
				Timestamp startedWorking = rs.getTimestamp("startedWorking");
				Timestamp closedOn = rs.getTimestamp("closedOn");
				
				ProblemHODDtoPA dtoPA = new ProblemHODDtoPA(logID, probDesc, empID, launchedOn, priority, engID, status, startedWorking, closedOn);
						
				dtoPAs.add(dtoPA);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException();
		}
		if(dtoPAs.size() == 0) {
			throw new ProblemException("No Records Found...");
		}
		
		return dtoPAs;
	}
	
	@Override
	public List<EmployeeHODDto> showAllUnVerifiedEmployee() throws EmployeeException {
		List<EmployeeHODDto> dtos = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT empID, name, email FROM employee WHERE status = 'UNVERIFIED'");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int empID = rs.getInt("empID");
				String name = rs.getString("name");
				String email = rs.getString("email");
				
				EmployeeHODDto dto = new EmployeeHODDto(empID, name, email);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		if(dtos.size() == 0) {
			throw new EmployeeException("No Employee Found...");
		}
		
		return dtos;
	}

	@Override
	public String approveAnEmployee(int empID, String password) throws EmployeeException {
		String message = "Employee Approval Failed...";
		
		try {
			if(checkHOD(password)) {
				try(Connection conn = DBUtil.provideConnection()) {
					PreparedStatement ps = conn.prepareStatement("UPDATE employee SET status = 'VERIFIED' WHERE empID = ?");
					
					ps.setInt(1, empID);
					
					int x = ps.executeUpdate();
					
					if(x > 0) {
						message = "Employee Approveded Successfully...";
					}
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new EmployeeException(e.getMessage());
				}
			}
		} catch (HODException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String assignAProblemToAnEngineer(int engID, int logID, String password) throws ProblemException {
		String message = "Failed To Assign An Engineer...";
		
		try {
			if(checkHOD(password)) {
				try(Connection conn = DBUtil.provideConnection()) {
		        	PreparedStatement ps = conn.prepareStatement("UPDATE problem_log SET engID = ? WHERE logID = ?");
					
		        	ps.setInt(1, engID);
		        	ps.setInt(2, logID);
		        	
		        	int x = ps.executeUpdate();
		        	
		        	if(x > 0) {
		        		message = "Complaint No. " + logID + " Is Successfully Assigned To Engineer With EngineerID " + engID;
		        	}
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new ProblemException(e.getMessage());
				}
			}
		} catch (HODException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
		return message;
	}

	public boolean launchAComplaint(Problem_Temp pTemp, int probID) throws ProblemException {
		boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO problem_log (probID, empID, LaunchedOn, status) VALUES (?, ?, ?, 'OPEN')");
			
			ps.setInt(1, probID);
			ps.setInt(2, pTemp.getEmpID());
			ps.setTimestamp(3, pTemp.getLaunchedOn());
			
			int x = ps.executeUpdate();
			
			if(x > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		return flag;
	}
	
	public boolean deleteANewProblem(int prob_temp_id) throws ProblemException {
		boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM problem_temp WHERE prob_temp_id = ?");
			
			ps.setInt(1, prob_temp_id);
			
			int x = ps.executeUpdate();
			
			if(x > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		return flag;
	}
	
	@Override
	public String setPriorityOfANewProblemLaunched(Problem_Temp pTemp, String priority, String password) throws ProblemException {
		String message = "Failed To Set A Priority...";
		
		try {
			if(checkHOD(password)) {

				try(Connection conn = DBUtil.provideConnection()) {
					PreparedStatement ps = conn.prepareStatement("INSERT INTO problem (probDesc, category, priority) VALUES (?, ?, ?)");
					ps.setString(1, pTemp.getProbDesc());
					ps.setString(2, pTemp.getCategory());
					ps.setString(3, priority);
					
					int x = ps.executeUpdate();
					
					if(x > 0) {
						PreparedStatement ps1 = conn.prepareStatement("SELECT probID FROM problem WHERE probDesc = ? AND category = ?");
						
						ps1.setString(1, pTemp.getProbDesc());
						ps1.setString(2, pTemp.getCategory());
						
						ResultSet rs1 = ps1.executeQuery();
						
						if(rs1.next()) {
							int probID = rs1.getInt("probID");
							if(launchAComplaint(pTemp, probID) && deleteANewProblem(pTemp.getProb_temp_id())) {
								message = "Priority Set & Problem Added Successfully...";
							}
						}
					}
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new ProblemException(e.getMessage());
				}
				
			}
		} catch (HODException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
		return message;
	}

	@Override
	public List<Problem_Temp> getNewProblemDetails() throws ProblemException {
		List<Problem_Temp> temps = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM problem_temp");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int prob_temp_id = rs.getInt("prob_temp_id"); 
				String probDesc = rs.getString("probDesc"); 
				String category = rs.getString("category"); 
				Timestamp launchedOnTimestamp = rs.getTimestamp("LaunchedOn"); 
				int empID = rs.getInt("empID");
				
				Problem_Temp temp = new Problem_Temp(prob_temp_id, probDesc, category, launchedOnTimestamp, empID);
				
				temps.add(temp);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ProblemException(e.getMessage());
		}
		
		if(temps.size() == 0) {
			throw new ProblemException("No New Problems Found...");
		}
		
		return temps;
	}
	
}
