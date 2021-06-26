package com.mywork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mywork.constant.WorkException;
import com.mywork.dao.EmployeeDAO;
import com.mywork.model.Employee;
import com.mywork.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDAO employeeDAO;
	
	public List<Employee> listAll() {
		return employeeDAO.getAllEmployee();
	}
	
	public boolean save(Employee employee) throws WorkException {
		try {
			return employeeDAO.save(employee);
		} catch (WorkException ex) {
			throw new WorkException("Error Occurred");
		}
	}
		
	public Employee get(Long id) {
		return employeeDAO.get(id);
	}

	

	public Employee findByPassCode(String code) {
		return employeeDAO.findByPassCode(code);
	}
	
}
