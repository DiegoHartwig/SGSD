package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Pesquisa;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class PesquisaDAO extends DAO {

	public void salvar(Pesquisa pesquisa) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(pesquisa);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public Pesquisa get(int pesquisa_id) {

		EntityManager em = getEntityManager().createEntityManager();

		return em.find(Pesquisa.class, pesquisa_id);

	}

	public List<Pesquisa> getAll() {

		EntityManager em = getEntityManager().createEntityManager();

		List<Pesquisa> lista = null;

		try {

			Query q = em.createQuery("select object(p) from Pesquisa as p order by p.data_pesquisa DESC ");

			lista = q.getResultList();

		} catch (Exception ex) {

			em.close();
		}

		return lista;
	}

}
