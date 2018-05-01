package com.nemesys.dracma;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Block implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7102206996257677489L;
	/**
	 * 
	 */
	
	private String hash;
	private String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.


	public Block(String data, long timeStamp, String previousHash ) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = timeStamp;
		this.hash = this.calculateHash();
	}
	
	public Block(String data, long timeStamp, Block previousBlock ) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this(data, timeStamp, previousBlock.hash);
	}

	public String getHash() {
		return hash;
	}

	public String getData() {
		return data;
	}	
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public String getPreviousHash() {
		return previousHash;
	}
	
	public String calculateHash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String calculatedhash = Strings.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				data 
				);
		return calculatedhash;
	}
	
}