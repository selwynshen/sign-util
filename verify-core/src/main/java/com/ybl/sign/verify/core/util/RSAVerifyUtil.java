package com.ybl.sign.verify.core.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Class RSAVerifyUtil
 * 验签
 *
 * @author Selwyn
 * Date: Jul 21, 2015 10:04:18 AM
 */
public class RSAVerifyUtil {
	private static final String DEF_ENCODING = 
			//"US-ASCII";
			"UTF-8";
	
	private static final String KEY_ALGORTHM = "RSA";
	
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	/***
	 * 
	 * @param params
	 * @param strPublicKey
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 */
	public static Boolean verify(String params, String sign, String strPublicKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException
	{
		byte[] pubKeyBytes = Base64.decodeBase64(strPublicKey.getBytes(DEF_ENCODING));
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubKeyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		PublicKey publicKey = (PublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
		Signature signature  = Signature.getInstance(SIGNATURE_ALGORITHM);
		
		signature.initVerify(publicKey);
		signature.update(params.getBytes(DEF_ENCODING));
		//先base64解码
		byte[] sigBytes = Base64.decodeBase64(sign.getBytes(DEF_ENCODING));
		
		return signature.verify(sigBytes);
	}
}
