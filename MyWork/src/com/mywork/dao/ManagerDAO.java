package com.mywork.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mywork.constant.WorkException;
import com.mywork.model.Employee;
import com.mywork.model.Manager;

@Repository
@Transactional
public class ManagerDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	
	
	public boolean save(Manager Manager) throws WorkException{
		try {
			entityManager.merge(Manager);
			return true;
		} catch (Exception ex) {
			throw new WorkException("Error Occurred");
		}
	}
		
	@SuppressWarnings("unchecked")
	public Manager get(Long id) {
		List<Manager> managerList = entityManager.createQuery("select m from Manager m where m.id = :id")
	            .setParameter("id", id).getResultList();
		if(managerList != null && managerList.size() > 0) {
            return managerList.get(0);
        }
		return null;
	}
	


	@SuppressWarnings("unchecked")
	public Manager findByManagerLoginId(String managerLoginId) {
        List<Manager> listManager= entityManager.createQuery("select m from Manager m where m.managerLoginId = :managerLoginId")
            .setParameter("managerLoginId", managerLoginId).getResultList();
        if(listManager.size() > 0) {
            return listManager.get(0);
        }
        return null;
        
    }
}
