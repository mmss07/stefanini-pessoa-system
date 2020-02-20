package com.stefanini.pessoa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.model.Usuario;
import com.stefanini.pessoa.service.ServicoPessoaApi;
import com.stefanini.pessoa.util.cdi.CDIServiceLocator;



@FacesConverter(forClass = Usuario.class)
public class PessoaConverter implements Converter {
	
	//@Inject
		private ServicoPessoaApi pessoas;
		
		public PessoaConverter() {
			pessoas = CDIServiceLocator.getBean(ServicoPessoaApi.class);
		}
		

		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Pessoa retorno = null;
			
			if (value != null) {
				Long id = new Long(value);
				retorno = pessoas.findById(id);
			}
			
			return retorno;
		}


		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				Pessoa pessoa = (Pessoa) value;
				return pessoa.getId() == null ? null : pessoa.getId().toString();
			}
			
			return "";
		}

}
