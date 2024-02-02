package com.studententry.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	static Connection connection=null;
	
    public static Connection getJdbcConnection() {
    	
    	if(connection==null){
    		
    	try {
    	Class.forName("com.mysql.cj.jdbc.Driver"); 
		  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newmysql","abstract-programmer", "example-password");
            }
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	}
    	
    	return connection;
    }
    
    public static void closeTheConnection() {
    	try {
    	connection.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
}
