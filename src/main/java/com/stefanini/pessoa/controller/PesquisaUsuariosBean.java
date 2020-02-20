package com.stefanini.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.pessoa.filter.UsuarioFilter;
import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.repository.Usuarios;
import com.stefanini.pessoa.service.CadastroUsuarioService;
import com.stefanini.pessoa.util.jsf.FacesUtil;




@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable{
	

	@Inject
	private Usuarios usuarios;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
//	@Inject
//	private EnvioEmailBean envioEmailBean;
	
	private static final long serialVersionUID = 1L;
	private List<Usuario> usuariosFiltrados;
	private Usuario usuario;
	private Usuario usuarioselecionado;
	private UsuarioFilter filtro;
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			filtro = new UsuarioFilter();
			pesquisaUsuarios();
		}
		
	}
	public List<Usuario> pesquisa() {		
		//Manufatura materia = materias.porDescricao(filtro.getDescricao());
		usuariosFiltrados.clear();
		usuariosFiltrados = usuarios.filtrados(filtro);
		
		//materiasFiltradas.add(materia);
		return usuariosFiltrados;
	}
	public List<Usuario> pesquisaUsuarios() {
		usuariosFiltrados = usuarios.listaUsuarios();
		return usuariosFiltrados;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UsuarioFilter filtro) {
		this.filtro = filtro;
	}
	public Usuario getUsuarioselecionado() {
		return usuarioselecionado;
	}
	public void setUsuarioselecionado(Usuario usuarioselecionado) {
		this.usuarioselecionado = usuarioselecionado;
	}
	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}
	public void excluir() {
		
		usuariosFiltrados = cadastroUsuarioService.excluir(usuarioselecionado, usuariosFiltrados);							
		FacesUtil.addInfoMessage("Usuário " + usuarioselecionado.getNome() + " excluído com sucesso.");
	}
	
	
	
}