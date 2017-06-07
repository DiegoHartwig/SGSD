package br.net.hartwig.emails;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
//Classe responsável pelo envio de email após a abertura do chamado
public class Mensagem {	
	
	private String assunto;	
	private String descricao;
	private String emaildestino;
	private String emailAutentica;
	private String senhaAutentica;	
	private int porta;
	private String smtp;
	
	public String getEmailAutentica() {
		return emailAutentica;
	}

	public void setEmailAutentica(String emailAutentica) {
		this.emailAutentica = emailAutentica;
	}		

	public String getSenhaAutentica() {
		return senhaAutentica;
	}

	public void setSenhaAutentica(String senhaAutentica) {
		this.senhaAutentica = senhaAutentica;
	}		

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

	public String getEmaildestino() {
		return emaildestino;
	}

	public void setEmaildestino(String emaildestino) {
		this.emaildestino = emaildestino;
	}

	@SuppressWarnings("deprecation")
	public void EnviarEmail(){
		
    SimpleEmail email = new SimpleEmail();

	try {
	email.setDebug(true);
	email.setHostName(smtp); 
	email.setSmtpPort(porta);                     
	email.setAuthentication(emailAutentica,senhaAutentica);
	email.setSSL(true);
	email.addTo(emaildestino); 
	email.setFrom(emailAutentica); 
	email.setSubject(assunto);
	email.setMsg(descricao);
	email.send();	

	} catch (EmailException e) {

	System.out.println(e.getMessage());

	} 

}
}