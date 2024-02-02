package com.studententry.DAO;

import java.sql.Connection;
import java.sql.Statement;

import com.studententry.controller.JdbcConnection;
import com.studententry.model.CreateTextFileOfAttendance;

public class CreateLastEntry {
	CreateTextFileOfAttendance createTextFileOfAttendance;
	
	public CreateLastEntry(CreateTextFileOfAttendance createTextFileOfAttendance){
		this.createTextFileOfAttendance=createTextFileOfAttendance;
	}
	
	public void deleteDatabase(String monthName,int daysOfMonth) {
		createTextFileOfAttendance.createMonthAttendance(monthName, daysOfMonth);
		
		
		Connection connection = null;
		try {
			connection = JdbcConnection.getJdbcConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery("drop table " + monthName + ";");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JdbcConnection.closeTheConnection();
		}
	}

}
