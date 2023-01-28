package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final String persistenceUnit = "BE-06-22-S3-L5-WeeklyProject-Finale";
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
	protected static final EntityManager em = emf.createEntityManager();
	protected static final EntityTransaction t = em.getTransaction();
	
	static {
		try {
			emf = Persistence
					.createEntityManagerFactory(persistenceUnit);
		} catch (Throwable ex) {
			System.err.println("Inizializzazione fallita.. D:"+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
}
