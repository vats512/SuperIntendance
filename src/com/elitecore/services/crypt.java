package com.elitecore.services;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;
public class crypt {
	 private static final byte[] keyValue = 
		        new byte[] { 'T','h','e','m','y','t','h','i','s','t','r','u','e','i','f','i'};
	 public static String encrypt(String Data) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance("AES");
	        c.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encVal = c.doFinal(Data.getBytes());
	        String encryptedValue = new BASE64Encoder().encode(encVal);
	        return encryptedValue;
	    }

	    public static String decrypt(String encryptedData) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance("AES");
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
	        byte[] decValue = c.doFinal(decordedValue);
	        String decryptedValue = new String(decValue);
	        return decryptedValue;
	    }
	    private static Key generateKey() throws Exception {
	        Key key = new SecretKeySpec(keyValue, "AES");
	        return key;
	}

	}

