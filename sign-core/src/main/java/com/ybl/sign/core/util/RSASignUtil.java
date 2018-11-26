package com.ybl.sign.core.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * Class RSASignUtil
 * 签名
 *
 * @author Selwyn
 * Date: Jul 21, 2015 10:04:04 AM
 */
public class RSASignUtil {
	private static final String DEF_ENCODING = 
			//"US-ASCII";
			"UTF-8";
	
	private static final String KEY_ALGORTHM = "RSA";
	
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	
	public static String sign(String params, String strPrivateKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException
	{
		byte[] priKeyBytes = Base64.decodeBase64(strPrivateKey.getBytes(DEF_ENCODING));
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(priKeyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Signature signature  = Signature.getInstance(SIGNATURE_ALGORITHM);
		
		signature.initSign(privateKey);
		signature.update(params.getBytes(DEF_ENCODING));
		
		byte[] signed = signature.sign();
		return new String(Base64.encodeBase64(signed), DEF_ENCODING);
	}
	/***
	 * 签名并进行转义
	 * @param params
	 * @param strPrivateKey
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static String signAndEscape(String params, String strPrivateKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException
	{
		String sign = sign(params, strPrivateKey);
		return URLEncoder.encode(sign, "utf-8");
	}
}
