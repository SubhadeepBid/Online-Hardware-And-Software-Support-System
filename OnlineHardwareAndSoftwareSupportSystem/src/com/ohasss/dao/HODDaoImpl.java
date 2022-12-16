package com.ohasss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ohasss.model.Engineer;
import com.ohasss.model.Problem;
import com.ohasss.model.Problem_Engineer;
import com.ohasss.exceptions.ComplainException;
import com.ohasss.exceptions.EngineerException;
import com.ohasss.exceptions.HODException;
import com.ohasss.utility.DBUtil;

public class HODDaoImpl implements HODDao {
	
	@Override
	public String loginHOD(String username, String pass) throws HODException {
		
		String message = "\nWrong credentials!\n";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from hod where username=? AND password=?");
			
			ps.setString(1, username);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String n = rs.getString("hname");
				
				message = "Login successful, welcome "+ n;
				
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

	@Override
	public String registerNewEngineer(Engineer eng) throws EngineerException {
		
		String message = "";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into engineer(email,password,category,name) values(?,?,?,?)");
			
			
			ps.setString(1, eng.getEnguser());
			ps.setString(2, eng.getEngpass());
			ps.setString(3, eng.getCategory());
			ps.setString(4, eng.getEngname());
			
			int x = ps.executeUpdate();
			
			try {
				
				PreparedStatement ps1 = conn.prepareStatement("select * from engineer where email = ? AND password = ?");
				
				ps1.setString(1,eng.getEnguser());
				ps1.setString(2,eng.getEngpass());
				
				ResultSet rs = ps1.executeQuery();
				
				if(rs.next()) {
					
					eng.setEngid(rs.getInt("engid"));
					
				}
				
				if(x>0) message = "Engineer registered with name "+ eng.getEngname() +"\n id "+ eng.getEngid() + "\n category "+ eng.getCategory();
				
			}catch(SQLException e) {
				System.out.println(e.getMessage()+"  --80");
			}
			
			
			
			
					
		}catch(SQLException e) {
			message = "Engineer already exists..! --88";
		}
		
		return message;
	}

	@Override
	public List<Engineer> listAllRegisteredEngineers() throws EngineerException {
		
		List<Engineer> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from engineer");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int engid = rs.getInt("engid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String category = rs.getString("category");
				
				Engineer engineer = new Engineer(engid,name,email,password,category);
				
				list.add(engineer);
			}
			
			
			
		}catch (SQLException e) {
			throw new EngineerException("No engineers available..!");
		}
		
		return list;
	}

	@Override
	public String removeEngineer(int engid) throws EngineerException {
		
		String message = "";
		
		try(Connection conn = DBUtil.provideConnection() ){
			
			PreparedStatement ps = conn.prepareStatement("delete from engineer where engid = ?");
			
			ps.setInt(1, engid);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				message = "Engineer with id "+ engid + " is removed"; 
			}
			
			
		} catch (SQLException e) {
			message = e.getMessage(); 
		}
		return message;
	}

	@Override
	public List<Problem_Engineer> viewAllProblems() throws ComplainException {
		
		List<Problem_Engineer> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from problem_engineer");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int pid = rs.getInt("problemid");
				int engid = rs.getInt("engid");
				int empid = rs.getInt("empid");
				String name = rs.getString("pname");
				String cat = rs.getString("category");
				String status = rs.getString("status");
				
				Problem_Engineer problem = new Problem_Engineer(pid, engid, empid, name, cat, status);
				
				list.add(problem);
			}
			if(list.size() == 0) {
				System.out.println("No problem yet..!");
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public String assignComplainToEngineer(Problem prob, int engid) throws ComplainException, EngineerException {
		
		String message = "Problem id or engineer id or category did not match..!";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps1 = conn.prepareStatement("select * from engineer where engid=?");
			
			ps1.setInt(1, engid);
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next()){
				
					String eng_category = rs1.getString("category");
					
					PreparedStatement ps = conn.prepareStatement("select * from problem where problemid=?");
	
					ps.setInt(1, prob.getProblemid());
					
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()) {
						PreparedStatement ps2 = conn.prepareStatement("update problem_engineer set engid=? where problemid=? and category=?");
					    
						ps2.setInt(1, engid);
						ps2.setInt(2, prob.getProblemid());
						ps2.setString(3, eng_category);
						
						int x = ps2.executeUpdate();
						if(x>0) {
							message = "Problem " + prob.getProblemid() + " is assigned to Engineer "+ rs1.getString("name") +
									" with id "+ rs1.getInt("engid");
							
						}
						
					}else {
						throw new EngineerException("Problem id or engineer id or category did not match");
					}
					
			}else {
				throw new EngineerException("No such Engineer id exist");
			}
				
		}catch (SQLException | EngineerException e) {

			throw new EngineerException(e.getMessage());
		}
		return message;
	}

	
}