package com.mywork.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import com.mywork.constant.WorkException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mywork.model.Employee;
import com.mywork.model.Manager;

@Repository
@Transactional
public class EmployeeDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployee() {
		List<?> list = entityManager.createQuery("SELECT emp FROM Employee emp").getResultList();
		return (List<Employee>) list;
	}
	
	public boolean save(Employee employee) throws WorkException{
		try {
			entityManager.merge(employee);
			return true;
		} catch (Exception ex) {
			throw new WorkException("Error Occurred");
		}
	}
		
	@SuppressWarnings("unchecked")
	public Employee get(Long id) {
		List<Employee> employeeList = entityManager.createQuery("select emp from Employee emp where emp.id = :id")
	            .setParameter("id", id).getResultList();
		if(employeeList != null && employeeList.size() > 0) {
            return employeeList.get(0);
        }
        return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public Employee findByPassCode(String passCode) {
        List<Employee> listEmployee= entityManager.createQuery("select emp from Employee emp where emp.passcode = :passCode")
            .setParameter("passCode", passCode).getResultList();
        if(listEmployee != null && listEmployee.size() > 0) {
            return listEmployee.get(0);
        }
        return null;
        
    }
}
