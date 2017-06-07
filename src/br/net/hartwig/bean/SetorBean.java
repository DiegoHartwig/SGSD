package br.net.hartwig.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.SetorDAO;
import br.net.hartwig.model.Setor;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "setorBean")
@SessionScoped
public class SetorBean implements Serializable {
	
	private static final long serialVersionUID = 8041504336070108898L;

	private Setor setor = new Setor();

	private DataModel<Setor> setores;

	private int usuario_id;
	
	//Variável que armazena o setor selecionado no dataTable
	private Setor setorSelecionado;

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public void setSetores(DataModel<Setor> setores) {
		this.setores = setores;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}	

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

	public void selecionaSetor() {
		this.setor = setores.getRowData();
	}

	public void novoSetor() {
		setor = new Setor();
	}

	public void addSetor() {

		try {
			SetorDAO dao = new SetorDAO();
			
			dao.Salvar(setor);
			
			String descricao = setor.getDescricao();
			
			// Mensagem de confirmação após salvar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Setor: "+descricao+", cadastrado com sucesso"));

		} catch (Exception ex) {

		}
	}
	
	//Atualizar dados do setor
	public String updateSetor() {

		String retorno = "erro";

		try {
			SetorDAO dao = new SetorDAO();
			dao.Update(setor);
			
			// Mensagem de confirmação após atualizar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados atualizados com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}	

	public DataModel<Setor> getSetores() {
		SetorDAO dao = new SetorDAO();

		try {
			List<Setor> lista = dao.GetALL();
			setores = new ListDataModel<Setor>(lista);
		} catch (Exception e) {

		}
		return setores;
	}

}
