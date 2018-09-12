package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Usuario;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class UsuarioDAO extends DAO {

	public void salvar(Usuario usuario) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(usuario);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public Usuario get(int usuario_id) {

		EntityManager em = getEntityManager().createEntityManager();

		return em.find(Usuario.class, usuario_id);
	}

	public void update(Usuario usuario) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Usuario u = em.find(Usuario.class, usuario.getId());

			u.setNome(usuario.getNome());
			u.setEmail(usuario.getEmail());
			u.setTelefone(usuario.getTelefone());
			u.setProfissao(usuario.getProfissao());
			u.setSetor(usuario.getSetor());
			u.setObs(usuario.getObs());

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public void delete(Usuario usuario) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Usuario u = em.find(Usuario.class, usuario.getId());

			em.remove(u);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public List<Usuario> GetALL() {

		EntityManager em = getEntityManager().createEntityManager();

		List<Usuario> lista = null;

		try {

			Query q = em.createQuery("select object(u) from Usuario as u order by u.nome");

			lista = q.getResultList();

		} catch (Exception ex) {

			em.close();
		}

		return lista;
	}

}
