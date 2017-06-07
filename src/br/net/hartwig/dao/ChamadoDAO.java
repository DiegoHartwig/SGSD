package br.net.hartwig.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Chamado;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class ChamadoDAO extends DAO {
	// M�todo Salvar Chamado
	public void Salvar(Chamado chamado) {
		//Fazendo a conex�o com o Entity Manager da classe DAO do Hibernate 
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicializando transa��o
			em.getTransaction().begin();
			//Persistindo o chamado
			em.persist(chamado);
			//commmit
			em.getTransaction().commit();
			//fechando conex�o
			em.close();

		} catch (Exception ex) {
			//Em caso de erro rollback
			em.getTransaction().rollback();
		}

	}

	// M�todo que retorna um chamado com par�metro chamado_id
	public Chamado Get(int chamado_id) {
		//Fazendo a conex�o com o Entity Manager 
		EntityManager em = getEntityManager().createEntityManager();
		//Retorna um chamado atrav�s do argumento id
		return em.find(Chamado.class, chamado_id);

	}

	// m�todo update chamado
	public void Update(Chamado chamado) {
		//Fazendo a conex�o com o Entity Manager 
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia Transa��o
			em.getTransaction().begin();
			//Retorna um chamado atrav�s do argumento id do chamado
			Chamado c = em.find(Chamado.class, chamado.getId());
			//Setando as informa��es 
			c.setDescricao(chamado.getDescricao());						
			c.setTitulo(chamado.getTitulo());
			c.setTipo(chamado.getTipo());			
			c.setTecnico(chamado.getTecnico());
			c.setUsuario(chamado.getUsuario());
			c.setAcompanhamento(chamado.getAcompanhamento());
			c.setSolucao(chamado.getSolucao());
			c.setStatus(chamado.getStatus());
			//Ao selecionar a op��o concluido ir� gravar a hora no banco
			if(chamado.getStatus().equals("Concluido")){				
				c.setData_encerramento(Calendar.getInstance());					
			}			
			//Commit
			em.getTransaction().commit();
			//Fecha a conex�o
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}
	}

	// M�todo para deletar chamado
	public void Delete(Chamado chamado) {		
		//Fazendo a conex�o com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia transa��o
			em.getTransaction().begin();
			//recupera um chamado
			Chamado c = em.find(Chamado.class, chamado.getId());
			//remove
			em.remove(c);
			//commit
			em.getTransaction().commit();
			//Fecha a conex�o
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// M�todo para listar todos os chamados
	@SuppressWarnings("unchecked")
	public List<Chamado> GetALL() {
		//Fazendo a conex�o com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		//List chamado recebe null
		List<Chamado> lista = null;

		try {
			//Query que seleciona todos os chamados
			Query q = em.createQuery("select object(c) from Chamado as c order by c.data_abertura DESC ");
			//Retorna uma lista de chamados
			lista = q.getResultList();

		} catch (Exception ex) {
			//Fecha conex�o
			em.close();
		}
		//Retorna lista com os objetos
		return lista;
	}

}
