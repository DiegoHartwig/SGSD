package br.net.hartwig.bean;

import java.io.File;
import java.io.IOException;
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
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "chamadoBean")
@SessionScoped
public class ChamadoBean implements Serializable {

	private static final long serialVersionUID = -6667994166226498861L;
	// Instanciando objeto privado do tipo Chamado
	private Chamado chamado = new Chamado();
	//DataModel chamados do tipo Chamado
	private DataModel<Chamado> chamados;
	private int usuario_id;
	private int tecnico_id;
	//Vari�vel utilizada no DataTable para filtrar chamados
	private List<Chamado> chamadosFiltrados;
	//Vari�vel que armazena o chamado selecionado no dataTable
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
	
	//getChamados
	//M�todo que retorna uma lista no datamodel
	public DataModel<Chamado> getChamados() {
		//Instanciando o ChamadoDAO
		ChamadoDAO dao = new ChamadoDAO();

		try {
			//Recebendo lista de chamados do m�todo GetALL do ChamadoDAO
			List<Chamado> lista = dao.GetALL();
			//Setando datamodel chamados, vindo de lista
			chamados = new ListDataModel<Chamado>(lista);
		} catch (Exception e) {

		}
		//Retorna o datamodel chamados
		return chamados;
	}
	
	//setChamados
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

	// Novo Chamado
	public void novoChamado() {
		chamado = new Chamado();	
		
	}

	// M�todo Add chamado
	public String addChamado() {
		String retorno = "erro";
		int numero = 0;
		try {
			// Instanciando ChamadoDAO
			ChamadoDAO dao = new ChamadoDAO();
			// Instanciando TecnicoDAO
			TecnicoDAO tecnicodao = new TecnicoDAO();
			// Instanciando UsuarioDAO
			UsuarioDAO usuariodao = new UsuarioDAO();
			
			EmailConfigDAO emailConfigDao = new EmailConfigDAO();

			chamado.setTecnico(tecnicodao.Get(tecnico_id));

			chamado.setUsuario(usuariodao.Get(usuario_id));			
			
			// Salvar chamado
			dao.Salvar(chamado);
			
			numero = chamado.getId();	
			
			String titulo = chamado.getTitulo();
			String descricao = chamado.getDescricao();
			String destinatario = chamado.getUsuario().getEmail();
			//Pega os par�metros da tabela de configura��o de email e depois seta abaixo
			String emailAut = emailConfigDao.Get(1).getEmail();
			String senhaAut = emailConfigDao.Get(1).getSenha();
			String smtpserver = emailConfigDao.Get(1).getSmtp();			
			int portasmtp = emailConfigDao.Get(1).getPorta();
			String emailAutomatico = "Aten��o! Este � um email autom�tico enviado pelo Sistema do Service Desk! ";
			
			
			//Envio de email ap�s abertura do chamado
			Mensagem mensagem = new Mensagem();
			mensagem.setEmailAutentica(emailAut);
			mensagem.setSenhaAutentica(senhaAut);
			mensagem.setSmtp(smtpserver);
			mensagem.setPorta(portasmtp);
			mensagem.setAssunto("Chamado n�mero: "+numero+",  aberto no Service Desk");
			mensagem.setDescricao("T�tulo: "+titulo+", Descri��o do chamado: "+descricao+
					" - " +emailAutomatico);
			mensagem.setEmaildestino(destinatario);
			mensagem.EnviarEmail();			
			
			// Mensagem de confirma��o ap�s salvar o novo chamado
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chamado N�mero: "+numero+" aberto com sucesso"));
			retorno = "chamados";		
			

		} catch (Exception ex) {

		}
		return retorno;

	}

	// Carregar Lista de Usu�rios
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

	// Carregar lista de Tecnicos

	public Collection<SelectItem> getCarregarTecnicos() {
		TecnicoDAO dao = new TecnicoDAO();
		Collection<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("-- Selecione o Tecnico --"));
		List<Tecnico> listaTecnico = dao.GetALL();

		for (int i = 0; i < listaTecnico.size(); i++) {
			lista.add(new SelectItem(listaTecnico.get(i).getId(), listaTecnico.get(i).getNome()));
		}

		return lista;
	}

	// Delete
	public String deleteChamado() {
		//Seta chamado pegando registro atual
		this.chamado = chamados.getRowData();
		
		String retorno = "erro";

		try {
			// Instanciando ChamadoDAO
			ChamadoDAO dao = new ChamadoDAO();
			// Chamando m�todo Delete de ChamadoDAO
			dao.Delete(chamado);
			// Mensagem de confirma��o ap�s salvar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chamado deletado com sucesso"));
			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	// Update
	public String updateChamado() {
		String retorno = "erro";

		try {
			// Instanciando ChamadoDAO
			ChamadoDAO dao = new ChamadoDAO();
			// Instanciando TecnicoDAO
			//TecnicoDAO tecnicodao = new TecnicoDAO();
			// Instanciando UsuarioDAO
			//UsuarioDAO usuariodao = new UsuarioDAO();

			//chamado.setTecnico(tecnicodao.Get(tecnico_id));

			//chamado.setUsuario(usuariodao.Get(usuario_id));	
			// Chamando m�todo update de ChamadoDAO
			
			//Instanciando EmailDAO
			EmailConfigDAO emailConfigDao = new EmailConfigDAO();		
			
			dao.Update(chamado);			
			
			String statusChamado = chamado.getStatus();	
			
			int numero = chamado.getId();	
			
			//Setando parametros do email
			String titulo = chamado.getTitulo();
			String solucao = chamado.getSolucao();
			String destinatario = chamado.getUsuario().getEmail();
			//Pega os par�metros da tabela de configura��o de email e depois seta abaixo
			String emailAut = emailConfigDao.Get(1).getEmail();
			String senhaAut = emailConfigDao.Get(1).getSenha();
			String smtpserver = emailConfigDao.Get(1).getSmtp();
			int portasmtp = emailConfigDao.Get(1).getPorta();
			String pesquisa = ("http://hartwig.net.br:8080/SGSD/pesquisa.xhtml");
			String emailAutomatico = "Aten��o! Este � um email autom�tico enviado pelo Sistema do Service Desk!";
	
			
			//Se foi selecionada a op��o concluido ent�o
			if (statusChamado.equals("Concluido")){
				Mensagem mensagem = new Mensagem();
				mensagem.setEmailAutentica(emailAut);
				mensagem.setSenhaAutentica(senhaAut);
				mensagem.setSmtp(smtpserver);
				mensagem.setPorta(portasmtp);
				mensagem.setAssunto("Chamado n�mero: "+numero+",  foi encerrado - Service Desk");
				mensagem.setDescricao("Ol�! Informamos que o chamado n�mero: "+numero+", foi encerrado. Solu��o do t�cnico: "+solucao+" - "
						+ "Por favor preencha a pesquisa de satisfa��o em: "+pesquisa+"  Muito Obrigado! "+emailAutomatico);
				mensagem.setEmaildestino(destinatario);
				mensagem.EnviarEmail();	
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chamado n�mero "+numero+", encerrado com sucesso!"));
				

			}else{
				if(statusChamado.equals("Em Andamento")){
					
					Mensagem mensagem = new Mensagem();
					mensagem.setEmailAutentica(emailAut);
					mensagem.setSenhaAutentica(senhaAut);
					mensagem.setSmtp(smtpserver);
					mensagem.setPorta(portasmtp);
					mensagem.setAssunto("Chamado n�mero: "+numero+",  est� em andamento");
					mensagem.setDescricao("Ol�! Informamos que o chamado n�mero: "+numero+","+titulo+", est� em atendimento pela equipe do Service Desk! "
										+emailAutomatico);
					mensagem.setEmaildestino(destinatario);
					mensagem.EnviarEmail();		
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chamado n�mero "+numero+", est� em andamento"));
					retorno = "listar";	
				}
			}
			
			
		} catch (Exception ex) {

		}
		return retorno;
	}
	
	//Gerar PDF
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
	    //cria o documento
	    Document pdf = (Document) document;        
	    //seta a margin e p�gina, precisa estar antes da abertura do documento, ou seja da linha: pdf.open()
	    pdf.setMargins(5f, 5f, 5f, 5f);	    
	    pdf.setPageSize(PageSize.LETTER);
	    pdf.addTitle("Relat�rio de Chamados");
	    pdf.open();
	    //aqui pega o contexto para formar a url da imagem
	    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    String logo = servletContext.getRealPath("") + File.separator + "resources/img" + File.separator + "LogoSGSD.png";
	    //cria a imagem e passando a url
	    Image image = Image.getInstance(logo);
	    //alinha ao centro
	    image.setAlignment(Image.ALIGN_CENTER);
	    //adiciona a img ao pdf
	    pdf.add(image);
	    //adiciona um paragrafo ao pdf, alinha tamb�m ao centro
	    Paragraph p = new Paragraph("Relat�rio de Chamados");
	    p.setAlignment("center");
	    pdf.add(p);
	}
	
	
	
	

}
