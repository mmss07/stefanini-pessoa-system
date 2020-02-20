package com.stefanini.pessoa.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.security.Sha2;
import com.stefanini.pessoa.service.CadastroPessoaService;
import com.stefanini.pessoa.util.StringUtil;
import com.stefanini.pessoa.util.jsf.FacesUtil;


@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Inject 
	private CadastroPessoaService cadastroPessoaService;

	//@Inject
	//private SendEmail sendEmail;
		
	private Pessoa pessoa;
		
	public CadastroPessoaBean(){
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
					pessoa = new Pessoa();
					System.out.println("Inicializou!");	
		}		
	}

	
	
	public void salvar() throws NoSuchAlgorithmException, UnsupportedEncodingException{		
		try {	
			if(pessoa != null && pessoa.getCpf() != null) {
				if(StringUtil.isCPF(pessoa.getCpf())) {
					
					cadastroPessoaService.Salvar(pessoa);
				}else {
					FacesUtil.addErrorMessage("Cpf inválido a pessoa!");
				}
				
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

