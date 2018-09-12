package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Setor;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class SetorDAO extends DAO {

	public void salvar(Setor setor) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(setor);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public Setor get(int setor_id) {

		EntityManager em = getEntityManager().createEntityManager();

		return em.find(Setor.class, setor_id);

	}

	public void update(Setor setor) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Setor s = em.find(Setor.class, setor.getId());

			s.setDescricao(setor.getDescricao());

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}
	}

	public List<Setor> getAll() {

		EntityManager em = getEntityManager().createEntityManager();

		List<Setor> lista = null;

		try {

			Query q = em.createQuery("select object(s) from Setor as s");

			lista = q.getResultList();

		} catch (Exception ex) {

			em.close();
		}

		return lista;
	}

}
