package com.student.view;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.studententry.DAO.CreateLastEntry;
import com.studententry.DAO.CreateMonthEntry;
import com.studententry.DAO.LoadStudents;
import com.studententry.controller.AttendanceEntry;
import com.studententry.controller.JdbcConnection;
import com.studententry.model.CreateTextFileOfAttendance;

public class StudentAttendanceEntryView {

	private Scanner scanner=new Scanner(System.in);
	
	public void userInteractions(String outputUser) {
		System.out.println(outputUser);
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	public static void main(String args []) {
		LoadStudents loadStudents =new LoadStudents();
		CreateMonthEntry createMonthEntry=new CreateMonthEntry(loadStudents);
		StudentAttendanceEntryView studentAttendanceEntryView =new StudentAttendanceEntryView();
		CreateTextFileOfAttendance createTextFileOfAttendance=new CreateTextFileOfAttendance(loadStudents,createMonthEntry);
		CreateLastEntry createLastEntry=new CreateLastEntry(createTextFileOfAttendance);
		AttendanceEntry attendanceEntry=new AttendanceEntry(createMonthEntry,studentAttendanceEntryView,createLastEntry);
		
		attendanceEntry.absentEntry();
		
		attendanceEntry.createAttendance();
	}
	
}
