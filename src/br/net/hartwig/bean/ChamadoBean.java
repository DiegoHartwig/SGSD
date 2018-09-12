package br.net.hartwig.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import br.net.hartwig.dao.ChamadoDAO;
import br.net.hartwig.dao.EmailConfigDAO;
import br.net.hartwig.dao.TecnicoDAO;
import br.net.hartwig.dao.UsuarioDAO;
import br.net.hartwig.emails.Mensagem;
import br.net.hartwig.model.Chamado;
import br.net.hartwig.model.Tecnico;
import br.net.hartwig.model.Usuario;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
@ManagedBean(name = "chamadoBean")
@RequestScoped
public class ChamadoBean implements Serializable {

	private static final long serialVersionUID = -6667994166226498861L;
	private Chamado chamado = new Chamado();
	private DataModel<Chamado> chamados;
	private int usuario_id;
	private int tecnico_id;
	private List<Chamado> chamadosFiltrados;
	private Chamado chamadoSelecionado;

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public int getTecnico_id() {
		return tecnico_id;
	}

	public void setTecnico_id(int tecnico_id) {
		this.tecnico_id = tecnico_id;
	}

	public void selecionarChamado() {
		this.chamado = chamados.getRowData();
	}

	public DataModel<Chamado> getChamados() {

		ChamadoDAO dao = new ChamadoDAO();

		try {

			List<Chamado> lista = dao.getAll();
			chamados = new ListDataModel<Chamado>(lista);
		} catch (Exception e) {

		}

		return chamados;
	}

	public void setChamados(DataModel<Chamado> chamados) {
		this.chamados = chamados;
	}

	public List<Chamado> getChamadosFiltrados() {
		return chamadosFiltrados;
	}

	public void setChamadosFiltrados(List<Chamado> chamadosFiltrados) {
		this.chamadosFiltrados = chamadosFiltrados;
	}

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;

	}

	public void novoChamado() {
		chamado = new Chamado();

	}

	public String addChamado() {
		String retorno = "erro";
		int numero = 0;

		try {

			ChamadoDAO dao = new ChamadoDAO();

			TecnicoDAO tecnicodao = new TecnicoDAO();

			UsuarioDAO usuariodao = new UsuarioDAO();

			EmailConfigDAO emailConfigDao = new EmailConfigDAO();

			chamado.setTecnico(tecnicodao.get(tecnico_id));

			chamado.setUsuario(usuariodao.get(usuario_id));

			dao.salvar(chamado);

			numero = chamado.getId();

			String titulo = chamado.getTitulo();
			String descricao = chamado.getDescricao();
			String destinatario = chamado.getUsuario().getEmail();
			String emailAut = emailConfigDao.get(1).getEmail();
			String senhaAut = emailConfigDao.get(1).getSenha();
			String smtpserver = emailConfigDao.get(1).getSmtp();
			int portasmtp = emailConfigDao.get(1).getPorta();
			String emailAutomatico = "Este é um e-mail automático enviado pelo Sistema do Service Desk - SGSD! ";

			Mensagem mensagem = new Mensagem();
			mensagem.setEmailAutentica(emailAut);
			mensagem.setSenhaAutentica(senhaAut);
			mensagem.setSmtp(smtpserver);
			mensagem.setPorta(portasmtp);
			mensagem.setAssunto("Chamado número: " + numero + ",  aberto no Service Desk");
			mensagem.setDescricao(
					"Título: " + titulo + ", Descrição do chamado: " + descricao + " - " + emailAutomatico);
			mensagem.setEmaildestino(destinatario);
			mensagem.EnviarEmail();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Chamado Número: " + numero + " aberto com sucesso"));
			retorno = "chamados";

		} catch (Exception ex) {

		}
		return retorno;

	}

	public Collection<SelectItem> getCarregarUsuarios() {
		UsuarioDAO dao = new UsuarioDAO();

		Collection<SelectItem> lista = new ArrayList<SelectItem>();

		lista.add(new SelectItem("-- Selecione o usuario --"));

		List<Usuario> listaUsuario = dao.GetALL();

		for (int i = 0; i < listaUsuario.size(); i++) {
			lista.add(new SelectItem(listaUsuario.get(i).getId(), listaUsuario.get(i).getNome()));
		}

		return lista;
	}

	public Collection<SelectItem> getCarregarTecnicos() {

		TecnicoDAO dao = new TecnicoDAO();

		Collection<SelectItem> lista = new ArrayList<SelectItem>();

		lista.add(new SelectItem("-- Selecione o Tecnico --"));

		List<Tecnico> listaTecnico = dao.getAll();

		for (int i = 0; i < listaTecnico.size(); i++) {
			lista.add(new SelectItem(listaTecnico.get(i).getId(), listaTecnico.get(i).getNome()));
		}

		return lista;
	}

	public String deleteChamado() {

		this.chamado = chamados.getRowData();

		String retorno = "erro";

		try {

			ChamadoDAO dao = new ChamadoDAO();

			dao.delete(chamado);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chamado deletado com sucesso"));
			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public String updateChamado() {
		String retorno = "erro";

		try {

			ChamadoDAO dao = new ChamadoDAO();

			EmailConfigDAO emailConfigDao = new EmailConfigDAO();

			dao.update(chamado);

			String statusChamado = chamado.getStatus();

			int numero = chamado.getId();

			String titulo = chamado.getTitulo();
			String solucao = chamado.getSolucao();
			String destinatario = chamado.getUsuario().getEmail();
			String emailAut = emailConfigDao.get(1).getEmail();
			String senhaAut = emailConfigDao.get(1).getSenha();
			String smtpserver = emailConfigDao.get(1).getSmtp();
			int portasmtp = emailConfigDao.get(1).getPorta();
			String pesquisa = ("http://hartwig.net.br:8080/SGSD/pesquisa.xhtml");
			String emailAutomatico = "Este é um e-mail automático enviado pelo Sistema de Service Desk - SGSD";

			if (statusChamado.equals("Concluido")) {
				Mensagem mensagem = new Mensagem();
				mensagem.setEmailAutentica(emailAut);
				mensagem.setSenhaAutentica(senhaAut);
				mensagem.setSmtp(smtpserver);
				mensagem.setPorta(portasmtp);
				mensagem.setAssunto("Chamado número: " + numero + ",  foi encerrado - Service Desk");
				mensagem.setDescricao(
						"Olá! Informamos que o chamado número: " + numero + ", foi encerrado. Solução do Tecnico: "
								+ solucao + " - " + "Por favor preencha a pesquisa de satisfação em: " + pesquisa
								+ "  Muito Obrigado! " + emailAutomatico);
				mensagem.setEmaildestino(destinatario);
				mensagem.EnviarEmail();

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Chamado número " + numero + ", encerrado com sucesso!"));

			} else {
				if (statusChamado.equals("Em Andamento")) {

					Mensagem mensagem = new Mensagem();
					mensagem.setEmailAutentica(emailAut);
					mensagem.setSenhaAutentica(senhaAut);
					mensagem.setSmtp(smtpserver);
					mensagem.setPorta(portasmtp);
					mensagem.setAssunto("Chamado número: " + numero + ",  está em andamento");
					mensagem.setDescricao("Olá! Informamos que o chamado número: " + numero + "," + titulo
							+ ", está em atendimento pela equipe do Service Desk! " + emailAutomatico);
					mensagem.setEmaildestino(destinatario);
					mensagem.EnviarEmail();

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Chamado número " + numero + ", está em andamento"));
					retorno = "listar";
				}
			}

		} catch (Exception ex) {

		}
		return retorno;
	}

	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {

		Document pdf = (Document) document;

		pdf.setMargins(5f, 5f, 5f, 5f);
		pdf.setPageSize(PageSize.LETTER);
		pdf.addTitle("Relatório de Chamados");
		pdf.open();

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String logo = servletContext.getRealPath("") + File.separator + "resources/img" + File.separator
				+ "LogoSGSD.png";

		Image image = Image.getInstance(logo);

		image.setAlignment(Image.ALIGN_CENTER);

		pdf.add(image);

		Paragraph p = new Paragraph("Relatório de Chamados");
		p.setAlignment("center");
		pdf.add(p);
	}

}
