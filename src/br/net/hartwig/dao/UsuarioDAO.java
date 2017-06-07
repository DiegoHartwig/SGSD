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

	// Método para salvar um usuário
	public void Salvar(Usuario usuario) {
		// Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			// Inicia a transação
			em.getTransaction().begin();
			// Persistindo
			em.persist(usuario);
			// Commit
			em.getTransaction().commit();
			// Fechando a conexão
			em.close();

		} catch (Exception ex) {
			// Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Retorna um usuario através do id
	public Usuario Get(int usuario_id) {
		// Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		// Retorna um usuário
		return em.find(Usuario.class, usuario_id);
	}

	// Método para atualizar informações de um usuário
	public void Update(Usuario usuario) {
		// Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			// Iniciando a transação
			em.getTransaction().begin();
			// Retorna um Usuário através do argumento id do Usuário
			Usuario u = em.find(Usuario.class, usuario.getId());
			// Setando as informações
			u.setNome(usuario.getNome());
			u.setEmail(usuario.getEmail());
			u.setTelefone(usuario.getTelefone());
			u.setProfissao(usuario.getProfissao());
			u.setSetor(usuario.getSetor());
			u.setObs(usuario.getObs());
			// Commit
			em.getTransaction().commit();
			// Fecha a conexão
			em.close();

		} catch (Exception ex) {
			// Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Método para deletar um usuário
	public void Delete(Usuario usuario) {
		// Conexão com EntityManager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			// Inicia a transação
			em.getTransaction().begin();
			// recupera um usuario através do id
			Usuario u = em.find(Usuario.class, usuario.getId());
			// remove
			em.remove(u);
			// commit
			em.getTransaction().commit();
			// Fecha a conexão
			em.close();

		} catch (Exception ex) {
			// Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Retorna uma lista de usuários
	@SuppressWarnings("unchecked")
	public List<Usuario> GetALL() {
		// Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		// Lista recebe null
		List<Usuario> lista = null;

		try {
			// Query da consulta
			Query q = em.createQuery("select object(u) from Usuario as u order by u.nome");
			// Lista recebe uma lista
			lista = q.getResultList();

		} catch (Exception ex) {
			// Fecha a conexão
			em.close();
		}
		// Retorna uma lista
		return lista;
	}



}
