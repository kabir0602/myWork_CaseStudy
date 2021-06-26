package com.mywork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mywork.constant.WorkException;
import com.mywork.dao.ManagerDAO;
import com.mywork.model.Manager;

@Service
public class ManagerService {
	@Autowired
	private ManagerDAO ManagerDAO;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public boolean save(Manager Manager) throws WorkException {
		try {
			return ManagerDAO.save(Manager);
		} catch (WorkException e) {
			throw new WorkException("Error Occurred");

		}
	}
		
	public Manager get(Long id) {
		return ManagerDAO.get(id);
	}
	
	
	public Manager findByManagerLoginId(String managerLoginId) {
		 Manager manager = ManagerDAO.findByManagerLoginId(managerLoginId);
		 return manager;
	}
	
	public Manager findByManagerLoginIdAndPassword(String managerLoginId, String password) {
		 Manager manager = ManagerDAO.findByManagerLoginId(managerLoginId);
		 if(manager != null) {
			boolean isPasswordMatched =  passwordEncoder.matches(password, manager.getPassword());
           if(isPasswordMatched) {
			return manager; 
           }
		 }
		 return null;
	}

	
}
