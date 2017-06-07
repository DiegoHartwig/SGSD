package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.EmailConfig;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class EmailConfigDAO extends DAO {

	// Método salvar Configuração de autenticação do email
	public void Salvar(EmailConfig emailConfig) {
		////Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transação
			em.getTransaction().begin();
			//persistindo
			em.persist(emailConfig);
			//commit
			em.getTransaction().commit();
			//Fechando a conexão
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	// Método que retorna um email
	public EmailConfig Get(int emailConfig_id) {
		//Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();
		//retorna um setor
		return em.find(EmailConfig.class, emailConfig_id);

	}

	// Método para atualizar as config
	public void Update(EmailConfig emailConfig) {
		//Fazendo a conexão com o Entity Manager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			//Inicia a transação
			em.getTransaction().begin();
			//Retorna um setor através do id do setor
			EmailConfig emailc = em.find(EmailConfig.class, emailConfig.getId());
			//Seta as informações
			emailc.setEmail(emailConfig.getEmail());
			emailc.setSenha(emailConfig.getSenha());
			emailc.setPorta(emailConfig.getPorta());
			emailc.setSmtp(emailConfig.getSmtp());
			//Commit
			em.getTransaction().commit();
			//Fecha a conexão
			em.close();

		} catch (Exception ex) {
			//Em caso de erro, rollback
			em.getTransaction().rollback();
		}
	}
	
	public void Delete(EmailConfig emailConfig) {
		// Conexão com EntityManager
		EntityManager em = getEntityManager().createEntityManager();

		try {
			// Inicia a transação
			em.getTransaction().begin();
			// recupera um usuario através do id
			EmailConfig ec = em.find(EmailConfig.class, emailConfig.getId());
			// remove
			em.remove(ec);
			// commit
			em.getTransaction().commit();
			// Fecha a conexão
			em.close();

		} catch (Exception ex) {
			// Em caso de erro, rollback
			em.getTransaction().rollback();
		}

	}

	
	//Método que retorna uma lista de configurações
		@SuppressWarnings("unchecked")
		public List<EmailConfig> GetALL() {
			//Conexão com o EntityManager
			EntityManager em = getEntityManager().createEntityManager();
			//Lista recebe null
			List<EmailConfig> lista = null;

			try {
				//Query que realiza a consulta
				Query q = em.createQuery("select object(e) from EmailConfig as e");
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
	
	

