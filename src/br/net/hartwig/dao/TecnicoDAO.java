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
	
	//Método para salvar um Técnico 
	public void Salvar(Tecnico tecnico){
		//Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		
		try{
			//Inicia a transação
			em.getTransaction().begin();
			//Persistindo
			em.persist(tecnico);
			//Commit
			em.getTransaction().commit();
			//Fechando a conexão
			em.close();
			
		} catch(Exception ex){
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}
		
	}
	
	//Retorna um tecnico através do Id
	public Tecnico Get(int tecnico_id){
		//Conexão com o EntityManager
		EntityManager em = getEntityManager().createEntityManager();
		//Retorna um técnico	
		return em.find(Tecnico.class, tecnico_id);			
	}
	
	//Método para atualizar as informações de um técnico	
		public void Update(Tecnico tecnico){
			//Conexão com o EntityManager
			EntityManager em = getEntityManager().createEntityManager();
				
			try{
				//Inicia a transação
				em.getTransaction().begin();
				//Retorna um técnico através do argumento id 
				Tecnico t = em.find(Tecnico.class, tecnico.getId());
				//Setando as informações
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
				//Fechando a conexão
				em.close();
					
			} catch(Exception ex){
				//Em caso de erro, rollback
				em.getTransaction().rollback();
			}
				
		}	
		
		//Método para deletar um técnico
		public void Delete(Tecnico tecnico){
			//Conexão com o EntityManager
			EntityManager em = getEntityManager().createEntityManager();
				
			try{
				//Iniciando a transação
				em.getTransaction().begin();
				//recupera um técnico através do id
				Tecnico t = em.find(Tecnico.class, tecnico.getId());
				//remove tecnico				
				em.remove(t);		
				//commit
				em.getTransaction().commit();
				//Fecha a conexão
				em.close();
					
			} catch(Exception ex){
				//Em caso de erro, rollback
				em.getTransaction().rollback();
			}
				
		}
		
		//Método que retorna uma lista de Técnicos
		@SuppressWarnings("unchecked")
		public List<Tecnico> GetALL(){
			//Conexão com o EntityManager
			EntityManager em = getEntityManager().createEntityManager();
			//Lista recebe null
			List<Tecnico> lista = null;
			
			try{
				//Query de consulta
				Query q = em.createQuery("select object(t) from Tecnico as t");
				//lista de técnicos
				lista = q.getResultList();
				
			} catch(Exception ex){
				//Fecha a conexão
				em.close();
			}
			//Retorna uma lista de técnicos
			return lista;
		}	
		
		//Método de autenticação para logar no sistema como técnico
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


