package com.nemesys.dracma;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class BlockChainTest {
	
	private static Logger logger = Logger.getLogger(BlockTest.class);
	Block genesisBlock;
	Block secondBlock;
	Block thirdBlock;
	List<Block> blocks;
	
	class MyChain extends BlockChain {

		public MyChain(Mint mint) {
			super(mint);
		}

		@Override
		public Iterator<Block> getAllBlocks() {
			return BlockChainTest.this.blocks.iterator();
		}		
	}
	
	public BlockChainTest() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		genesisBlock = new Block(Strings.randomString(),(new Date()).getTime());
		secondBlock = new Block(Strings.randomString(), (new Date()).getTime(), genesisBlock);
		thirdBlock = new Block(Strings.randomString(), (new Date()).getTime(), secondBlock);		
		blocks = Arrays.asList(genesisBlock, secondBlock, thirdBlock);
	}
	
	@Test
	public void testBlockChainIsValid() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		BlockChain chain = new MyChain(null);
		
		Assert.assertEquals(chain.isValid(), true);
		logger.debug("testBlockChainIsValid");
	}
	
}
