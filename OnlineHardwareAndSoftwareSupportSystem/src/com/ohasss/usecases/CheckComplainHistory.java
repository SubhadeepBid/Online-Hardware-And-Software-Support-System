package com.ohasss.usecases;

import java.util.List;
import java.util.Scanner;

import com.ohasss.model.Employee;
import com.ohasss.model.Problem_Engineer;
import com.ohasss.dao.EmployeeDao;
import com.ohasss.dao.EmployeeDaoImpl;
import com.ohasss.exceptions.ComplainException;
import com.ohasss.exceptions.EmployeeException;

public class CheckComplainHistory {

	public static void main() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter employee id: ");
		int empid = sc.nextInt();
		
		Employee emp = new Employee();
		emp.setEmpid(empid);
		
		EmployeeDao dao = new EmployeeDaoImpl();
		
		try {
			List<Problem_Engineer> problemsRegisteredByEmployee = dao.checkComplainHistory(emp);
			
			problemsRegisteredByEmployee.forEach(p -> System.out.println(p));
			
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		} catch (ComplainException e) {
			System.out.println(e.getMessage());
		}

		
		
	}
}