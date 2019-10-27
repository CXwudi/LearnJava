package com.cxwudi.playground;

import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptDecrypt {
	
	public static void main(String[] args) {
		logger.info("start");
		Cipher cipher = null;
		
		//encryption
		try {
			cipher = Cipher.getInstance("RC4");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			logger.error(e.toString());
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec("I love miku".getBytes(), "RC4"));
		} catch (InvalidKeyException e) {
			logger.error(e.toString());
		}
		
		var encrptedBytes = new byte[0];
		try {
			encrptedBytes = cipher.doFinal("my password".getBytes());
			logger.info("encrypting " + "my password");
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			logger.error(e.toString());
		}
		
		logger.info("encrypted string: {}, array: {}",new String(encrptedBytes), Arrays.toString(encrptedBytes));
		
		//decryption
//		try {
//			cipher = Cipher.getInstance("RC4");
//		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
//			logger.error(e.toString());
//		}
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec("I love miku".getBytes(), "RC4"));
		} catch (InvalidKeyException e) {
			logger.error(e.toString());
		}
		
		var output = new byte[0];
		try {
			output = cipher.doFinal(encrptedBytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			logger.error(e.toString());
		}
		
		logger.info("decryption: {}", new String(output));

		
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
}
