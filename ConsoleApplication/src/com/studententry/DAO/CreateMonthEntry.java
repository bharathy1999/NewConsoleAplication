package com.studententry.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;

import com.studententry.controller.JdbcConnection;

public class CreateMonthEntry {

	public LoadStudents loadStudents;
	
	public CreateMonthEntry(LoadStudents loadStudents){
		this.loadStudents=loadStudents;
	}

	public void createNewDatabase(String monthName) {
		Connection connection = null;
		try {
			connection = JdbcConnection.getJdbcConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("create table " + monthName + " ( RegisterNumber int primary key,"
							+ " Name varchar(20) unique key, AttendanceEntry varchar(62));");
			preparedStatement.executeUpdate();

			PreparedStatement preparedStatementForInsert = connection
					.prepareStatement("insert into " + monthName + " values(?,?,?);");
			int i = 1;
			for (String key : loadStudents.studentList.keySet()) {
				preparedStatementForInsert.setInt(1, i);
				preparedStatementForInsert.setString(2, key);
				preparedStatementForInsert.setString(3, loadStudents.studentList.get(key));
				preparedStatementForInsert.addBatch();
				i++;
			}

			preparedStatementForInsert.executeBatch();

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

	public void enterTheAttendance(String monthName) {
		Connection connection = null;
		try {
			connection = JdbcConnection.getJdbcConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from " + monthName);

			while (resultSet.next()) {
				String newEntry = resultSet.getString(3);
				String studentName = resultSet.getString(2);
				PreparedStatement preparedStatementForInsert = connection
						.prepareStatement("update attendanceEntry set AttendanceEntry=? where Name=? ;");
				preparedStatementForInsert.setString(1, newEntry + "" + loadStudents.studentList.get(studentName));
				preparedStatementForInsert.setString(2, studentName);

				preparedStatementForInsert.executeUpdate();
			}
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

	
	public ResultSet resultSetForTextFile(String monthName) {
		ResultSet resultSet = null;
		try {

			Statement statement = JdbcConnection.getJdbcConnection().createStatement();
			resultSet = statement.executeQuery("select * from " + monthName + ";");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultSet;
	}

}
