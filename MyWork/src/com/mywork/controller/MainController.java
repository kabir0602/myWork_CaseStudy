package com.mywork.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.constant.WorkException;
import com.mywork.model.Employee;
import com.mywork.model.Manager;
import com.mywork.model.Timesheet;
import com.mywork.service.EmployeeService;
import com.mywork.service.ManagerService;
import com.mywork.service.TimesheetService;
import static com.mywork.constant.Constants.*;

@Controller
public class MainController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private TimesheetService timesheetService;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView(INDEX);
		return mav;
	}

	@RequestMapping("/employeelogin")
	public String empLogoin(Map<String, Object> model) {
		Employee employee = new Employee();
		model.put("employee", employee);
		return EMPLOYEE_LOGIN_FORM;
	}

	@RequestMapping("/managerLogin")
	public String managerLogoin(Map<String, Object> model) {
		Manager customer = new Manager();
		model.put("manager", customer);
		return MANAGER_LOGIN_FORM;
	}

	@RequestMapping(value = "/loginManager", method = RequestMethod.POST)
	public String loginManager(Map<String, Object> model, @ModelAttribute("manager") Manager manager,
			HttpSession session) {
		Manager foundManager = managerService.findByManagerLoginIdAndPassword(manager.getManagerLoginId(),
				manager.getPassword());
		if (foundManager != null) {
			session.setAttribute("Id", foundManager.getManagerLoginId());
			session.setAttribute("Role", "MANAGER");
			return MANAGER_HOME;
		} else {
			model.put("errormsg", "Invalid credentials");
		}
		return MANAGER_LOGIN_FORM;
	}

	@RequestMapping("/employeeadd")
	public String employeeadd(Map<String, Object> model) {
		Employee employee = new Employee();
		model.put("employee", employee);
		return EMPLOYEE_ADD_FORM;
	}

	@RequestMapping(value = "/addnewemployee", method = RequestMethod.POST)
	public ModelAndView employeeadd(@ModelAttribute("employee") Employee employee, HttpSession session) throws WorkException {
		ModelAndView model = new ModelAndView(EMPLOYEE_LIST);
		if (session != null && session.getAttribute("Role").equals("MANAGER")) {
			String managerLoginId = (String) session.getAttribute("Id");
			Manager foundManager = managerService.findByManagerLoginId(managerLoginId);
			if (foundManager != null) {
				foundManager = managerService.findByManagerLoginId(managerLoginId);
				Employee dbEmployee = employeeService.findByPassCode(employee.getPasscode());
				//create new employee
				 if(employee.getId()==null && dbEmployee == null) {
					 employee.setManager(foundManager);
						employeeService.save(employee);
					    foundManager = managerService.findByManagerLoginId(managerLoginId);
						List<Employee> list = foundManager.getEmployeelist();
						model.addObject("list", list);
						return model;
				 }
				 //edit employee with same passcode
				 else if (employee.getId()!=null && dbEmployee!=null && dbEmployee.getId().equals(employee.getId())) {
					employee.setManager(foundManager);
					employeeService.save(employee);
				    foundManager = managerService.findByManagerLoginId(managerLoginId);
					List<Employee> list = foundManager.getEmployeelist();
					model.addObject("list", list);
					return model;

				}
				 //edit employee with new passcode
				 else if (employee.getId()!=null && dbEmployee==null) {
					employee.setManager(foundManager);
					employeeService.save(employee);
				    foundManager = managerService.findByManagerLoginId(managerLoginId);
					List<Employee> list = foundManager.getEmployeelist();
					model.addObject("list", list);
					return model;

				} else {
					model = new ModelAndView(EMPLOYEE_ADD_FORM);
					model.addObject("errormsg", "This Passcode already exists");

				}
			}
		}
		return model;
	}

	@RequestMapping("/employeeslist")
	public String employeeslist(Map<String, Object> model, HttpSession session) {
		if (session != null && session.getAttribute("Role").equals("MANAGER")) {
			String managerLoginId = (String) session.getAttribute("Id");
		Manager foundManager = managerService.findByManagerLoginId(managerLoginId);
		if (foundManager != null) {
			List<Employee> list = foundManager.getEmployeelist();
			model.put("list", list);
			return EMPLOYEE_LIST;
		}
		}
		model.put("list", new ArrayList<Employee>());
		return EMPLOYEE_LIST;

	}

	@RequestMapping(value = "/loginEmployee", method = RequestMethod.POST)
	public String loginEmployee(Map<String, Object> model, @ModelAttribute("employee") Employee employee,
			HttpSession session) {
		Employee emp = employeeService.findByPassCode(employee.getPasscode());
		if (emp != null) {
			session.setAttribute("Id", emp.getId());
			session.setAttribute("Role", "EMPLOYEE");
			return EMPLOYEE_HOME;
		} else {
			model.put("errormsg", "Invalid credentials");
		}
		return EMPLOYEE_LOGIN_FORM;
	}

	@RequestMapping("/clockin")
	public String clockin(Map<String, Object> model, HttpSession session) {
		if (session != null && session.getAttribute("Role").equals("EMPLOYEE")) {
			long employeeId = (long) session.getAttribute("Id");
			Employee employee = timesheetService.clockIn(employeeId);
			if (employee != null) {
				return CLOCK_IN_SUCCESS;
			} else {
				model.put("errormsg", "You are already logged in");
			}
		} else {
			model.put("errormsg", "You are not logged in");
		}
		return EMPLOYEE_HOME;
	}

	@RequestMapping("/clockout")
	public String clockout(Map<String, Object> model, HttpSession session) {
		if (session != null && session.getAttribute("Role").equals("EMPLOYEE")) {
			long employeeId = (long) session.getAttribute("Id");
			Timesheet timesheet = timesheetService.clockOut(employeeId);
			if (timesheet != null) {
				return CLOCK_OUT_SUCCESS;
			} else {
				model.put("errormsg", "You haven't logged In OR You have already logged Out");
			}
		} else {
			model.put("errormsg", "You are not logged in");
		}
		return EMPLOYEE_HOME;
	}

	@RequestMapping("/timesheet")
	public String timesheet(Map<String, Object> model, HttpSession session) {
		if (session != null && session.getAttribute("Role").equals("EMPLOYEE")) {
			long employeeId = (long) session.getAttribute("Id");
			List<Timesheet> list = timesheetService.findByEmployeeId(employeeId);
			model.put("list", list);
			return EMPLOYEE_TIMESHEET;
		}
		return "redirect:/";
	}

	@RequestMapping("/employeehome")
	public ModelAndView employeehome() {
		ModelAndView mav = new ModelAndView(EMPLOYEE_HOME);
		return mav;
	}

	@RequestMapping("/emplyeetimesheets")
	public String employeeTimesheets(Map<String, Object> model, @RequestParam long id) {
		List<Timesheet> list = timesheetService.findByEmployeeId(id);
		model.put("list", list);
		return EMPLOYEE_TIMESHEET;
	}

	@RequestMapping("/editemployee")
	public ModelAndView employeeEdit(HttpSession session, @RequestParam long id) {
		         ModelAndView model = new ModelAndView(EMPLOYEE_ADD_FORM);
		         Employee employee = employeeService.get(id);
				 model.addObject("employee", employee);
				 if(employee == null) {
				 model.addObject("errormsg", "This employee doesn't exists"); 
				 model=	 new ModelAndView(EMPLOYEE_LIST);	
				 }
		     return model;
	}

	
	@RequestMapping("/logout")
	public String logOut(Map<String, Object> model,HttpSession session) {
		if(session != null) {
		session.removeAttribute("Role");
		session.removeAttribute("Id");
		}
		if(!model.isEmpty())
		model.put("errormsg", "");
		return "redirect:/";
		
	}

}
