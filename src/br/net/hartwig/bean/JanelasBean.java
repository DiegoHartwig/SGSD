package br.net.hartwig.bean;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "janelaBean")
@SessionScoped
public class JanelasBean {

	// Métodos para abrir janelas dialog

	// novo chamado
	public void abrirNovoChamado() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 500);
		opcoes.put("contentWidth", 500);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("novo_chamado", opcoes, null);
	}

	// editar chamado
	public void abrirAtualizarChamado() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 600);
		opcoes.put("contentWidth", 600);
		RequestContext.getCurrentInstance().openDialog("atualizar_chamado", opcoes, null);
	}

}
