package com.ybl.sign.api.util;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;


/**
 * Class RSAUtil
 * RSA工具类
 *
 * @author Selwyn
 * Date: Jul 21, 2015 9:30:27 AM
 */
public class RSAUtil {
	private  static final String KEY_ALGORTHM = "RSA";
	
	public static final String PUBLIC_KEY = "RSAPublicKey";
	
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	
	private static final String DEF_ENCODING = "US-ASCII";
	/***
	 * 
	 * @return
	 */
	private static KeyPair generateKeyPair()
	{
		KeyPairGenerator generator = null;
		KeyPair keyPair = null;
		while (keyPair == null)
		{
			try {
				generator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
				keyPair = generator.generateKeyPair();
			} catch (Exception e) {
				continue;
				//do nothing
			}
		}
		return keyPair;
	}
	
	public static Map<String,Object> generateKey()
	{
		Map<String,Object> keyMap = new HashMap<String, Object>();
		
		KeyPair keyPair = generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		byte[] priKeyBytes = Base64.encodeBase64(privateKey.getEncoded());
		byte[] pubKeyBytes = Base64.encodeBase64(publicKey.getEncoded());
		
		String strPrivateKey = null, strPublicKey = null;
		try {
			strPrivateKey = new String(priKeyBytes,DEF_ENCODING);
			strPublicKey = new String(pubKeyBytes,DEF_ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		keyMap.put(PRIVATE_KEY, strPrivateKey);
		keyMap.put(PUBLIC_KEY, strPublicKey);	
		
		return keyMap;
	}
}
