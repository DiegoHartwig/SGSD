
package br.net.hartwig.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.net.hartwig.dao.EquipeDAO;
import br.net.hartwig.dao.TecnicoDAO;
import br.net.hartwig.filter.SessionUtil;
import br.net.hartwig.model.Equipe;
import br.net.hartwig.model.Tecnico;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@SessionScoped
@ManagedBean(name = "tecBean")

public class TecnicoBean implements Serializable{
	
	private static final long serialVersionUID = -8826181376019953978L;

	private Tecnico tecnico = new Tecnico();
	
	private TecnicoDAO tecnicoDAO = new TecnicoDAO();

	private DataModel<Tecnico> tecnicos;
	
	//Variável que armazena o tecnico selecionado no dataTable
	private Tecnico tecnicoSelecionado;
	
	//usuario da sessao
	private String UsuarioLog;

	private int equipe_id;

	public int getEquipe_id() {
		return equipe_id;
	}

	public void setEquipe_id(int equipe_id) {
		this.equipe_id = equipe_id;
	}

	public void setTecnicos(DataModel<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}	

	public TecnicoDAO getTecnicoDAO() {
		return tecnicoDAO;
	}

	public void setTecnicoDAO(TecnicoDAO tecnicoDAO) {
		this.tecnicoDAO = tecnicoDAO;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}


	public Tecnico getTecnicoSelecionado() {
		return tecnicoSelecionado;
	}

	public void setTecnicoSelecionado(Tecnico tecnicoSelecionado) {
		this.tecnicoSelecionado = tecnicoSelecionado;
	}	

	public String getUsuarioLog() {
		return UsuarioLog;
	}

	public void setUsuarioLog(String usuarioLog) {
		UsuarioLog = usuarioLog;
	}

	public void selecionarTecnico() {
		this.tecnico = tecnicos.getRowData();
	}

	public DataModel<Tecnico> getTecnicos() {
		TecnicoDAO dao = new TecnicoDAO();

		try {
			List<Tecnico> lista = dao.GetALL();
			tecnicos = new ListDataModel<Tecnico>(lista);
		} catch (Exception e) {

		}

		return tecnicos;
	}

	// delete tecnico
	public String deleteTecnico() {
		this.tecnico = tecnicos.getRowData();

		String retorno = "erro";

		try {
			TecnicoDAO dao = new TecnicoDAO();
			
			dao.Delete(tecnico);
			
			String nome = tecnico.getNome();

			// Mensagem de confirmação após deletar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Técnico: "+nome+", excluído do Sistema"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public String updateTecnico() {

		String retorno = "erro";

		try {
			TecnicoDAO dao = new TecnicoDAO();
			
			EquipeDAO equipeDao = new EquipeDAO();
			tecnico.setEquipe(equipeDao.Get(equipe_id));
			
			dao.Update(tecnico);

			// Mensagem de confirmação após atualizar os dados do técnico
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados atualizados com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void novoTecnico() {
		tecnico = new Tecnico();
	}

	public String addTecnico() {

		String retorno = "erro";

		try {
			TecnicoDAO dao = new TecnicoDAO();

			EquipeDAO equipeDao = new EquipeDAO();
			tecnico.setEquipe(equipeDao.Get(equipe_id));
			String nome = tecnico.getNome();
			dao.Salvar(tecnico);
			// Mensagem de confirmação após salvar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Técnico: "+nome+", cadastrado com sucesso"));

			retorno = "tecnicos";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public Collection<SelectItem> getCarregarEquipes() {
		EquipeDAO dao = new EquipeDAO();
		Collection<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("-- Selecione a equipe --"));
		List<Equipe> listaEquipe = dao.GetALL();

		for (int i = 0; i < listaEquipe.size(); i++) {
			lista.add(new SelectItem(listaEquipe.get(i).getId(), listaEquipe.get(i).getDescricao()));
		}

		return lista;
	}
	

	//autenticação de login
	public String logar(){
		
		tecnico = tecnicoDAO.getAutenticaTecnico(tecnico.getLogin(), tecnico.getSenha());
		
		if (tecnico == null) {
			tecnico = new Tecnico();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "login ou senha incorreto!",
							"Erro no login"));
			return null;
		}else {
			
			//Se digitar um login e senha correto
			Object b = new Object();
			
			SessionUtil.setParam("TecnicoLogado", b);
			UsuarioLog = tecnico.getLogin();
			//System.out.println(UsuarioLog);
			return "/restrito/chamados.xhtml?faces-redirect=true";
			
		}
	}
	
	//Método logout
			public String logout() { 
				FacesContext.getCurrentInstance().getExternalContext() .invalidateSession();
				
				return "/index?faces-redirect=true"; 
			
			
			}

}
