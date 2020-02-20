package com.stefanini.pessoa.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.security.Sha2;
import com.stefanini.pessoa.service.CadastroUsuarioService;
import com.stefanini.pessoa.util.jsf.FacesUtil;


@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Inject 
	private CadastroUsuarioService cadastroUsuarioService;
	
	private Usuario usuario;
		
	public CadastroUsuarioBean(){
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
					validaSessao();
					usuario = new Usuario();
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
			e.printStackTrace();
		}
	}
	
	public void salvar() throws NoSuchAlgorithmException, UnsupportedEncodingException{		
		try {
			
			
			Sha2 sha2 = new Sha2(); 
			usuario.setSenha(sha2.criptografiaSha2(usuario.getSenha()));				
			cadastroUsuarioService.Salvar(usuario);
		
		}catch (Exception e) {
			FacesUtil.addErrorMessage("ERRO ao Salvar o usuário!");
		}
			
		limpar();
		
		FacesUtil.addInfoMessage("Usuário Salvo com sucesso!");
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	private void limpar(){
		usuario = new Usuario();
	
	}

		
	public boolean isEditando(){
		return this.usuario.getIdusuario() != null;
		
	}
		

}

