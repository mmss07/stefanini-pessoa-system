package com.stefanini.pessoa.repository;


import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.stefanini.pessoa.filter.UsuarioFilter;
import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);	
		
		if ((filtro.getEmail() != null)&&(filtro.getEmail() != "")) { 
			criteria.add(Restrictions.like("email", "%"+filtro.getEmail()+"%"));
		}
		
		if ((filtro.getNome() != null)&&(filtro.getNome() != "")) { 
			criteria.add(Restrictions.like("nome","%"+filtro.getNome()+"%"));
		}
		
		if ((filtro.getLogin() != null)&&(filtro.getLogin() != "")) { 
			criteria.add(Restrictions.like("login","%"+filtro.getLogin()+"%"));
		}

		return criteria.addOrder(Order.asc("nome")).list();
		
	}
	
	public Usuario porId(Long idusuario) {
		return this.manager.find(Usuario.class, idusuario);
	}

	public List<Usuario> usuarios() {		
		return this.manager.createQuery("from Usuario", Usuario.class).getResultList();
	}
	
	public List<Usuario> listaUsuarios() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}
	
	public Usuario porEmail(String email) {
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("SELECT x from Usuario x where lower(x.email) = :email", Usuario.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return usuario;
	}
	
	public Usuario porLoginESenha(String login, String senha) {
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("SELECT x from Usuario x where lower(x.login) = :login and lower(x.senha) = :senha", Usuario.class)
				.setParameter("login", login.toLowerCase()).setParameter("senha", senha.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return usuario;
	}
	
	public Long getNextCodigo() {
		Long codigo = new Long(0);
		try {
			codigo = this.manager.createQuery("SELECT MAX(id) from Usuario", Long.class).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return codigo+1;
	}
	
	@Transactional
	public Usuario salvar(Usuario usuario){		
		usuario = manager.merge(usuario);		
		return usuario;
	}
	
	@Transactional
	public void excluir(Usuario usuario) {		
		try {			
			usuario = porId(usuario.getIdusuario());
			manager.remove(usuario);
			manager.flush();
			
		} catch (PersistenceException e) {
			throw new PersistenceException("Usuário Não pode ser Excluída!");
		}
	}
	

}
