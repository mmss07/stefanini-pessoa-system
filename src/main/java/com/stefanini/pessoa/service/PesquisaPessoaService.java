package com.stefanini.pessoa.service;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.util.jpa.Transactional;

public class PesquisaPessoaService implements Serializable{
	
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
	
	
	public List<Pessoa> listaPessoas() {
		return servicoFipeApi.listaPessoas();
	}
	
	public Pessoa porId(Long idpessoa) {
		//return pessoas.porId(idpessoa);
		return new Pessoa();//
	}


}
