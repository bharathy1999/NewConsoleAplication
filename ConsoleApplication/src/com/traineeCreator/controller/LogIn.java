package com.traineeCreator.controller;

import java.util.ArrayList;

import com.traineeCreator.model.Trainee;
import com.traineeCreator.view.TraineeView;
import com.traineeCreator.DAO.LoadTrainee;

public class LogIn{
	
	TraineeView  traineeView;
	LoadTrainee loadTrainee;
	ArrayList<Trainee> traineeList;

	
	public LogIn(LoadTrainee loadTrainee,TraineeView  traineeView){
		this.traineeView=traineeView;
		this.loadTrainee=loadTrainee;
		this.traineeList=loadTrainee.traineeList;
	}
 
   
 public void accountRetrieve() {
	
	 traineeView.userQuestions("Enter a Name");
	 String name=traineeView.giveScannner().next();
	 traineeView.userQuestions("Enter a Password");
	 String password=traineeView.giveScannner().next();
	  Trainee traineeAccount=null;
	  
	  int indexOfTrainee=0;
	  
	  for(Trainee trainee: traineeList) {
		  
		  if(trainee.getName().equals(name)&&trainee.getPassword().equals(password)) {
			  traineeAccount=trainee;
			  break;
		  }
		  
		  indexOfTrainee++;
	  }
	  if(traineeAccount==null) {
		  return ;
	  }
	  
	  traineeView.userQuestions("If you want to update I Did This press 1");
	  byte userInput =traineeView.giveScannner().nextByte();
	  
	  if(userInput==1) {
		  traineeView.userQuestions("Enter your I Did This...");
		  String iDidThis=traineeView.giveScannner().nextLine();
		  traineeAccount.getIDidThis().add(iDidThis);
	  }
	  
	 
	  if(traineeAccount.getId()==0) {
		  traineeView.userQuestions("If you want to update I Did This press 1");
		  byte userInputForId =traineeView.giveScannner().nextByte();
		  
		  if(userInputForId==1) {
			  traineeView.userQuestions("Enter your ID");
			  int  iD=traineeView.giveScannner().nextInt();
			  traineeAccount.setId(iD);
		  }
	  }
	  
	  
	  
	  if(traineeAccount.getDob()==null) {
		  traineeView.userQuestions("Update DOB press1");
		  byte userInputForDOB =traineeView.giveScannner().nextByte();
		  
		  if(userInputForDOB==1) {
			  traineeView.userQuestions("Enter your DOB like \"yyyy-mm--dd\"");
			  String dOB=traineeView.giveScannner().next();
			  
			  traineeAccount.setDob(dOB);
		  }
		  
	  }
	  
	  traineeView.userQuestions("If you want to add Gems for anyone..press 1");
	  byte userInputForGems=traineeView.giveScannner().nextByte(); 
	  
	  if(userInputForGems==1) {
		  traineeView.userQuestions("Enter your the Trainee Name for adding gems");
		  String traineeName=traineeView.giveScannner().next();
		  addGems(traineeName);
	  }
	  
	  
	  setAccount(indexOfTrainee,traineeAccount);
	  loadTrainee.writeIntoJson(traineeList);
 }
 
   void addGems(String name) {
	   Trainee traineeForGems=null;
	   
	   int indexOfTrainee=0;
	   
	   for(Trainee trainee: traineeList) {
		  
			  if(trainee.getName().equals(name)) {
				  traineeForGems=trainee;
				  break;
			  }
			  indexOfTrainee++;
		  }
	   
	  
	   
	   if(traineeForGems!=null) {
		   traineeView.userQuestions("Enter your Gems");
		   int gems=traineeView.giveScannner().nextInt(); 
		   traineeForGems.setGems(gems);
	       setAccount( indexOfTrainee, traineeForGems);
	   }
	   
   }
 
   
  void setAccount(int index,Trainee trainee){
	  traineeList.set(index, trainee);
   }
 
  public static void main(String[] args) {
	  
	  LoadTrainee loadTrainee=new LoadTrainee();
	  TraineeView  traineeView=new TraineeView();
	  LogIn log=new LogIn(loadTrainee,traineeView);
	  System.out.println(log.traineeList.get(0));
	  
	
}
  

}
