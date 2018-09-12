package br.net.hartwig.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.EmailConfigDAO;
import br.net.hartwig.model.EmailConfig;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
@ManagedBean(name = "emailConfigBean")
@RequestScoped
public class EmailConfigBean {

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

			emailConfigDAO.salvar(emailConfig);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Configurado com sucesso"));

		} catch (Exception ex) {

		}
	}

	public String updateEmailConfig() {

		String retorno = "erro";

		try {
			EmailConfigDAO emailConfigDAO = new EmailConfigDAO();
			emailConfigDAO.update(emailConfig);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados atualizados com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void deleteConfig() {
		this.emailConfig = configs.getRowData();

		try {
			EmailConfigDAO emailConfigDao = new EmailConfigDAO();

			emailConfigDao.delete(emailConfig);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Configuração deletada com sucesso"));

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
