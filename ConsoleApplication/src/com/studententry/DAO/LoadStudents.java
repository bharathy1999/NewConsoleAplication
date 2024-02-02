package com.studententry.DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.studententry.controller.JdbcConnection;

public class LoadStudents {

	public HashMap<String, String> studentList = new HashMap();

	public LoadStudents() {
		loadListOfStudent();
	}

	void loadListOfStudent() {

		try {
			Statement statement = JdbcConnection.getJdbcConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Student");

			while (resultSet.next()) {
				studentList.put(resultSet.getString(2), "P    ");
			}

			JdbcConnection.closeTheConnection();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}