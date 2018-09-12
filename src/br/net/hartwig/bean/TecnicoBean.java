
package br.net.hartwig.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */

@ManagedBean(name = "tecBean")
@SessionScoped
public class TecnicoBean implements Serializable {

	private static final long serialVersionUID = -8826181376019953978L;

	private Tecnico tecnico = new Tecnico();

	private TecnicoDAO tecnicoDAO = new TecnicoDAO();

	private DataModel<Tecnico> tecnicos;

	private Tecnico tecnicoSelecionado;

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
			List<Tecnico> lista = dao.getAll();
			tecnicos = new ListDataModel<Tecnico>(lista);
		} catch (Exception e) {

		}

		return tecnicos;
	}

	public String deleteTecnico() {
		this.tecnico = tecnicos.getRowData();

		String retorno = "erro";

		try {
			TecnicoDAO dao = new TecnicoDAO();

			dao.delete(tecnico);

			String nome = tecnico.getNome();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Técnico: " + nome + ", excluído do Sistema"));

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
			tecnico.setEquipe(equipeDao.get(equipe_id));

			dao.Update(tecnico);

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

			tecnico.setEquipe(equipeDao.get(equipe_id));

			String nome = tecnico.getNome();

			dao.salvar(tecnico);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Técnico: " + nome + ", cadastrado com sucesso"));

			retorno = "tecnicos";

		} catch (Exception ex) {

		}
		return retorno;

	}

	public Collection<SelectItem> getCarregarEquipes() {

		EquipeDAO dao = new EquipeDAO();

		Collection<SelectItem> lista = new ArrayList<SelectItem>();

		lista.add(new SelectItem("-- Selecione a equipe --"));

		List<Equipe> listaEquipe = dao.getAll();

		for (int i = 0; i < listaEquipe.size(); i++) {
			lista.add(new SelectItem(listaEquipe.get(i).getId(), listaEquipe.get(i).getDescricao()));
		}

		return lista;
	}

	public String logar() {

		tecnico = tecnicoDAO.getAutenticaTecnico(tecnico.getLogin(), tecnico.getSenha());

		if (tecnico == null) {
			tecnico = new Tecnico();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "login ou senha incorreto!", "Erro no login"));
			return null;
		} else {

			Object b = new Object();

			SessionUtil.setParam("TecnicoLogado", b);

			UsuarioLog = tecnico.getLogin();

			return "/restrito/chamados.xhtml?faces-redirect=true";

		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index?faces-redirect=true";

	}

}
