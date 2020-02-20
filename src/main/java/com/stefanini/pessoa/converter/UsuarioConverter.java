package com.stefanini.pessoa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.repository.Usuarios;
import com.stefanini.pessoa.util.cdi.CDIServiceLocator;



@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {
	
	//@Inject
		private Usuarios usuarios;
		
		public UsuarioConverter() {
			usuarios = CDIServiceLocator.getBean(Usuarios.class);
		}
		

		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Usuario retorno = null;
			
			if (value != null) {
				Long id = new Long(value);
				retorno = usuarios.porId(id);
			}
			
			return retorno;
		}


		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				Usuario usuario = (Usuario) value;
				return usuario.getIdusuario() == null ? null : usuario.getIdusuario().toString();
			}
			
			return "";
		}

}
