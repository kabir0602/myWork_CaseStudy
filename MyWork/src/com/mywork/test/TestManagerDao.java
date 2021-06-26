package com.mywork.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mywork.constant.WorkException;
import com.mywork.dao.ManagerDAO;
import com.mywork.model.Employee;
import com.mywork.model.Manager;
import com.mywork.service.ManagerService;

	@RunWith(MockitoJUnitRunner.class)
	public class TestManagerDao {
		
	   @InjectMocks 
	   ManagerService service = new ManagerService();

	   @Mock
	   ManagerDAO dao;

	   @org.junit.Test
	   public void testFindManager(){
		   Manager manage = new Manager();
		   manage.setName("test name");
		   manage.setManagerLoginId("123");
	      Mockito.when(dao.findByManagerLoginId("123")).thenReturn(manage);
	      Manager found = service.findByManagerLoginId("123");
	      Assert.assertEquals(found.getName(),manage.getName());
	   }
	   
	   @org.junit.Test
	   public void testGetManager(){
		   java.util.List<Employee> list = new ArrayList<Employee>();
		   Manager manage = new Manager();
		   manage.setName("test name");
		   manage.setManagerLoginId("123");
		   manage.setId(1l);
	      Mockito.when(dao.get(1l)).thenReturn(manage);
	      Manager found = service.get(1l);
	      Assert.assertEquals(found.getId(),manage.getId());
	   }
	   
	   public void testSaveManager() throws WorkException{
		   Manager manage = new Manager();
		   manage.setName("test name");
		   manage.setManagerLoginId("123");
		   manage.setId(1l);	
	      Mockito.when(dao.save(manage)).thenReturn(true);
	      boolean isManSaved = service.save(manage);
	      Assert.assertTrue(isManSaved);
	   }
	}

