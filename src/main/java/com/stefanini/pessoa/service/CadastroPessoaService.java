package com.stefanini.pessoa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.util.jpa.Transactional;

public class CadastroPessoaService implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private ServicoPessoaApi servicoFipeApi;
		
	
	@Transactional
	public void Salvar(Pessoa pessoa) throws Exception{	
		pessoa.setId(getNextCodigo());
		//pessoas.salvar(pessoa);			
	}
		
	public Long getNextCodigo(){				 
		 //return pessoas.getNextCodigo();
		return new Long(0);
	}
	
	public List<Pessoa> excluir(Pessoa pessoaselecionado, List<Pessoa> pessoasFiltrados) {
		//pessoas.excluir(pessoaselecionado);
		pessoasFiltrados.remove(pessoaselecionado);
		return pessoasFiltrados;
	}	
	
	public Pessoa porManufacturer(String manufacturer) {		
		//return pessoas.porManufacturer(manufacturer);
		return new Pessoa();
	}
	
//	public List<Marca> listaMarcas(){
//		return servicoFipeApi.listaMarcas();
//	}
//	
//	public List<Modelo> listaModelos(String modelo){
//		return servicoFipeApi.listaModelos(modelo);
//	}
	
	public Pessoa porId(Long idpessoa) {
		//return pessoas.porId(idpessoa);
		return new Pessoa();//
	}


}
