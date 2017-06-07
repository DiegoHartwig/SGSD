package br.net.hartwig.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@Entity
@Table(name="pesquisa")
public class Pesquisa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;	
	
	@Column(name="Questao1")
	private String questao1;
	
	@Column(name="Questao2")
	private String questao2;
	
	@Column(name="Questao3")
	private String questao3;
	
	@Column(name="Questao4")
	private String questao4;
	
	@Column(name="Questao5")
	private String questao5;
	
	@Column(name = "data_pesquisa")	
	private Calendar data_pesquisa = Calendar.getInstance();	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestao1() {
		return questao1;
	}

	public void setQuestao1(String questao1) {
		this.questao1 = questao1;
	}

	public String getQuestao2() {
		return questao2;
	}

	public void setQuestao2(String questao2) {
		this.questao2 = questao2;
	}

	public String getQuestao3() {
		return questao3;
	}

	public void setQuestao3(String questao3) {
		this.questao3 = questao3;
	}

	public String getQuestao4() {
		return questao4;
	}

	public void setQuestao4(String questao4) {
		this.questao4 = questao4;
	}

	public String getQuestao5() {
		return questao5;
	}

	public void setQuestao5(String questao5) {
		this.questao5 = questao5;
	}

	public Calendar getData_pesquisa() {
		return data_pesquisa;
	}

	public void setData_pesquisa(Calendar data_pesquisa) {
		this.data_pesquisa = data_pesquisa;
	}
	

}

