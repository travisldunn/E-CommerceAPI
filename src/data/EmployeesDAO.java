package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EmployeesDAO {

	@PersistenceContext
	EntityManager em;

	public List<employeesEntitie> getAllEmployees() {
		List<employeesEntitie> employees = (List<employeesEntitie>) em.createNamedQuery("getAllEmployees").getResultList();
		return employees;
	}

}
