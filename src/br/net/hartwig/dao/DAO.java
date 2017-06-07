package br.net.hartwig.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.ejb.EntityManagerImpl;

//Classe principal do Hibernate
//Utilizando EntityManager do JPA
/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class DAO {

	private static EntityManagerFactory emf = null;

	public EntityManagerFactory getEntityManager() {

		if (emf == null) {
			// Ao se conectar irá procurar por uma conexão chamado SGSD que está
			// no persistence.xml
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
