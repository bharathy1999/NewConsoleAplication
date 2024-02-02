package com.studententry.controller;

import com.student.view.StudentAttendanceEntryView;
import com.studententry.DAO.CreateLastEntry;
import com.studententry.DAO.CreateMonthEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.Iterator;

public class AttendanceEntry {

	CreateMonthEntry createMonthEntry;
	StudentAttendanceEntryView studentAttendanceEntryView ;
	CreateLastEntry createLastEntry;
	
	public AttendanceEntry(CreateMonthEntry createMonthEntry,StudentAttendanceEntryView studentAttendanceEntryView,CreateLastEntry createLastEntry){
		this.createMonthEntry=createMonthEntry;
		this.studentAttendanceEntryView=studentAttendanceEntryView;
		this.createLastEntry=createLastEntry;
	}

	public void createAttendance() {

		LocalDate currentDate = LocalDate.now();
		Month currentMonth = currentDate.getMonth();
		String monthName = currentMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		int dayOfMonth = currentDate.getDayOfMonth();
		int totalDays = currentDate.lengthOfMonth();

		if (dayOfMonth == 1) {

			createMonthEntry.createNewDatabase(monthName);

		}

		else if (dayOfMonth == totalDays) {
			createLastEntry.deleteDatabase(monthName,totalDays);
		}

		else {
			createMonthEntry.enterTheAttendance(monthName);
		}

	}

	public void absentEntry() {

		studentAttendanceEntryView.userInteractions("How many absent entries today.");
		int numberOfAbsent = studentAttendanceEntryView.getScanner().nextInt();
		studentAttendanceEntryView.userInteractions(" Enter the student names one by one.");

		for (int i = 0; i < numberOfAbsent; i++) {

			String studentName = studentAttendanceEntryView.getScanner().next();
			createMonthEntry.loadStudents.studentList.put(studentName, "A    ");

		}
	}

}
