package com.studententry.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.util.Map;

import com.studententry.DAO.CreateMonthEntry;
import com.studententry.DAO.LoadStudents;

public class CreateTextFileOfAttendance {
	LoadStudents loadStudents;
	CreateMonthEntry createMonthEntry;
	
	public CreateTextFileOfAttendance(LoadStudents loadStudents,CreateMonthEntry createMonthEntry){
		this.loadStudents=loadStudents;
		this.createMonthEntry=createMonthEntry;
	}

	public void createMonthAttendance(String monthName, int daysOfMonth) {

		try {

			Path path = Path.of(monthName);
			int len = largestNameOfSudents();
			StringBuilder datesString = new StringBuilder();
			for (int i = 1; i <= 31; i++) {
				datesString.append((i < 10 ? "0" + i : i) + "   ");
			}

			BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE);
			bufferedWriter.write(String.format("%-" + (len + 3) + "s", "Date") + datesString.toString());
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			ResultSet resultSet = createMonthEntry.resultSetForTextFile(monthName);
			while (resultSet.next()) {
				bufferedWriter.write(
						String.format("%-" + (len + 3) + "s", resultSet.getString(2)) + "" + resultSet.getString(3));
			}

		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int largestNameOfSudents() {

		int max = 0;

		for (String names : loadStudents.studentList.keySet()) {
			if (names.length() > max) {
				max = names.length();
			}
		}
		return max;

	}

}
