package com.stefanini.pessoa.controller;

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
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	

	private Usuario usuario;
	

	@Inject 
	private CadastroUsuarioService cadastroUsuarioService;
	
	private void currentExternalContext(Usuario usuarioAux){
	       if (FacesContext.getCurrentInstance() == null){
	           throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
	       }else{
	    	   ExternalContext currentExternalContext = FacesContext.getCurrentInstance().getExternalContext();
	    	   currentExternalContext.getSessionMap().put("usuario", usuarioAux);
	       }
	    }
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
			System.out.println("Inicializou!");
		}		
	}
	
	public String validaLogin() {
	    String retorno = "";   
		Sha2 sha2 = new Sha2(); 
		try {
			usuario.setSenha(sha2.criptografiaSha2(usuario.getSenha()));
			
			Usuario usuarioAux = cadastroUsuarioService.porLoginESenha(usuario.getLogin(), usuario.getSenha());
			
			if(usuarioAux != null) {
				currentExternalContext(usuarioAux);
				retorno = "/Home.xhtml?faces-redirect=true";
			}else {				
				FacesUtil.addErrorMessage("Usuário não encontrado!");
			}
		} catch (NoSuchAlgorithmException e) {
			FacesUtil.addErrorMessage("ERRO ao logar o usuário!");
		} catch (UnsupportedEncodingException e) {
			FacesUtil.addErrorMessage("ERRO ao logar o usuário!");
		}				
	   return retorno;
	       
	  
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public static void main(String[] args) {
		Sha2 sha2 = new Sha2(); 
		
			try {
				System.out.println(sha2.criptografiaSha2("**basic**"));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
