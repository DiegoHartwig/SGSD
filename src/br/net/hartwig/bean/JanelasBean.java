package br.net.hartwig.bean;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 * @author Diego Hartwig
 * @since 1.0.2017
 * @version 1.2.2017
 */
@ManagedBean(name = "janelaBean")
@RequestScoped
public class JanelasBean {

	public void abrirNovoChamado() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 500);
		opcoes.put("contentWidth", 500);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("novo_chamado", opcoes, null);
	}

	public void abrirAtualizarChamado() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 600);
		opcoes.put("contentWidth", 600);
		RequestContext.getCurrentInstance().openDialog("atualizar_chamado", opcoes, null);
	}

}
