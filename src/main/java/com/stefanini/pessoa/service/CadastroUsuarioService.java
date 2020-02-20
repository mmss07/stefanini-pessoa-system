package com.stefanini.pessoa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.repository.Usuarios;
import com.stefanini.pessoa.util.jpa.Transactional;
import com.stefanini.pessoa.util.jsf.FacesUtil;

public class CadastroUsuarioService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public void Salvar(Usuario usuario) throws Exception{
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());		
		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {			
			FacesUtil.addInfoMessage("Já existe um usuário com o E-mail informado.");
		}else{
			usuario.setIdusuario(getNextCodigo());
			usuarios.salvar(usuario);
		}

	}
		
	public Long getNextCodigo(){				
		 return usuarios.getNextCodigo();
	}
	
	public List<Usuario> excluir(Usuario usuarioselecionado, List<Usuario> usuariosFiltrados) {		
		usuarios.excluir(usuarioselecionado);
		usuariosFiltrados.remove(usuarioselecionado);		
		FacesUtil.addInfoMessage("Usuário " + usuarioselecionado.getNome() + " excluído com sucesso.");
		return usuariosFiltrados;
	}	
	
	public Usuario porEmail(String email) {				
		return usuarios.porEmail(email);
	}

	public Usuario porLoginESenha(String login, String senha) {
		return usuarios.porLoginESenha(login, senha);
	}
	
}
