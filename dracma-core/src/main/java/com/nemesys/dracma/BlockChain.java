package com.nemesys.dracma;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public abstract class BlockChain {
	private Mint mint;
	
	public BlockChain(Mint mint) {
		this.mint = mint;
	}
	public abstract Iterator<Block> getAllBlocks();
	 
	public boolean compareInternalHashes(Block block) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return block.getHash().equals(block.calculateHash());
	}
	 
	public boolean compareHashes(Block a, Block b) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return a.getHash().equals(b.getPreviousHash());
	}
	 
	public boolean isValid() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Iterator<Block> iterator = this.getAllBlocks();
		 
		if(iterator.hasNext()) {
		 boolean result = true;			 
		 Block prev = iterator.next();
				 
		 while(iterator.hasNext()) {
			 Block next = iterator.next();
					 
			 result = result && 
					 compareInternalHashes(prev) && 
					 compareHashes(prev, next) &&
					 (mint == null || mint.isValid(this));
					 
			 prev = next;
					 
			 if(!result)
				 return false;
		 }
		}
				 
		return true;
	}
	public Mint getMint() {
		return mint;
	}
	
	
}
