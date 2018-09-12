package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.EmailConfig;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class EmailConfigDAO extends DAO {

	public void salvar(EmailConfig emailConfig) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(emailConfig);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public EmailConfig get(int emailConfig_id) {

		EntityManager em = getEntityManager().createEntityManager();

		return em.find(EmailConfig.class, emailConfig_id);

	}

	public void update(EmailConfig emailConfig) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			EmailConfig emailc = em.find(EmailConfig.class, emailConfig.getId());

			emailc.setEmail(emailConfig.getEmail());
			emailc.setSenha(emailConfig.getSenha());
			emailc.setPorta(emailConfig.getPorta());
			emailc.setSmtp(emailConfig.getSmtp());

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}
	}

	public void delete(EmailConfig emailConfig) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			EmailConfig ec = em.find(EmailConfig.class, emailConfig.getId());

			em.remove(ec);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public List<EmailConfig> GetALL() {

		EntityManager em = getEntityManager().createEntityManager();

		List<EmailConfig> lista = null;

		try {

			Query q = em.createQuery("select object(e) from EmailConfig as e");

			lista = q.getResultList();

		} catch (Exception ex) {

			em.close();
		}

		return lista;
	}

}
