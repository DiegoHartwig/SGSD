package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Equipe;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class EquipeDAO extends DAO {

	public void salvar(Equipe equipe) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(equipe);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public Equipe get(int equipe_id) {

		EntityManager em = getEntityManager().createEntityManager();

		return em.find(Equipe.class, equipe_id);

	}

	public void update(Equipe equipe) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Equipe e = em.find(Equipe.class, equipe.getId());

			e.setDescricao(equipe.getDescricao());

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public List<Equipe> getAll() {

		EntityManager em = getEntityManager().createEntityManager();

		List<Equipe> lista = null;

		try {

			Query q = em.createQuery("select object(e) from Equipe as e");

			lista = q.getResultList();

		} catch (Exception ex) {

			em.close();
		}

		return lista;
	}

}
