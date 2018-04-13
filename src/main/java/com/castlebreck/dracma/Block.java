package com.castlebreck.dracma;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;

public class Block implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8809541075979426222L;
	public static final String ZERO = "0";
	int index;
	Timestamp timestamp;
	String data;
	int nonce;
	String hash;
	String previousHash;

	public Block(Timestamp timestamp, String data) throws NoSuchAlgorithmException {
        this.index = 0;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = ZERO;
        this.hash = this.calculateHash();
        this.nonce = 0;
	}

    public String calculateHash() throws NoSuchAlgorithmException {
    	MessageDigest digester = MessageDigest.getInstance("SHA");
    	digester.update(
    		String.format("%d%s%s%d", this.index, this.timestamp.toString(), this.data, this.nonce).getBytes()
    	);
    	return Base64.getEncoder().encodeToString(digester.digest());
    }

	public int getIndex() {
		return index;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getData() {
		return data;
	}

	public int getNonce() {
		return nonce;
	}

	public String getHash() {
		return hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	

   
    
}
