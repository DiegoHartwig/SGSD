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

	// M�todo enviar pesquisa
	public void Salvar(Pesquisa pesquisa) {
		////Fazendo a conex�o com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transa��o
			em.getTransaction().begin();
			//persistindo
			em.persist(pesquisa);
			//commit
			em.getTransaction().commit();
			//Fechando a conex�o
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// M�todo que retorna uma pesquisa
	public Pesquisa Get(int pesquisa_id) {
		//Fazendo a conex�o com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		//retorna 
		return em.find(Pesquisa.class, pesquisa_id);

	}	
	
	//M�todo que retorna lista de pesquisa
	@SuppressWarnings("unchecked")
	public List<Pesquisa> GetALL() {
		//Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		//Lista recebe null
		List<Pesquisa> lista = null;

		try {
			//Query que realiza a consulta
			Query q = em.createQuery("select object(p) from Pesquisa as p order by p.data_pesquisa DESC ");
			//Lista 
			lista = q.getResultList();

		} catch (Exception ex) {
			//Fecha a conex�o
			em.close();
		}
		//Retorna uma lista 
		return lista;
	}

}

