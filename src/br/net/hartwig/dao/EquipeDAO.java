package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Equipe;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class EquipeDAO extends DAO {
	
	//Método salvar equipe 
	public void Salvar(Equipe equipe){
		//Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		
		try{
			//Inicia transação
			em.getTransaction().begin();
			//persistindo
			em.persist(equipe);
			//commit
			em.getTransaction().commit();
			//Fecha a conexão
			em.close();
			
		} catch(Exception ex){
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}
		
	}
	
	//Método que retorna uma equipe através do id
	public Equipe Get(int equipe_id){
		//Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		////Retorna uma equipe 
		return em.find(Equipe.class, equipe_id);
			
	}
	
	//Método update	
		public void Update(Equipe equipe){
			//Fazendo a conexão com o Entity Manager
			EntityManager em = getEntityManager().createEntityManager();
				
			try{
				//Inicia Transação
				em.getTransaction().begin();
				//Retorna uma equipe através do argumento id da equipe
				Equipe e = em.find(Equipe.class, equipe.getId());
				//seta os valores 
				e.setDescricao(equipe.getDescricao());				
				em.getTransaction().commit();
				em.close();
					
			} catch(Exception ex){
					//Em caso de erro, rollback
					em.getTransaction().rollback();
			}
				
		}		
		
		//Método que retorna uma lista de Equipes
		@SuppressWarnings("unchecked")
		public List<Equipe> GetALL(){
			//Conexão com EntityManager
			EntityManager em = getEntityManager().createEntityManager();
			//Lista recebe null
			List<Equipe> lista = null;
			
			try{
				//Query, seleciona todas as equipes
				Query q = em.createQuery("select object(e) from Equipe as e");
				//Retorna uma lista de equipes
				lista = q.getResultList();
				
			} catch(Exception ex){
				//Fecha a conexão
				em.close();
			}
			//Retona uma lista de equipes
			return lista;
		}
				
}	

