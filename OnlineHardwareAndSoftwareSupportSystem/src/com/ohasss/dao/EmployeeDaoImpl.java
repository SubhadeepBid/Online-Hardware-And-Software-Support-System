package com.ohasss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ohasss.model.Employee;
import com.ohasss.model.Problem_Engineer;
import com.ohasss.exceptions.ComplainException;
import com.ohasss.exceptions.EmployeeException;
import com.ohasss.utility.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String registerEmployee(Employee emp) throws EmployeeException {
		
		String message = "";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into employee(email,password,name) values(?,?,?)");
			
			ps.setString(1, emp.getEmpusername());
			ps.setString(2, emp.getEmppassword());
			ps.setString(3, emp.getEmpname());
			
			int x = ps.executeUpdate();
			
			try {
				
				PreparedStatement ps1 = conn.prepareStatement("select * from employee where email = ?");
				
				ps1.setString(1,emp.getEmpusername());
				
				ResultSet rs = ps1.executeQuery();
				
				if(rs.next()) {
					
					emp.setEmpid(rs.getInt("empid"));
					
				}
				
				if(x>0) message = "Employee "+ emp.getEmpname() +" registered with id "+ emp.getEmpid();
				
			}catch(SQLException e) {
				System.out.println(e.getMessage()+"  --47");
			}
					
		}catch(SQLException e) {
			message = "Employee already exists..! --55";
		}
		
		return message;
	}

	@Override
	public String loginEmployee(Employee emp) throws EmployeeException {
		
		String message = "\nWrong credentials..!\n";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from employee where email=? AND password=?");
			
			ps.setString(1, emp.getEmpusername());
			ps.setString(2, emp.getEmppassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				emp.setEmpid(rs.getInt("empid"));
				emp.setEmpname(rs.getString("name"));
				
				message = "Login successful..!\nWelcome Employee" + emp.getEmpname()
				+"!\nAssigned id: "+emp.getEmpid();
			}
			
		} catch (SQLException e1) {
			
			System.out.println(e1.getMessage());
		}
		return message;
	}

	@Override
	public String registerComplain(int empid,String category,String name) throws ComplainException {
		
		String message = "No new problem registered..!";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into problem(empid,pname,category) values(?,?,?)");
			
			ps.setInt(1, empid);
			ps.setString(2, name);
			ps.setString(3, category);
			
			
			int x = ps.executeUpdate();
			if(x>0) {
				
				PreparedStatement ps2 = conn.prepareStatement("select * from problem order by problemid desc");
				
				ResultSet rs = ps2.executeQuery();
				
				if(rs.next()) {
					int pid = rs.getInt("problemid");
				
					try {
						
						PreparedStatement ps1 = conn.prepareStatement("insert into problem_engineer(problemid,empid,pname,category,status) values(?,?,?,?,?)");
						ps1.setInt(1, pid);
						ps1.setInt(2, empid);
						ps1.setString(3, name);
						ps1.setString(4, category);
						ps1.setString(5, "Unresolved");
						
						int y = ps1.executeUpdate();
						if(y>0) {
							message = "New problem registered with problem id: "+pid;
							
						}
						
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
		} catch (SQLException e) {
			throw new ComplainException(e.getMessage());
		}
		return message;
	}

	@Override
	public String checkComplainStatus(int problemid) throws ComplainException {
		
		String message = "No Engineer Assigned yet..!";
		
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from problem_Engineer where problemid=?");
			
			ps.setInt(1, problemid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String status = rs.getString("engid");
				
				message = "Problem id " + problemid + " is assigned to engineer with id: "+status;
			}else {
				message = "Wrong problem id..!";
			}
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public List<Problem_Engineer> checkComplainHistory(Employee emp) throws EmployeeException, ComplainException {
		
		List<Problem_Engineer> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()){
			
			
			PreparedStatement ps1 = conn.prepareStatement("select * from employee where empid = ?");
			
			ps1.setInt(1, emp.getEmpid());
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				try {
				PreparedStatement ps = conn.prepareStatement("select * from problem_engineer where empid = ?");
				
				ps.setInt(1, emp.getEmpid());
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int pid = rs.getInt("problemid");
					int engid = rs.getInt("engid");
					int empid = rs.getInt("empid");
					String name = rs.getString("pname");
					String cat = rs.getString("category");
					String status = rs.getString("status");
					
					Problem_Engineer problem = new Problem_Engineer(pid,engid,empid,name,cat,status);
					
					list.add(problem);
				}
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
				if(list.size() == 0) {
					System.out.println("No complains yet..!");
				}
				
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("Employee doesn't exist..!");
		}
		
		return list;
	}

	@Override
	public String changePassword(String email,String password) throws EmployeeException {
		String message = "Password updation failed..!";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update employee set password=? where email=?");
			
			ps.setString(1, password);
			ps.setString(2, email);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				message = "Password updated..!";
			}
			
		} catch (SQLException e) {
			message = e.getMessage()+" --126";
		}
		
		return message;
	}


	
}