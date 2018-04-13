package com.castlebreck.dracma;

import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.log4j.Logger;

class Blockchain{
	private static Logger logger = Logger.getLogger(Blockchain.class);
	
	private ArrayList<Block> chain = new ArrayList<Block>();
	
	public Blockchain() throws NoSuchAlgorithmException {
		chain.add(this.createGenesis());
    }

	public Block createGenesis() throws NoSuchAlgorithmException {
        Block block = new Block(
    		new Timestamp(Calendar.getInstance().getTimeInMillis()),
    		(new RandomString(64)).nextString()
        );
        
        logger.debug(String.format("Genesis block created on '%s'. Hash = '%s', data='%s'",block.getTimestamp().toString(),  block.getHash(), block.data));
        return block;
    }

    public Block latestBlock() {
        return chain.get(chain.size()-1);
    }

    public void addBlock(Block block) throws NoSuchAlgorithmException{
    	block.previousHash = this.latestBlock().hash;
    	block.hash = block.calculateHash();
        this.chain.add(block);
    }

    public boolean checkValid() throws NoSuchAlgorithmException {
        for(int i = 1; i < this.chain.size(); i++) {
        	Block currentBlock = this.chain.get(i);
        	Block previousBlock = this.chain.get(i - 1);

            if (currentBlock.hash.compareTo(currentBlock.calculateHash()) != 0) {
                return false;
            }

            if (currentBlock.previousHash.compareTo(previousBlock.hash) != 0) {
                return false;
            }
        }

        return true;
    }
}