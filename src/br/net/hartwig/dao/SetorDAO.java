package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Setor;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class SetorDAO extends DAO {

	// Método salvar Setor
	public void Salvar(Setor setor) {
		////Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transação
			em.getTransaction().begin();
			//persistindo
			em.persist(setor);
			//commit
			em.getTransaction().commit();
			//Fechando a conexão
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Método que retorna um Setor
	public Setor Get(int setor_id) {
		//Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		//retorna um setor
		return em.find(Setor.class, setor_id);

	}

	// Método para atualizar Setor
	public void Update(Setor setor) {
		//Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transação
			em.getTransaction().begin();
			//Retorna um setor através do id do setor
			Setor s = em.find(Setor.class, setor.getId());
			//Seta as informações
			s.setDescricao(setor.getDescricao());
			//Commit
			em.getTransaction().commit();
			//Fecha a conexão
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}
	}
	
	//Método que retorna uma lista de Setores
	@SuppressWarnings("unchecked")
	public List<Setor> GetALL() {
		//Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		//Lista recebe null
		List<Setor> lista = null;

		try {
			//Query que realiza a consulta
			Query q = em.createQuery("select object(s) from Setor as s");
			//Lista de Setores
			lista = q.getResultList();

		} catch (Exception ex) {
			//Fecha a conexão
			em.close();
		}
		//Retorna uma lista de Setores
		return lista;
	}

}

