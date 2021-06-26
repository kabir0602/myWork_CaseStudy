package com.mywork.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mywork.constant.WorkException;
import com.mywork.dao.TimesheetDAO;
import com.mywork.model.Employee;
import com.mywork.model.Timesheet;

@Service
public class TimesheetService {
	@Autowired
	private TimesheetDAO timesheetDAO;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	public List<Timesheet> listAll() {
		return timesheetDAO.getAllTimesheet();
	}
	
	public boolean save(Timesheet Timesheet) throws WorkException {
		try {
			return timesheetDAO.save(Timesheet);
		} catch (WorkException e) {
			throw new WorkException("Error Occurred");		}
	}

	
	public List<Timesheet> findByEmployeeId(Long employeeId) {
		return (List<Timesheet>) timesheetDAO.findByEmployeeId(employeeId);
	}
	
	public Employee clockIn(Long employeeId) {
		Date checkInDate = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(checkInDate);
        String dayOfWeek = new SimpleDateFormat("EEEE").format(checkInDate);
		Employee employee  = employeeService.get(employeeId);
		List<Timesheet> timeSheetList = employee.getTimeSheetlist();
		Timesheet timesheet = timesheetDAO.findByEmployeeIdAndCheckIn(employeeId, checkInDate);
		if(timesheet == null) {
			timesheet = new Timesheet();
			timesheet.setCheckIn(checkInDate);
			timesheet.setWeekDay(dayOfWeek);
			timesheet.setEmployee(employee);
			timeSheetList.add(timesheet);		
			try {
				employeeService.save(employee);
			} catch (WorkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return employee;
		}
		return null;
	}
	
	public Timesheet clockOut(Long employeeId) {
		Date today = new Date();
		Timesheet timesheet = timesheetDAO.findByEmployeeIdAndCheckIn(employeeId, today);
		if((timesheet != null) && (timesheet.getCheckOut()==null)) {
			timesheet.setCheckOut(today);
			String hours  = calculateHours(timesheet.getCheckIn(), today);
			timesheet.setWorkedHours(hours);
			try {
				timesheetDAO.save(timesheet);
			} catch (WorkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return timesheet;
			}	
		return null;
	}
	
	public List<Timesheet> timeSheetView(Long employeeId) {
		Employee employee  = employeeService.get(employeeId);
		List<Timesheet> timeSheetList = employee.getTimeSheetlist();	
		return timeSheetList;
      }
	
	private String calculateHours(Date dateCheckin, Date dateCheckOut) {
		long different = dateCheckOut.getTime() - dateCheckin.getTime();
	    long secondsInMilli = 1000;
	    long minutesInMilli = secondsInMilli * 60;
	    long hoursInMilli = minutesInMilli * 60;
	    long elapsedHours = different / hoursInMilli;
	    different = different % hoursInMilli;
	    long elapsedMinutes = different / minutesInMilli;
	    String hours=elapsedHours+":"+elapsedMinutes;
//
//		long secs =(dateCheckOut.getTime() - dateCheckin.getTime())/1000;
//		double hours = secs/3600;
		return hours;
		
		
	}
}
