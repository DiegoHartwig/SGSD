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


import br.net.hartwig.dao.SetorDAO;
import br.net.hartwig.dao.UsuarioDAO;
import br.net.hartwig.model.Setor;
import br.net.hartwig.model.Usuario;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 8627171377682391486L;

	private Usuario usuario = new Usuario();

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private DataModel<Usuario> usuarios;
	
	//Variável para armazenar o usuario selecionado no datatable
	private Usuario usuarioSelecionado;

	private int setor_id;

	public int getSetor_id() {
		return setor_id;
	}

	public void setSetor_id(int setor_id) {
		this.setor_id = setor_id;
	}

	public void setUsuarios(DataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void selecionarUsuario() {
		this.usuario = usuarios.getRowData();
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}


	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public DataModel<Usuario> getUsuarios() {
		UsuarioDAO dao = new UsuarioDAO();

		try {
			List<Usuario> lista = dao.GetALL();
			usuarios = new ListDataModel<Usuario>(lista);
		} catch (Exception e) {

		}

		return usuarios;
	}

	// Novo Usuario
	public void novoUsuario() {
		usuario = new Usuario();
	}

	// Add Usuario
	public void addUsuario() {		

		try {
			UsuarioDAO dao = new UsuarioDAO();

			SetorDAO setorDao = new SetorDAO();
			
			usuario.setSetor(setorDao.Get(setor_id));

			dao.Salvar(usuario);
			
			String nome = usuario.getNome();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario: "+nome+", cadastrado com sucesso"));
			
		} catch (Exception ex) {
			
		}
		
	}

	// delete
	public void deleteUsuario() {
		this.usuario = usuarios.getRowData();

		try {
			UsuarioDAO dao = new UsuarioDAO();
			
			dao.Delete(usuario);			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario excluído com sucesso"));
			
		} catch (Exception ex) {

		}		

	}

	public String updateUsuario() {

		String retorno = "erro";

		try {
			UsuarioDAO dao = new UsuarioDAO();
			
			SetorDAO setorDao = new SetorDAO();
			
			usuario.setSetor(setorDao.Get(setor_id));
			
			dao.Update(usuario);
			
			String nome = usuario.getNome();

			// Mensagem de confirmação após atualizar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados do usuário: "+nome+", atualizados com sucesso"));

			retorno = "usuarios";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public Collection<SelectItem> getCarregarSetores() {
		SetorDAO dao = new SetorDAO();
		Collection<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("-- Selecione o Setor --"));
		List<Setor> listaSetor = dao.GetALL();

		for (int i = 0; i < listaSetor.size(); i++) {
			lista.add(new SelectItem(listaSetor.get(i).getId(), listaSetor.get(i).getDescricao()));
		}

		return lista;
	}
	

}
