package br.net.hartwig.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * Classe configura��es de par�metros de autentica��o de envio de email
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@Entity
@Table(name="emailconfig")
public class EmailConfig {
	
	@Id	
	@Column(name="id")
	private int id;	
	
	@Column(name="email")
	private String email;	
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="porta")
	private int porta;
	
	@Column(name="smtp")
	private String smtp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
	
	

}

