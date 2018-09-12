package br.net.hartwig.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.ejb.EntityManagerImpl;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class DAO {

	private static EntityManagerFactory emf = null;

	public EntityManagerFactory getEntityManager() {

		if (emf == null) {

			emf = Persistence.createEntityManagerFactory("SGSD");

		}

		return emf;
	}

	public DAO() {

	}

	public Session getSession() {
		@SuppressWarnings("unused")
		Session session = null;

		if (getEntityManager().createEntityManager().getDelegate() instanceof EntityManagerImpl) {
			EntityManagerImpl entityManagerImpl = (EntityManagerImpl) getEntityManager().createEntityManager()
					.getDelegate();
			return entityManagerImpl.getSession();
		} else {
			return (Session) getEntityManager().createEntityManager().getDelegate();
		}
	}
}
