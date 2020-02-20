package com.stefanini.pessoa.security;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha2 implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	public String criptografiaSha2(String campo) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String text = campo;

		md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		byte[] digest = md.digest();
		
				
		return String.format("%064x", new java.math.BigInteger(1, digest));
		
		
		
	}
	
	
	

}
