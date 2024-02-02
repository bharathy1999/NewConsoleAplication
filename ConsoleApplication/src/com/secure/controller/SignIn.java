package com.secure.controller;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.secure.DAO.RetrieveProfile;
import com.secure.model.AccountProfile;
import com.secure.view.SecureView;

public class SignIn {
	static int loginattempts=1;
	SecureView secureView;
	RetrieveProfile retrieveProfile;
	
	
	public SignIn(SecureView secureView,RetrieveProfile retrieveProfile){
		this.secureView=secureView;
		this.retrieveProfile=retrieveProfile;
	}
	public void login(){
		 
		 secureView.userInteractions("Enter a Name : ");
		 String name=secureView.getScanner().next();
		 
		 while(true&&loginattempts<=3) {
		 secureView.userInteractions("Enter a Password :");
		 String password=secureView.getScanner().next();
		 AccountProfile response=retrieveProfile.logIn(name, password);
		 
		 if(response!=null) {
			secureView.userInteractions("Enter otp that in your email");
			String userotp=secureView.getScanner().next();
			
			if(multifactorauthentication().equals(userotp)) {
			secureView.userInteractions(response.getUsername()+" "+response.getAccountNumber());
			 break; 
			}
			else {
				secureView.userInteractions("Your email verification failed.");
			}
			
		 }
		 
		 else {
			 secureView.userInteractions("Enter a Valid Password :");
			 loginattempts++;
		 }
		 
		 }
		 
	 }
	 
	 
	static String multifactorauthentication(){
		
		String senderEmail="bsankar200@gmail.com";
		String senderPassword="Tvcb@1073";
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session=Session.getInstance(properties,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(senderEmail, senderPassword);
		    }
		});
		
		int otp=0;
		Message message=new MimeMessage(session);
		try {
		message.setFrom(new InternetAddress(senderEmail));
		message.setRecipient(Message.RecipientType.TO,new InternetAddress("pandimr67@gmail.com"));
		message.setSubject("Verification mail.....");
		Random random=new Random();
		 otp=random.nextInt(999999);
		message.setText("your otp for account login verification from ***** "+String.format("%06d", otp)+".");
		Transport.send(message);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return String.format("%06d", otp);
	 }
	
	
	public static void main(String[] args) {
		System.out.println(multifactorauthentication());
	}
	
	
}
