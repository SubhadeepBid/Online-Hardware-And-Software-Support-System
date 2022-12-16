package com.ohasss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ohasss.model.Engineer;
import com.ohasss.model.Problem_Engineer;
import com.ohasss.exceptions.EngineerException;
import com.ohasss.utility.DBUtil;

public class EngineerDaoImpl implements EngineerDao{

	@Override
	public String login(Engineer e) throws EngineerException {
		
		String message = "Wrong credentials..!";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from engineer where email=? AND password=?");
			
			ps.setString(1, e.getEnguser());
			ps.setString(2, e.getEngpass());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				e.setEngid(rs.getInt("engid"));
				e.setEngname(rs.getString("name"));
				e.setCategory(rs.getString("category"));
				
				message = "Login successful..!\nWelcome Engineer " + e.getEngname() +"!\nAssigned id: "+e.getEngid();
			}
			
		} catch (SQLException e1) {
			
			System.out.println(e1.getMessage());
		}
		return message;
		
	}

	@Override
	public List<Problem_Engineer> viewProblemsAssigned(int engid) throws EngineerException {
		
		List<Problem_Engineer> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from problem_engineer where engid=?");
			
			ps.setInt(1, engid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int pid = rs.getInt("problemid");
				int empid = rs.getInt("empid");
				String category = rs.getString("category");
				String name = rs.getString("pname");
				String status = rs.getString("status");
				
				Problem_Engineer problem = new Problem_Engineer(pid, engid, empid, name, category, status);
				
				list.add(problem);
			}
			
			
		}catch (SQLException e) {
			throw new EngineerException("No such engineer exist!");
		}
		if(list.size()==0) {
			System.out.println("No problem assigned to engineer with id "+engid);
		}
		return list;
	}

	@Override
	public String updateComplainStatus(int pid,int engid) throws EngineerException {
		
		String message = "Complain Status not updated..!";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from problem_engineer where problemid=? AND engid=?");
			
			ps.setInt(1, pid);
			ps.setInt(2, engid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				PreparedStatement ps1 = conn.prepareStatement("update problem_engineer set status='Resolved' where problemid=?");
				
				ps1.setInt(1, pid);
				
				int x = ps1.executeUpdate();
				
				if(x>0) {
					message = "Problem id: " + pid + " Status: Resolved";
				}
				
			}
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public String changePassword(String email,String password) throws EngineerException {
		
		String message = "Password updation failed..!";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update engineer set password=? where email=?");
			
			ps.setString(1, password);
			ps.setString(2, email);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				message = "Password updated..!";
			}
			
		} catch (SQLException e) {
			message = e.getMessage()+" --76";
		}
		
		return message;
	}

}