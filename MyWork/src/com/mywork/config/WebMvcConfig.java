package com.mywork.config;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mywork.constant.WorkException;
import com.mywork.model.Employee;
import com.mywork.model.Manager;
import com.mywork.model.Timesheet;
import com.mywork.service.ManagerService;
import com.mywork.service.TimesheetService;

@Configuration
@ComponentScan("com.mywork")
public class WebMvcConfig {
	@Autowired
	TimesheetService timesheetService;
	
	@Autowired
	ManagerService managerService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		addManagerRecord1();
		addManagerRecord2();

		return viewResolver;
	}

	private void addManagerRecord1() {
		Manager manager = managerService.findByManagerLoginId("1234567");
		if (manager == null) {
			manager = new Manager();
			manager.setManagerLoginId("1234567");
			manager.setName("TestManager");
			manager.setDepartment("Test");
			manager.setPassword(passwordEncoder.encode("123"));
			try {
				managerService.save(manager);
			} catch (WorkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Employee emp = timesheetService.clockIn(1L);
//		Timesheet timesheet = timesheetService.clockOut(1L);

	}
	
	 	private void addManagerRecord2() {
		Manager manager = managerService.findByManagerLoginId("Manager");
		if (manager == null) {
			manager = new Manager();
			manager.setManagerLoginId("Manager");
			manager.setName("OneManager");
			manager.setDepartment("Food");
			manager.setPassword(passwordEncoder.encode("000"));
			try {
				managerService.save(manager);
			} catch (WorkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	} 
}
