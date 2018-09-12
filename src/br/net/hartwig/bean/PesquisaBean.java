package br.net.hartwig.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.PesquisaDAO;
import br.net.hartwig.model.Pesquisa;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
@ManagedBean(name = "pesquisaBean")
@RequestScoped
public class PesquisaBean implements Serializable {

	private static final long serialVersionUID = -1977722218611733118L;

	private Pesquisa pesquisa = new Pesquisa();

	private DataModel<Pesquisa> pesquisas;

	private List<Pesquisa> pesquisaFiltrada;

	private Pesquisa pesquisaSelecionada;

	public Pesquisa getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Pesquisa pesquisa) {
		this.pesquisa = pesquisa;
	}

	public void setPesquisas(DataModel<Pesquisa> pesquisas) {
		this.pesquisas = pesquisas;
	}

	public void selecionaPesquisa() {
		this.pesquisa = pesquisas.getRowData();
	}

	public void novaPesquisa() {
		pesquisa = new Pesquisa();
	}

	public List<Pesquisa> getPesquisaFiltrada() {
		return pesquisaFiltrada;
	}

	public void setPesquisaFiltrada(List<Pesquisa> pesquisaFiltrada) {
		this.pesquisaFiltrada = pesquisaFiltrada;
	}

	public Pesquisa getPesquisaSelecionada() {
		return pesquisaSelecionada;
	}

	public void setPesquisaSelecionada(Pesquisa pesquisaSelecionada) {
		this.pesquisaSelecionada = pesquisaSelecionada;
	}

	public void addPesquisa() {

		try {
			PesquisaDAO dao = new PesquisaDAO();

			dao.salvar(pesquisa);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Pesquisa de satisfação enviada com sucesso! Muito Obrigado!"));

		} catch (Exception ex) {

		}
	}

	public DataModel<Pesquisa> getPesquisas() {
		PesquisaDAO dao = new PesquisaDAO();

		try {
			List<Pesquisa> lista = dao.getAll();
			pesquisas = new ListDataModel<Pesquisa>(lista);
		} catch (Exception e) {

		}
		return pesquisas;
	}

}
