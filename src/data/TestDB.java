package data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestDB {

	public static void main(String[] args) {
		System.out.println("in main");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MCPU");
		System.out.println("after Factory");
		EntityManager em = emf.createEntityManager();
		System.out.println("after EM");
		String test = "Travis";
		String password = "1234";
		userEntitie temp = em.find(userEntitie.class, 1);
		System.out.println(temp);

	}
}