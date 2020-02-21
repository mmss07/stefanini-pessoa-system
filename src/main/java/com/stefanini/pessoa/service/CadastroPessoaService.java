package com.stefanini.pessoa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.stefanini.pessoa.model.Pessoa;

public class CadastroPessoaService implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private ServicoPessoaApi servicoFipeApi;
		
	
	public void Salvar(Pessoa pessoa) throws Exception{	
		pessoa.setId(nextId());
		servicoFipeApi.salvar(pessoa);			
	}
		
	public Pessoa alterar(Pessoa pessoa) {		
		servicoFipeApi.alterar(pessoa.getId());
		return pessoa;
	}
	
	public Long nextId(){				 
		return servicoFipeApi.nextId();
		
	}
	
	public Pessoa findByCpf(String cpf){
		Pessoa pessoa = new Pessoa();		
		try {
			pessoa = servicoFipeApi.findByCpf(cpf);
		    		   
		} catch (Exception ex) {
			ex.fillInStackTrace();
		}	
		if(pessoa.getId() == null) {
			return null;
		}
		return pessoa;		
	}
	

}
