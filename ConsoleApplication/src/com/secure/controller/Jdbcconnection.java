package com.secure.controller;

import java.sql.Connection;
import java.sql.DriverManager;


public class Jdbcconnection {
	
   private static Connection con=null;
  
  
 public static Connection getConnectionJdbc() {
	 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  con =DriverManager.getConnection("jdbc:mysql://localhost:3306/newmysql","abstract-programmer", "example-password");
	  }
	  catch(Exception e) {
		 e.printStackTrace();
	  }
	  return con;
  }
  
}
