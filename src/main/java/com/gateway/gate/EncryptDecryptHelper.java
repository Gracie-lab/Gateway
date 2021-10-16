package com.gateway.gate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import wiremock.net.minidev.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;


@Slf4j
public class EncryptDecryptHelper {

	private static final String SECRET_KEY = "Thisisatestkeyfortesting";


	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static String encrypt(String strToEncrypt){
		return encrypt(strToEncrypt, SECRET_KEY);
	}

	public static String decrypt(String strToDecrypt){
		return decrypt(strToDecrypt, SECRET_KEY);
	}

	public static void setKey(String myKey)
	{
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	public static String encrypt(String payload, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(payload.getBytes("UTF-8")));
		}
		catch (Exception e)
		{
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}



	public static String decrypt(String strToDecrypt, String secret)
	{
		try
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		}
		catch (Exception e)
		{
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static void main(String[] args) {

		String key = "Thisisatestkeyfortesting";

		//language=JSON
		String data = "{\n"
				+ "  \"name\": \"TestTitle1\",\n"
				+ "  \"age\": \"78\"\n"
				+ "}";

		System.out.println("Original String: " + data);

		String encryptedString = EncryptDecryptHelper.encrypt(data, key);

		System.out.println("Encrypted String: " + encryptedString);

		String decryptedString = EncryptDecryptHelper.decrypt(encryptedString, key);

		System.out.println("Decrypted String: " + decryptedString);

		String response = EncryptDecryptHelper.decrypt("xwTmmBs8P2hsGLUe/7h6CQ==", key);
		System.out.println("Decrypted response :" + response);

	}


	public static MultiValueMap<String, String> convertJsonToQueryParamMap(String json ) {

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = mapper.readTree(json);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

		while ( fields.hasNext() ){
			Map.Entry<String, JsonNode> entry = fields.next();
			multiValueMap.add(entry.getKey(), entry.getValue().asText());
		}

		return multiValueMap;
	}
}
