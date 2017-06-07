package br.net.hartwig.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@Entity
@Table(name="tecnico")
public class Tecnico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tecnico")
	private int id;
		
	@Column(name="nome")
	private String nome;
	
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefone")
	private String telefone;	
	
	@Column(name="profissao")
	private String profissao;
	
	@Column(name="obs")
	private String obs;
	
	@OneToMany(mappedBy="tecnico")
	private List<Chamado> chamados;

	@ManyToOne
	@JoinColumn(name="equipe_id")
	private Equipe equipe;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}	

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	
		
}


