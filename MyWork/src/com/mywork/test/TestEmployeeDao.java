package com.mywork.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mywork.constant.WorkException;
import com.mywork.dao.EmployeeDAO;
import com.mywork.model.Employee;
import com.mywork.service.EmployeeService;

import antlr.collections.List;

	@RunWith(MockitoJUnitRunner.class)
	public class TestEmployeeDao {
		
	   @InjectMocks 
	   EmployeeService service = new EmployeeService();

	   @Mock
	   EmployeeDAO dao;

	   @org.junit.Test
	   public void testVerifyPasscode(){
		   Employee emp = new Employee();
			emp.setFullName("test name");
			emp.setPasscode("123");
	      Mockito.when(dao.findByPassCode("123")).thenReturn(emp);
	      Employee found = service.findByPassCode("123");

	      Assert.assertEquals(found.getFullName(),emp.getFullName());
	   }
	   
	   @org.junit.Test
	   public void testAllEmployees(){
		   java.util.List<Employee> list = new ArrayList<Employee>();
		   Employee emp = new Employee();
			emp.setFullName("test name");
			emp.setPasscode("123");
			list.add(emp);
	      Mockito.when(dao.getAllEmployee()).thenReturn(list);
	      java.util.List<Employee> foundlist = service.listAll();

	      Assert.assertEquals(foundlist.size(),list.size());
	   }
	   
	   public void testSaveEmployee() throws WorkException{
		   Employee emp = new Employee();
			emp.setFullName("test name");
			emp.setPasscode("00000");			
	      Mockito.when(dao.save(emp)).thenReturn(true);
	      boolean isEmpSaved = service.save(emp);
	      Assert.assertTrue(isEmpSaved);
	   }
	   
	   @org.junit.Test
	   public void testVerifyGet(){
		   Employee emp = new Employee();
		   emp.setId(1l);
			emp.setFullName("test name");
			emp.setPasscode("123");
	      Mockito.when(dao.get(1l)).thenReturn(emp);
	      Employee found = service.get(1l);
	      Assert.assertEquals(found.getId(),emp.getId());
	   }
	   
	   @org.junit.Test
	   public void testVerifyGetNull(){
		  Long id=2l;
	      Mockito.when(dao.get(2l)).thenReturn(null);
	      Employee found = service.get(id);
	      Assert.assertNull(found);
	   }
	   
	}

