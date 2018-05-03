package com.nemesys.dracma;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Strings {
	
	public static String applySha256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
		byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
		return Base64.getEncoder().encodeToString(hash);
	}

	public static byte[] randomBytes() throws NoSuchAlgorithmException {
		final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		final byte[] bytes = new byte[1024/8];
		secureRandom.nextBytes(bytes);
		return bytes;
	}
	
	public static String randomString() throws NoSuchAlgorithmException {
		return Base64.getEncoder().encodeToString(randomBytes());
	}
}
