package com.castlebreck.dracma;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Main 
{
	private static Logger logger = Logger.getLogger(Blockchain.class);
	
    @SuppressWarnings("unused")
	public static void main( String[] args )
    {
    	Blockchain chain;
		try {
			chain = new Blockchain();
			logger.debug(String.format("A new dracma block chain was just created"));
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}
    	
    }
}
