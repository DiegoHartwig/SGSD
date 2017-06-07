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

	// M�todo salvar Setor
	public void Salvar(Setor setor) {
		////Fazendo a conex�o com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transa��o
			em.getTransaction().begin();
			//persistindo
			em.persist(setor);
			//commit
			em.getTransaction().commit();
			//Fechando a conex�o
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// M�todo que retorna um Setor
	public Setor Get(int setor_id) {
		//Fazendo a conex�o com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		//retorna um setor
		return em.find(Setor.class, setor_id);

	}

	// M�todo para atualizar Setor
	public void Update(Setor setor) {
		//Fazendo a conex�o com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transa��o
			em.getTransaction().begin();
			//Retorna um setor atrav�s do id do setor
			Setor s = em.find(Setor.class, setor.getId());
			//Seta as informa��es
			s.setDescricao(setor.getDescricao());
			//Commit
			em.getTransaction().commit();
			//Fecha a conex�o
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}
	}
	
	//M�todo que retorna uma lista de Setores
	@SuppressWarnings("unchecked")
	public List<Setor> GetALL() {
		//Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		//Lista recebe null
		List<Setor> lista = null;

		try {
			//Query que realiza a consulta
			Query q = em.createQuery("select object(s) from Setor as s");
			//Lista de Setores
			lista = q.getResultList();

		} catch (Exception ex) {
			//Fecha a conex�o
			em.close();
		}
		//Retorna uma lista de Setores
		return lista;
	}

}

