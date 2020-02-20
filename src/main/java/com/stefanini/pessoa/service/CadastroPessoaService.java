package com.stefanini.pessoa.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.util.jpa.Transactional;

public class CadastroPessoaService implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private ServicoPessoaApi servicoFipeApi;
		
	
	public void Salvar(Pessoa pessoa) throws Exception{	
		//pessoa.setId(nextId());
		servicoFipeApi.salvar(pessoa);			
	}
		
	public Pessoa alterar(Pessoa pessoa) {		
		servicoFipeApi.alterar(pessoa.getId());
		return pessoa;
	}
	
	public Long nextId(){				 
		return servicoFipeApi.nextId();
		
	}
	
	public List<Pessoa> findByCpf(String cpf){
		List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();		
		try {
			listaDePessoas = servicoFipeApi.findByCpf(cpf);
		    		   
		} catch (Exception ex) {
			ex.fillInStackTrace();
		}		
		return listaDePessoas;		
	}
	

}
