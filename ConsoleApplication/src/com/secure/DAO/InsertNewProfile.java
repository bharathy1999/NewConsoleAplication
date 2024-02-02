package com.secure.DAO;

import java.sql.PreparedStatement;

import javax.crypto.SecretKey;

import com.secure.controller.Jdbcconnection;



public class InsertNewProfile {

	public void createProfile(String name, String password,String accountNumber,String secretKey) {
        try {
			PreparedStatement  preparedStatement=Jdbcconnection.getConnectionJdbc().prepareStatement("insert into accountprofile (userName,password,accountNumber,secretKey)  values (?,?,?,?)");
			preparedStatement.setString(1,name );
			preparedStatement.setString(2,password) ;
			preparedStatement.setString(3, accountNumber);
			preparedStatement.setString(4, secretKey);
			preparedStatement.executeUpdate();
			preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

       

}

	
}
