package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.net.hartwig.model.Tecnico;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class TecnicoDAO extends DAO {

	public void salvar(Tecnico tecnico) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(tecnico);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public Tecnico get(int tecnico_id) {

		EntityManager em = getEntityManager().createEntityManager();

		return em.find(Tecnico.class, tecnico_id);
	}

	public void Update(Tecnico tecnico) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Tecnico t = em.find(Tecnico.class, tecnico.getId());

			t.setNome(tecnico.getNome());
			t.setLogin(tecnico.getLogin());
			t.setSenha(tecnico.getSenha());
			t.setEmail(tecnico.getEmail());
			t.setTelefone(tecnico.getTelefone());
			t.setProfissao(tecnico.getProfissao());
			t.setObs(tecnico.getObs());
			t.setEquipe(tecnico.getEquipe());

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public void delete(Tecnico tecnico) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Tecnico t = em.find(Tecnico.class, tecnico.getId());

			em.remove(t);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public List<Tecnico> getAll() {

		EntityManager em = getEntityManager().createEntityManager();

		List<Tecnico> lista = null;

		try {

			Query q = em.createQuery("select object(t) from Tecnico as t");

			lista = q.getResultList();

		} catch (Exception ex) {

			em.close();
		}

		return lista;
	}

	public Tecnico getAutenticaTecnico(String login, String senha) {
		EntityManager em = getEntityManager().createEntityManager();
		try {
			Tecnico tecnico = (Tecnico) em
					.createQuery("select t from Tecnico t where t.login = :login and t.senha = :senha")
					.setParameter("login", login).setParameter("senha", senha).getSingleResult();

			return tecnico;

		} catch (NoResultException e) {
			return null;

		}
	}

}
