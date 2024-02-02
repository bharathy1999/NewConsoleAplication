package com.traineeCreator.DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.traineeCreator.model.Trainee;

public class LoadTrainee {
	
	public ArrayList<Trainee> traineeList = new ArrayList();
	
	public LoadTrainee(){
		loadInitialData();
	}
	
	public void loadInitialData() {
		
		try {
			Path filePath = Path.of(
					"/home/bharathi/eclipse-workspace/ConsoleApplication/src/com/traineeCreator/DAO/Studentfile.json");
			String contentofjson = Files.readString(filePath);
			JSONArray jsonarray = new JSONArray(contentofjson);

			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobj = jsonarray.getJSONObject(i);
				int id = jsonobj.getInt("id");
				String name = jsonobj.getString("name");
				String dob = jsonobj.getString("dateOfBirth");
				String joiningdate = jsonobj.getString("joiningDate");
				String password = jsonobj.getString("password");
				ArrayList ididthis = jsonToList(jsonobj.getJSONArray("iDidThis"));
				int gems = jsonobj.getInt("gems");

				Trainee trainee = new Trainee(id, name, dob, joiningdate, password, ididthis, gems);
				traineeList.add(trainee);

			}

		}

		catch (IOException io) {
			io.printStackTrace();
		}

		
	}

	private ArrayList<String> jsonToList(JSONArray jsonarr) {
		ArrayList<String> arraylist = new ArrayList();
		for (int i = 0; i < jsonarr.length(); i++) {
			arraylist.add(jsonarr.getString(i));
		}
		return arraylist;
	}

	public void writeIntoJson(ArrayList<Trainee> arrayList) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[\n\n");

		for (int i = 0; i < arrayList.size(); i++) {
			Trainee trainee = arrayList.get(i);

			stringBuilder.append("{\n");
			stringBuilder.append("\"id\":").append(trainee.getId()).append(",\n");
			stringBuilder.append("\"name\":\"").append(trainee.getName()).append("\",\n");
			stringBuilder.append("\"dateOfBirth\":\"").append(trainee.getDob()).append("\",\n");
			stringBuilder.append("\"joiningDate\":\"").append(trainee.getJoiningDate()).append("\",\n");
			stringBuilder.append("\"password\":\"").append(trainee.getPassword()).append("\",\n");
			stringBuilder.append("\"iDidThis\":").append("[");
			ArrayList<String> iDidThis = trainee.getIDidThis();
			for (int j = 0; j < iDidThis.size(); j++) {
				if (j == iDidThis.size() - 1) {
					stringBuilder.append("\"").append(iDidThis.get(j)).append("\"");
				} else {
					stringBuilder.append("\"").append(iDidThis.get(j)).append("\",");
				}
			}
			stringBuilder.append("],\n");
			stringBuilder.append("\"gems\":").append(trainee.gems);
			stringBuilder.append("},\n\n");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		stringBuilder.append("]");

		try {
			Path filePath = Path.of(
					"/home/bharathi/eclipse-workspace/ConsoleApplication/src/com/traineeCreator/DAO/Studentfile.json");
			File file = filePath.toFile();
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(stringBuilder.toString());
		} catch (IOException io) {
			io.printStackTrace();
		}

	}
	public static void main(String[] args) {
		LoadTrainee loadTrainee=new LoadTrainee();
		for(Trainee trainee:loadTrainee.traineeList) {
			System.out.println(trainee);
		}
	}

}
