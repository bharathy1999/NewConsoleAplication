package com.traineeCreator.controller;

import java.util.ArrayList;

import com.traineeCreator.DAO.LoadTrainee;
import com.traineeCreator.model.Trainee;
import com.traineeCreator.view.TraineeView;

public class SignUp {
	TraineeView  traineeView;
	LoadTrainee loadTrainee;
	ArrayList<Trainee> traineeList;

	
	public SignUp(LoadTrainee loadTrainee,TraineeView  traineeView){
		this.traineeView=traineeView;
		this.loadTrainee=loadTrainee;
		this.traineeList=loadTrainee.traineeList;
	}
	
	public void create() {
		
		traineeView.userQuestions("Enter a name");
		String name=traineeView.giveScannner().next();
		traineeView.userQuestions("Enter a password");
		String password=traineeView.giveScannner().next();
		
		traineeList.add(new Trainee(name,password));
		loadTrainee.writeIntoJson(traineeList);
	}
	
	
}
