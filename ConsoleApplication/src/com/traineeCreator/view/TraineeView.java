package com.traineeCreator.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

import com.traineeCreator.DAO.LoadTrainee;
import com.traineeCreator.controller.LogIn;


public class TraineeView {
	private Scanner scanner = new Scanner(System.in);

	public void userQuestions(String s) {
		System.out.println(s);
	}

	public Scanner giveScannner() {
		return scanner;
	}
	
	
	public static void main(String[] args) {
		TraineeView traineeView=new TraineeView();
		traineeView.userQuestions("Enter 1 for Login \n 2 for Signup");
		byte userInput=traineeView.giveScannner().nextByte();
		LoadTrainee loadTrainee=new LoadTrainee();
		LogIn logIn=new LogIn(loadTrainee,traineeView);
		if(userInput==1){
			logIn.accountRetrieve();
		}
       if(userInput==2){
			
		}
	}
	
	
	
}
