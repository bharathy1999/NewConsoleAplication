package com.secure.view;

import java.util.Scanner;

import com.secure.DAO.InsertNewProfile;
import com.secure.DAO.RetrieveProfile;
import com.secure.controller.SignIn;
import com.secure.controller.SignUp;

public class SecureView {
private Scanner scanner=new Scanner(System.in);
	
	public void userInteractions(String outputUser) {
		System.out.println(outputUser);
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	


	public static void main(String[] args) {
		SecureView  secureView =new SecureView (); 
		InsertNewProfile insertNewProfile=new InsertNewProfile();
		SignUp signUp=new SignUp(secureView,insertNewProfile);
		RetrieveProfile retrieveProfile=new RetrieveProfile(signUp);
		SignIn signIn=new SignIn(secureView,retrieveProfile);
		
		secureView.userInteractions(" If you have a account press 1 for sign in.... otherwise create a account");
		byte number=secureView.scanner.nextByte();
		
		if(number==1) {
			signIn.login();
		}
		else {
			signUp.create();
		}
		
	}
	
	
}
