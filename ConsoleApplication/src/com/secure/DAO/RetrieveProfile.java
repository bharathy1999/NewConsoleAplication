package com.secure.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import com.secure.controller.Jdbcconnection;
import com.secure.controller.SignUp;
import com.secure.model.AccountProfile;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
public class RetrieveProfile {
   SignUp signUp;
	public RetrieveProfile (SignUp signup){
		this.signUp=signUp;
	}
	
	public AccountProfile logIn(String name ,String password){
		AccountProfile accountProfile=null;
		try {
		
		PreparedStatement  preparedStatement=Jdbcconnection.getConnectionJdbc().prepareStatement("Select * from accountprofile where name=?"); 
		preparedStatement.setString(1, name);
		ResultSet resultSet=preparedStatement.executeQuery();
		while (resultSet.next()) {
	        if( resultSet.getString("password").equals(signUp.hashing(password))){
	        String userName=resultSet.getString("name");
	        byte[] decodedKeyBytes = Base64.getDecoder().decode(resultSet.getString("secretKey"));
	        SecretKey secretkey=new SecretKeySpec(decodedKeyBytes, "AES");
            long accountnumber=signUp.decrypt(resultSet.getString("accountnumber"),secretkey);
            accountProfile=new AccountProfile(userName,accountnumber);
	        }
	    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return accountProfile;
	}
	
	
	
	
	
}
