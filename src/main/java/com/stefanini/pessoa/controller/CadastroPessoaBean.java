package com.stefanini.pessoa.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.service.CadastroPessoaService;
import com.stefanini.pessoa.util.StringUtil;
import com.stefanini.pessoa.util.jsf.FacesUtil;


@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Inject 
	private CadastroPessoaService cadastroPessoaService;
		
	private Pessoa pessoa;
		
	public CadastroPessoaBean(){
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
					validaSessao();
					pessoa = new Pessoa();
					System.out.println("Inicializou!");	
		}		
	}

	public void validaSessao() {
		try {
			ExternalContext currentExternalContext = FacesContext.getCurrentInstance().getExternalContext();
			Usuario usuario = (Usuario) currentExternalContext.getSessionMap().get("usuario");
			if(usuario == null) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("../Login.xhtml");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvar(){		
		try {	
			if(pessoa != null && pessoa.getCpf() != null) {
				pessoa.setId(new Long(2));
				//if(true) {					
					cadastroPessoaService.Salvar(pessoa);
				//}else {
					FacesUtil.addErrorMessage("Cpf inválido a pessoa!");
				//}				
			}		
		}catch (Exception e) {
			FacesUtil.addErrorMessage("ERRO ao Salvar a pessoa!");
		}
			
		limpar();
		
		FacesUtil.addInfoMessage("Pessoa Salva com sucesso!");
		
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa Pessoa) {
		this.pessoa = Pessoa;
	}

	
	private void limpar(){
		pessoa = new Pessoa();
	
	}

		
	public boolean isEditando(){
		return this.pessoa.getNome() != null;
		
	}
		

}

