package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Pesquisa;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class PesquisaDAO extends DAO {

	// Método enviar pesquisa
	public void Salvar(Pesquisa pesquisa) {
		////Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transação
			em.getTransaction().begin();
			//persistindo
			em.persist(pesquisa);
			//commit
			em.getTransaction().commit();
			//Fechando a conexão
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Método que retorna uma pesquisa
	public Pesquisa Get(int pesquisa_id) {
		//Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		//retorna 
		return em.find(Pesquisa.class, pesquisa_id);

	}	
	
	//Método que retorna lista de pesquisa
	@SuppressWarnings("unchecked")
	public List<Pesquisa> GetALL() {
		//Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		//Lista recebe null
		List<Pesquisa> lista = null;

		try {
			//Query que realiza a consulta
			Query q = em.createQuery("select object(p) from Pesquisa as p order by p.data_pesquisa DESC ");
			//Lista 
			lista = q.getResultList();

		} catch (Exception ex) {
			//Fecha a conexão
			em.close();
		}
		//Retorna uma lista 
		return lista;
	}

}

