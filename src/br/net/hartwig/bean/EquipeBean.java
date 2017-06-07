
package br.net.hartwig.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.EquipeDAO;
import br.net.hartwig.model.Equipe;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "equipeBean")
@SessionScoped
public class EquipeBean implements Serializable{
	
	private static final long serialVersionUID = 898756567772993466L;

	private Equipe equipe = new Equipe();

	private DataModel<Equipe> equipes;

	private int usuario_id;
	
	//Variável que armazena a equipe selecionada no dataTable
	private Equipe equipeSelecionada;

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public void setEquipes(DataModel<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}	

	public Equipe getEquipeSelecionada() {
		return equipeSelecionada;
	}

	public void setEquipeSelecionada(Equipe equipeSelecionada) {
		this.equipeSelecionada = equipeSelecionada;
	}

	public void selecionaEquipe() {
		this.equipe = equipes.getRowData();
	}

	public String updateEquipe() {

		String retorno = "erro";

		try {
			EquipeDAO dao = new EquipeDAO();
			dao.Update(equipe);
			// Mensagem de confirmação após atualizar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados atualizados com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void novaEquipe() {
		equipe = new Equipe();
	}

	public void addEquipe() {

		try {
			EquipeDAO dao = new EquipeDAO();
			
			dao.Salvar(equipe);
			
			String descricao = equipe.getDescricao();

			// Mensagem de confirmação após salvar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Equipe: "+descricao+" cadastrada com sucesso"));

		} catch (Exception ex) {

		}

	}

	public DataModel<Equipe> getEquipes() {
		EquipeDAO dao = new EquipeDAO();

		try {
			List<Equipe> lista = dao.GetALL();
			equipes = new ListDataModel<Equipe>(lista);
		} catch (Exception e) {

		}
		return equipes;
	}

}
