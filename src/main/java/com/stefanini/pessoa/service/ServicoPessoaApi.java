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
	
	private static String getConection(String url) throws MalformedURLException, IOException {					
		
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");	
		if (conn.getResponseCode() != 200) {
			System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output = "";
		String line;
		while ((line = br.readLine()) != null) {
			output += line.replace("fipe_name", "fipename");
		}
		conn.disconnect();
		return output;		
	}
	
	public static List<Pessoa> listaPessoas() {
		List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();
		try {
			//String url = "http://localhost:8090/pessoas";
			String url = "https://mmss20200712.herokuapp.com/pessoas";
			  
			String output = getConection(url);			
			Gson gson = new  GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
			Type collectionType = new TypeToken<List<Pessoa>>() {}.getType();
			listaDePessoas = gson.fromJson(output, collectionType);

		} catch (IOException ex) {
			ex.fillInStackTrace();
		} finally {
		}
		return listaDePessoas;
	}
	
	public static void main(String[] args) {
		List<Pessoa> lista  = listaPessoas();
		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			Pessoa pessoa = (Pessoa) iterator.next();
			System.out.println(pessoa.getNome());
			
		}
	}
	
	
//	public List<Pessoa> listaPessoas(){
//		List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();		
//		try {
//			String url = "http://fipeapi.appspot.com/api/1/carros/marcas.json";
//			String output = getConection(url);
//			Gson gson = new Gson();
//			Type collectionType = new TypeToken<List<Pessoa>>() {}.getType();
//		    listaDePessoas = gson.fromJson(output, collectionType);
//		    		   
//		} catch (IOException ex) {
//			ex.fillInStackTrace();
//		}		
//		return listaDePessoas;		
//	}
//	
//	public List<Modelo> listaModelos(String modelo){
//		List<Modelo> listaDeFabricantes = new ArrayList<Modelo>();		
//		try {
//			String url = "http://fipeapi.appspot.com/api/1/carros/veiculos/"+modelo+".json";
//			String output = getConection(url);
//			Gson gson = new Gson();
//			Type collectionType = new TypeToken<List<Modelo>>() {}.getType();
//		    listaDeFabricantes = gson.fromJson(output, collectionType);
//		    		   
//		} catch (IOException ex) {
//			ex.fillInStackTrace();
//		}		
//		return listaDeFabricantes;		
//	}
	
}
