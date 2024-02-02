package com.traineeCreator.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trainee {
	private int id;
	private String name;
	private LocalDate dateOfBirth;
	private LocalDate joiningDate;
	private String password;
	private ArrayList<String> iDidThis = new ArrayList();
	public int gems;

	public Trainee(String name, String password) {
		this.name = name;
		this.password = password;
		this.joiningDate  = LocalDate.now();
		
	}

	public Trainee(int id, String name, String dateOfBirth, String joiningDate, String password,
			ArrayList<String> ididthis, int gems) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = LocalDate.parse(dateOfBirth);
		this.joiningDate = LocalDate.parse(joiningDate);
		this.password = password;
		this.iDidThis = ididthis;
		this.gems = gems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dateOfBirth;
	}

	public void setDob(String date) {
		this.dateOfBirth = LocalDate.parse(date);
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getIDidThis() {
		return iDidThis;
	}

	public void setIDidThis(String didthis) {
		this.iDidThis.add(didthis);
	}

	public void setGems(int gems) {
		this.gems += gems;
	}
	
	
	public String toString() {
		return this.name;
	}
	
}
