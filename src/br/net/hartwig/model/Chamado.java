package br.net.hartwig.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
@Entity
@Table(name = "chamado")
public class Chamado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "data_abertura")
	private Calendar data_abertura = Calendar.getInstance();

	@Column(name = "data_encerramento")
	private Calendar data_encerramento;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "equipamento")
	private String equipamento;

	@Column(name = "acompanhamento")
	private String acompanhamento;

	@Column(name = "status")
	private String status;

	@Column(name = "solucao")
	private String solucao;

	@Column(name = "tipo")
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "usu_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "tec_id")
	private Tecnico tecnico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Calendar data_abertura) {
		this.data_abertura = data_abertura;
	}

	public Calendar getData_encerramento() {
		return data_encerramento;
	}

	public void setData_encerramento(Calendar data_encerramento) {
		this.data_encerramento = data_encerramento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

}
