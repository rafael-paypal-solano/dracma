package com.nemesys.dracma;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import org.apache.log4j.*;

import org.junit.Assert;
import org.junit.Test;


public class BlockTest {
	private static Logger logger = Logger.getLogger(BlockTest.class);
			
	@Test
	public void testHashChaining() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Block genesisBlock = new Block(Strings.randomString(),(new Date()).getTime());
		Block secondBlock = new Block(Strings.randomString(), (new Date()).getTime(), genesisBlock);
		Block thirdBlock = new Block(Strings.randomString(), (new Date()).getTime(), secondBlock);
		
		Assert.assertEquals(genesisBlock.getHash(), secondBlock.getPreviousHash());
		Assert.assertEquals(secondBlock.getHash(), thirdBlock.getPreviousHash());
		
		logger.debug(String.format("%s", genesisBlock.getHash()));
		logger.debug(String.format("%s", secondBlock.getHash()));
		logger.debug(String.format("%s", thirdBlock.getHash()));
	}
}	
