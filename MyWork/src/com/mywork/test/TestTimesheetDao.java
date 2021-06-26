package com.mywork.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mywork.constant.WorkException;
import com.mywork.dao.EmployeeDAO;
import com.mywork.dao.ManagerDAO;
import com.mywork.dao.TimesheetDAO;
import com.mywork.model.Employee;
import com.mywork.model.Manager;
import com.mywork.model.Timesheet;
import com.mywork.service.EmployeeService;
import com.mywork.service.ManagerService;
import com.mywork.service.TimesheetService;

import antlr.collections.List;

@RunWith(MockitoJUnitRunner.class)
public class TestTimesheetDao {

	@InjectMocks
	TimesheetService service = new TimesheetService();

	@Mock
	TimesheetDAO dao;

	@org.junit.Test
	public void testFindByEmployeeId() {
		java.util.List<Timesheet> list = new ArrayList<Timesheet>();
		Timesheet sheet = new Timesheet();
		sheet.setId(1l);
		sheet.setWeekDay("Monday");
		list.add(sheet);

		Manager manage = new Manager();
		manage.setName("test name");
		manage.setManagerLoginId("123");
		Mockito.when(dao.findByEmployeeId(1l)).thenReturn(list);
		java.util.List<Timesheet> foundlist = service.findByEmployeeId(1l);
		Assert.assertEquals(foundlist.size(), list.size());
	}

	@org.junit.Test
	public void testAllTimesheets() {
		java.util.List<Timesheet> list = new ArrayList<Timesheet>();
		Timesheet sheet = new Timesheet();
		sheet.setId(1l);
		sheet.setWeekDay("Monday");
		list.add(sheet);
		Manager manage = new Manager();
		manage.setName("test name");
		manage.setManagerLoginId("123");
		Mockito.when(dao.getAllTimesheet()).thenReturn(list);
		java.util.List<Timesheet> foundlist = service.listAll();
		Assert.assertEquals(foundlist.size(), list.size());
	}

	public void testSaveTimesheet() throws WorkException {
		Employee emp = new Employee();
		emp.setFullName("test name");
		emp.setPasscode("00000");
		Timesheet t = new Timesheet();

		t.setId(1l);
		t.setCheckIn(new Date());
		t.setCheckOut(new Date());
		t.setEmployee(emp);
		Mockito.when(dao.save(t)).thenReturn(true);
		boolean isSaved = service.save(t);
		Assert.assertTrue(isSaved);
	}

}
