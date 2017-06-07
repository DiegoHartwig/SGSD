package br.net.hartwig.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.EmailConfigDAO;
import br.net.hartwig.model.EmailConfig;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "emailConfigBean")
@SessionScoped
public class EmailConfigBean  {	

	private EmailConfig emailConfig = new EmailConfig();	
	
	private DataModel<EmailConfig> configs;	

	public void setConfigs(DataModel<EmailConfig> configs) {
		this.configs = configs;
	}

	public EmailConfig getEmailConfig() {
		return emailConfig;
	}

	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	public void selecionaEmailConfig() {
		this.emailConfig = configs.getRowData();
	}

	public void addEmailConfig() {

		try {
			EmailConfigDAO emailConfigDAO = new EmailConfigDAO();
			
			emailConfigDAO.Salvar(emailConfig);		
			
			
			// Mensagem de confirmação após salvar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Configurado com sucesso"));

		} catch (Exception ex) {

		}
	}
	
	//Atualizar dados 
	public String updateEmailConfig() {

		String retorno = "erro";

		try {
			EmailConfigDAO emailConfigDAO = new EmailConfigDAO();
			emailConfigDAO.Update(emailConfig);
			
			// Mensagem de confirmação após atualizar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados atualizados com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}	
	
	// delete
	public void deleteConfig() {
		this.emailConfig = configs.getRowData();

		try {
			EmailConfigDAO emailConfigDao = new EmailConfigDAO();
			
			emailConfigDao.Delete(emailConfig);			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario excluído com sucesso"));
			
		} catch (Exception ex) {

		}		

	}
	

	public DataModel<EmailConfig> getConfigs() {
		EmailConfigDAO emailConfigDao = new EmailConfigDAO();

		try {
			List<EmailConfig> lista = emailConfigDao.GetALL();
			configs = new ListDataModel<EmailConfig>(lista);
		} catch (Exception e) {

		}
		return configs;
	}




}
