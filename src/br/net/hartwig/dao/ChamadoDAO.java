package br.net.hartwig.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Chamado;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
public class ChamadoDAO extends DAO {

	public void salvar(Chamado chamado) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(chamado);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public Chamado get(int chamado_id) {

		EntityManager em = getEntityManager().createEntityManager();

		return em.find(Chamado.class, chamado_id);

	}

	public void update(Chamado chamado) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Chamado c = em.find(Chamado.class, chamado.getId());

			c.setDescricao(chamado.getDescricao());
			c.setTitulo(chamado.getTitulo());
			c.setTipo(chamado.getTipo());
			c.setTecnico(chamado.getTecnico());
			c.setUsuario(chamado.getUsuario());
			c.setAcompanhamento(chamado.getAcompanhamento());
			c.setSolucao(chamado.getSolucao());
			c.setStatus(chamado.getStatus());

			if (chamado.getStatus().equals("Concluido")) {
				c.setData_encerramento(Calendar.getInstance());
			}

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}
	}

	public void delete(Chamado chamado) {

		EntityManager em = getEntityManager().createEntityManager();

		try {

			em.getTransaction().begin();

			Chamado c = em.find(Chamado.class, chamado.getId());

			em.remove(c);

			em.getTransaction().commit();

			em.close();

		} catch (Exception ex) {

			em.getTransaction().rollback();
		}

	}

	public List<Chamado> getAll() {

		EntityManager em = getEntityManager().createEntityManager();

		List<Chamado> lista = null;

		try {

			Query q = em.createQuery("select object(c) from Chamado as c order by c.data_abertura DESC ");

			lista = q.getResultList();

		} catch (Exception ex) {

			em.close();
		}

		return lista;
	}

}
