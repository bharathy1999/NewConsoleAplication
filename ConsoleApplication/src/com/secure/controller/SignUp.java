package com.secure.controller;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.secure.DAO.InsertNewProfile;
import com.secure.view.SecureView;

public class SignUp {
 SecureView secureView;
 InsertNewProfile insertNewProfile;
 
 public SignUp(SecureView secureView,InsertNewProfile insertNewProfile){
	 this.secureView=secureView;
	 this.insertNewProfile=insertNewProfile;
 }
 
public void create() {
		
	    secureView.userInteractions("Enter a Name: ");
		String name=secureView.getScanner().next();
		secureView.userInteractions("Enter a Strong Password: ");
		String password=secureView.getScanner().next();
		if(!isStrongPassword(password)){
			secureView.userInteractions("your password is weak please enter strong password ");
			return;
		}
		
		secureView.userInteractions("Enter a AccountNumber: ");
		long accountNumber=secureView.getScanner().nextLong();
		try {
		SecretKey secretkey=generateSecretKey();
		String accountNumberAfterEncryption=encrypt(accountNumber,secretkey);
        insertNewProfile.createProfile(name, hashing(password), accountNumberAfterEncryption,secretKeyToString(secretkey));
		secureView.userInteractions("Profile Created Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
private static String secretKeyToString(SecretKey secretKey) {
    byte[] keyBytes = secretKey.getEncoded();
    return Base64.getEncoder().encodeToString(keyBytes);
}

public String hashing(String input) {
	StringBuilder hexString = new StringBuilder();

	try {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = sha256.digest(input.getBytes());
		for (byte hashByte : hashBytes) {
			String hex = Integer.toHexString(0xff & hashByte);
			hexString.append(hex);
		}

	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	return hexString.toString();
}

private boolean isStrongPassword(String password) {
	boolean isCapital = false;
	boolean isSymbol = false;
	boolean isNumeric = false;

	for (char ch : password.toCharArray()) {

		if (Character.isUpperCase(ch)) {
			isCapital = true;
		}

		if (Character.isDigit(ch)) {
			isNumeric = true;
		}

		String symbolsString = "~`!@#$%^&*()+_-={}[];':\"/?><,.\\";

		if (symbolsString.indexOf(ch) != -1) {
			isSymbol = true;
		}

	}
	return isCapital && isSymbol && isNumeric;
}

private SecretKey generateSecretKey() throws Exception {
	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	keyGenerator.init(128);
	return keyGenerator.generateKey();
}

private String encrypt(long input, SecretKey secretKey) throws Exception {
	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(cipher.ENCRYPT_MODE, secretKey);
	byte[] longBytes = ByteBuffer.allocate(Long.BYTES).putLong(input).array();

	byte[] encryptedBytes = cipher.doFinal(longBytes);

	return Base64.getEncoder().encodeToString(encryptedBytes);
}

public long decrypt(String encryptedText, SecretKey secretKey) throws Exception {
	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(cipher.DECRYPT_MODE, secretKey);

	byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
	byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
	return ByteBuffer.wrap(decryptedBytes).getLong();
}

	
}
