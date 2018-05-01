package com.nemesys.dracma;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Strings {
	//Applies Sha256 to a string and returns the result. 
	public static String applySha256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
		//Applies sha256 to our input, 
		byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
		return Base64.getEncoder().encodeToString(hash);
	}

}
