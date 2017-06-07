package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.net.hartwig.model.Tecnico;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class TecnicoDAO extends DAO {
	
	//M�todo para salvar um T�cnico 
	public void Salvar(Tecnico tecnico){
		//Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		
		try{
			//Inicia a transa��o
			em.getTransaction().begin();
			//Persistindo
			em.persist(tecnico);
			//Commit
			em.getTransaction().commit();
			//Fechando a conex�o
			em.close();
			
		} catch(Exception ex){
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}
		
	}
	
	//Retorna um tecnico atrav�s do Id
	public Tecnico Get(int tecnico_id){
		//Conex�o com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		//Retorna um t�cnico	
		return em.find(Tecnico.class, tecnico_id);			
	}
	
	//M�todo para atualizar as informa��es de um t�cnico	
		public void Update(Tecnico tecnico){
			//Conex�o com o EntityManager
			EntityManager em = getEntityManager().createEntityManager();
				
			try{
				//Inicia a transa��o
				em.getTransaction().begin();
				//Retorna um t�cnico atrav�s do argumento id 
				Tecnico t = em.find(Tecnico.class, tecnico.getId());
				//Setando as informa��es
				t.setNome(tecnico.getNome());
				t.setLogin(tecnico.getLogin());
				t.setSenha(tecnico.getSenha());
				t.setEmail(tecnico.getEmail());
				t.setTelefone(tecnico.getTelefone());				
				t.setProfissao(tecnico.getProfissao());
				t.setObs(tecnico.getObs());
				t.setEquipe(tecnico.getEquipe());
				//Commit			
				em.getTransaction().commit();
				//Fechando a conex�o
				em.close();
					
			} catch(Exception ex){
				//Em caso de erro, rollback
				em.getTransaction().rollback();
			}
				
		}	
		
		//M�todo para deletar um t�cnico
		public void Delete(Tecnico tecnico){
			//Conex�o com o EntityManager
			EntityManager em = getEntityManager().createEntityManager();
				
			try{
				//Iniciando a transa��o
				em.getTransaction().begin();
				//recupera um t�cnico atrav�s do id
				Tecnico t = em.find(Tecnico.class, tecnico.getId());
				//remove tecnico				
				em.remove(t);		
				//commit
				em.getTransaction().commit();
				//Fecha a conex�o
				em.close();
					
			} catch(Exception ex){
				//Em caso de erro, rollback
				em.getTransaction().rollback();
			}
				
		}
		
		//M�todo que retorna uma lista de T�cnicos
		@SuppressWarnings("unchecked")
		public List<Tecnico> GetALL(){
			//Conex�o com o EntityManager
			EntityManager em = getEntityManager().createEntityManager();
			//Lista recebe null
			List<Tecnico> lista = null;
			
			try{
				//Query de consulta
				Query q = em.createQuery("select object(t) from Tecnico as t");
				//lista de t�cnicos
				lista = q.getResultList();
				
			} catch(Exception ex){
				//Fecha a conex�o
				em.close();
			}
			//Retorna uma lista de t�cnicos
			return lista;
		}	
		
		//M�todo de autentica��o para logar no sistema como t�cnico
		public Tecnico getAutenticaTecnico(String login, String senha){
			EntityManager em = getEntityManager().createEntityManager();
			try{
				Tecnico tecnico = (Tecnico)em.createQuery("select t from Tecnico t where t.login = :login and t.senha = :senha")
											.setParameter("login", login)
											.setParameter("senha", senha).getSingleResult();
				
				return tecnico;
				
			} catch (NoResultException e) {
				return null;
						
						
			}
		}

		
	}


