package com.stefanini.pessoa.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.util.jpa.Transactional;
import com.stefanini.pessoa.util.jsf.FacesUtil;

public class PesquisaPessoaService implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private ServicoPessoaApi servicoFipeApi;
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {								
					System.out.println("Inicializou!");	
		}		
	}
	
	public List<Pessoa> excluir(Pessoa pessoaselecionado, List<Pessoa> pessoasFiltrados) {
		servicoFipeApi.delete(pessoaselecionado.getId());
		pessoasFiltrados.remove(pessoaselecionado);
		return pessoasFiltrados;
	}	
	
	public Pessoa porManufacturer(String manufacturer) {		
		//return pessoas.porManufacturer(manufacturer);
		return new Pessoa();
	}
	
	public List<Pessoa> listaPessoas() {
		return servicoFipeApi.listaPessoas();
	}
	
	public Pessoa porId(Long idpessoa) {
		//return pessoas.porId(idpessoa);
		return new Pessoa();//
	}


}
