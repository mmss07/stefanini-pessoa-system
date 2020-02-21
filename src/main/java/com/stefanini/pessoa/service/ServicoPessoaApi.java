package com.stefanini.pessoa.service;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stefanini.pessoa.model.Pessoa;
import com.stefanini.pessoa.util.StringUtil;
import com.stefanini.pessoa.util.adapter.GsonUTCDateAdapter;

public class ServicoPessoaApi implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String getConection(String url, String methodo) throws MalformedURLException, IOException {					
		
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		if(methodo.equalsIgnoreCase("GET")){
			conn.setRequestMethod("GET");			
		}else if(methodo.equalsIgnoreCase("POST")){
			conn.setRequestMethod("POST");
		}
		conn.setRequestProperty("Accept", "application/json");	
		if (conn.getResponseCode() != 200) {
			System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output = "";
		String line;
		while ((line = br.readLine()) != null) {
			output += line;
		}
		conn.disconnect();
		return output;		
	}
	
	public List<Pessoa> listaPessoas() {
		List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();
		try {
			//String url = "http://localhost:8090/pessoas";
			String url = "https://mmss20200712.herokuapp.com/pessoas";
			  
			String output = getConection(url,"GET");			
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
			Type collectionType = new TypeToken<List<Pessoa>>() {}.getType();
			listaDePessoas = gson.fromJson(output, collectionType);

		} catch (IOException ex) {
			ex.fillInStackTrace();
		} finally {
		}
		return listaDePessoas;
	}
	
	public Long nextId() {
		Pessoa pessoa = new Pessoa();
		try {
			//String url = "http://localhost:8090/pessoas";
			String url = "https://mmss20200712.herokuapp.com/pessoas/nextId";
			  
			String output = getConection(url,"GET");			
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
			pessoa = gson.fromJson(output, Pessoa.class);

		} catch (IOException ex) {
			ex.fillInStackTrace();
		} finally {
		}
		return pessoa.getId();
	}
	
	public Pessoa salvar(Pessoa pessoa) throws Exception {
		
		try {
			//String url = "http://localhost:8090/pessoas";
			String url = "https://mmss20200712.herokuapp.com/pessoas";			  						
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();			
			String pessoaJson = gson.toJson(pessoa);
			sendPost(url, pessoaJson);
		} catch (IOException ex) {
			ex.fillInStackTrace();
		} finally {
		}
		return pessoa;
	}
	
	public Pessoa findByCpf(String cpf){
		Pessoa pessoa = new Pessoa();		
		try {
			String url = "https://mmss20200712.herokuapp.com/pessoas/cpf/"+cpf;
			String output = getConection(url,"GET");
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
			Type retorno = new TypeToken<Pessoa>() {}.getType();
		    pessoa = gson.fromJson(output, retorno);		    		  
		} catch (IOException ex) {
			ex.fillInStackTrace();
		}				
		return pessoa;		
	}
	
	public Pessoa findById(Long id){
		Pessoa pessoa = new Pessoa();		
		try {
			String url = "https://mmss20200712.herokuapp.com/pessoas/"+id;
			String output = getConection(url,"GET");
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
			Type retorno = new TypeToken<Pessoa>() {}.getType();
		    pessoa = gson.fromJson(output, retorno);
		    		   
		} catch (IOException ex) {
			ex.fillInStackTrace();
		}		
		return pessoa;		
	}
	
	public Pessoa delete(Long id){
		Pessoa pessoa = new Pessoa();		
		try {
			String url = "https://mmss20200712.herokuapp.com/pessoas/"+id;
			String output = getConection(url,"DELETE");			
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();	
			Type retorno = new TypeToken<Pessoa>() {}.getType();
		    pessoa = gson.fromJson(output, retorno);
		    		   
		} catch (IOException ex) {
			ex.fillInStackTrace();
		}		
		return pessoa;		
	}
	
	public Pessoa alterar(Long id){
		Pessoa pessoa = new Pessoa();		
		try {
			String url = "https://mmss20200712.herokuapp.com/pessoas/"+id;
			String output = getConection(url,"PUT");			
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();	
			Type retorno = new TypeToken<Pessoa>() {}.getType();
		    pessoa = gson.fromJson(output, retorno);
		    		   
		} catch (IOException ex) {
			ex.fillInStackTrace();
		}		
		return pessoa;		
	}
	
	public String sendPost(String url, String json) throws Exception {

	    try {	        
	        HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
	        try {
	            
	            request.setDoOutput(true);
	            request.setDoInput(true);	            
	            request.setRequestProperty("Content-Type", "application/json");
	            request.setRequestMethod("POST");
	            request.connect();	    
	            
	            try (OutputStream outputStream = request.getOutputStream()) {
	                outputStream.write(json.getBytes("UTF-8"));
	            }
	          
	            return readResponse(request);
	        } finally {
	            request.disconnect();
	        }
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return json;
	}

	private String readResponse(HttpURLConnection request) throws IOException {
		ByteArrayOutputStream os;
	    try (InputStream is = request.getInputStream()) {
	        os = new ByteArrayOutputStream();
	        int b;
	        while ((b = is.read()) != -1) {
	            os.write(b);
	        }
	    }
	    return new String(os.toByteArray());
	}	
	
	public static void main(String[] args) {
		
//			Pessoa pessoa = new Pessoa();
//			pessoa.setCpf("63606044003");
//			//pessoa.setDataatualizacao("1581908400000");
//			pessoa.setEmail("raquel@hotmail.com");
//			pessoa.setId(new Long(2));
//			pessoa.setNacionalidade("Americana");
//			pessoa.setNaturalidade("Californiana");
//			pessoa.setNome("Raquel Bormann");
//			System.out.println(pessoa.getNome());
//			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
//			String json = gson.toJson(pessoa);
//			sendPost("https://mmss20200712.herokuapp.com/pessoas", json);
		
	}
	
}
