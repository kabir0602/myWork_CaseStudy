package com.mywork.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mywork.constant.WorkException;
import com.mywork.model.Employee;
import com.mywork.model.Timesheet;

@Repository
@Transactional
public class TimesheetDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Timesheet> getAllTimesheet() {
		List<?> list = entityManager.createQuery("SELECT ts FROM Timesheet ts").getResultList();
		return (List<Timesheet>) list;
	}
	
	public boolean save(Timesheet Timesheet) throws WorkException{
		try {
			entityManager.merge(Timesheet);
			return true;
		} catch (Exception ex) {
			throw new WorkException("Error Occurred");
		}
	}
		
	
	
	@SuppressWarnings("unchecked")
	public Collection<Timesheet> findByEmployeeId(Long employeeId) {
        return entityManager.createQuery("select ts from Timesheet ts where ts.employee.id = :employeeId")
            .setParameter("employeeId", employeeId).getResultList();
    }
	
	@SuppressWarnings("unchecked")
	public Timesheet findByEmployeeIdAndCheckIn(Long employeeId, Date checkInDate) {
		List<Timesheet> timesheetList =  entityManager.createQuery("select ts from Timesheet ts where ts.employee.id = :employeeId and DATE_FORMAT(ts.checkIn,'%d/%m/%Y')  = DATE_FORMAT(CURDATE(),'%d/%m/%Y')")
        .setParameter("employeeId", employeeId)
       // .setParameter("checkInDate", checkInDate)
        .getResultList();
		if(timesheetList != null && timesheetList.size() > 0 ) {
			return timesheetList.get(0);
		}
       return null;
    }
	
}
