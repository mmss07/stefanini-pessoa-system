package com.stefanini.pessoa.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stefanini.pessoa.model.Pessoa;
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
			String url = "https://mmss20200712.herokuapp.com/pessoas/nextid";
			  
			String output = getConection(url,"GET");			
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
			pessoa = gson.fromJson(output, Pessoa.class);

		} catch (IOException ex) {
			ex.fillInStackTrace();
		} finally {
		}
		return pessoa.getId() + 1;
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		
		try {
			//String url = "http://localhost:8090/pessoas";
			String url = "https://mmss20200712.herokuapp.com/pessoas";
			  
			String output = getConection(url,"POST");			
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
			
			pessoa = gson.fromJson(output, Pessoa.class);

		} catch (IOException ex) {
			ex.fillInStackTrace();
		} finally {
		}
		return pessoa;
	}
	
	
	public static void main(String[] args) {
//		List<Pessoa> lista  = listaPessoas();
//		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
//			Pessoa pessoa = (Pessoa) iterator.next();
//			System.out.println(pessoa.getNome());
//			
//		}
	}
	
	public List<Pessoa> findByCpf(String cpf){
		List<Pessoa> listaDeFabricantes = new ArrayList<Pessoa>();		
		try {
			String url = "https://mmss20200712.herokuapp.com/pessoas/cpf/"+cpf;
			String output = getConection(url,"GET");
			Gson gson = new Gson();
			Type collectionType = new TypeToken<List<Pessoa>>() {}.getType();
		    listaDeFabricantes = gson.fromJson(output, collectionType);
		    		   
		} catch (IOException ex) {
			ex.fillInStackTrace();
		}		
		return listaDeFabricantes;		
	}
	
	public Pessoa findById(Long id){
		Pessoa pessoa = new Pessoa();		
		try {
			String url = "https://mmss20200712.herokuapp.com/pessoas/"+id;
			String output = getConection(url,"GET");
			Gson gson = new Gson();
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
			Gson gson = new Gson();
			Type retorno = new TypeToken<Pessoa>() {}.getType();
		    pessoa = gson.fromJson(output, retorno);
		    		   
		} catch (IOException ex) {
			ex.fillInStackTrace();
		}		
		return pessoa;		
	}
	

	
}
