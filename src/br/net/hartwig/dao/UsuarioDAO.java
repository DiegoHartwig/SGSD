package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Usuario;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class UsuarioDAO extends DAO {

	// M�todo para salvar um usu�rio
	public void Salvar(Usuario usuario) {
		// Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			// Inicia a transa��o
			em.getTransaction().begin();
			// Persistindo
			em.persist(usuario);
			// Commit
			em.getTransaction().commit();
			// Fechando a conex�o
			em.close();

		} catch (Exception ex) {
			// Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Retorna um usuario atrav�s do id
	public Usuario Get(int usuario_id) {
		// Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		// Retorna um usu�rio
		return em.find(Usuario.class, usuario_id);
	}

	// M�todo para atualizar informa��es de um usu�rio
	public void Update(Usuario usuario) {
		// Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			// Iniciando a transa��o
			em.getTransaction().begin();
			// Retorna um Usu�rio atrav�s do argumento id do Usu�rio
			Usuario u = em.find(Usuario.class, usuario.getId());
			// Setando as informa��es
			u.setNome(usuario.getNome());
			u.setEmail(usuario.getEmail());
			u.setTelefone(usuario.getTelefone());
			u.setProfissao(usuario.getProfissao());
			u.setSetor(usuario.getSetor());
			u.setObs(usuario.getObs());
			// Commit
			em.getTransaction().commit();
			// Fecha a conex�o
			em.close();

		} catch (Exception ex) {
			// Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// M�todo para deletar um usu�rio
	public void Delete(Usuario usuario) {
		// Conex�o com EntityManager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			// Inicia a transa��o
			em.getTransaction().begin();
			// recupera um usuario atrav�s do id
			Usuario u = em.find(Usuario.class, usuario.getId());
			// remove
			em.remove(u);
			// commit
			em.getTransaction().commit();
			// Fecha a conex�o
			em.close();

		} catch (Exception ex) {
			// Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Retorna uma lista de usu�rios
	@SuppressWarnings("unchecked")
	public List<Usuario> GetALL() {
		// Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		// Lista recebe null
		List<Usuario> lista = null;

		try {
			// Query da consulta
			Query q = em.createQuery("select object(u) from Usuario as u order by u.nome");
			// Lista recebe uma lista
			lista = q.getResultList();

		} catch (Exception ex) {
			// Fecha a conex�o
			em.close();
		}
		// Retorna uma lista
		return lista;
	}



}
