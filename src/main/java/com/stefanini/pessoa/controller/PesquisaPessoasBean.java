package com.stefanini.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.pessoa.filter.PessoaFilter;
import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.service.PesquisaPessoaService;
import com.stefanini.pessoa.util.jsf.FacesUtil;




@Named
@ViewScoped
public class PesquisaPessoasBean implements Serializable{
	

	@Inject
	private PesquisaPessoaService cadastroPessoaService;
	
//	@Inject
//	private EnvioEmailBean envioEmailBean;
	
	private static final long serialVersionUID = 1L;
	private List<Pessoa> pessoasFiltrados;
	private Pessoa pessoa;
	private Pessoa pessoaselecionado;
	private PessoaFilter filtro;
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			filtro = new PessoaFilter();
			pesquisaPessoas();
		}
		
	}
	public List<Pessoa> pesquisa() {				
		pessoasFiltrados.clear();
		pessoasFiltrados = cadastroPessoaService.listaPessoas();
		
		//materiasFiltradas.add(materia);
		return pessoasFiltrados;
	}
	public List<Pessoa> pesquisaPessoas() {
		pessoasFiltrados = cadastroPessoaService.listaPessoas();
		return pessoasFiltrados;
	}

	public List<Pessoa> getPessoasFiltrados() {
		return pessoasFiltrados;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PessoaFilter filtro) {
		this.filtro = filtro;
	}
	public Pessoa getPessoaselecionado() {
		return pessoaselecionado;
	}
	public void setPessoaselecionado(Pessoa pessoaselecionado) {
		this.pessoaselecionado = pessoaselecionado;
	}
	public void setPessoasFiltrados(List<Pessoa> pessoasFiltrados) {
		this.pessoasFiltrados = pessoasFiltrados;
	}
	public void excluir() {
		
		pessoasFiltrados = cadastroPessoaService.excluir(pessoaselecionado, pessoasFiltrados);							
		FacesUtil.addInfoMessage("Pessoa " + pessoaselecionado.getNome() + " excluído com sucesso.");
	}
	
	
	
}