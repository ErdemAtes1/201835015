package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DbHelper.Helper;

public class Patient extends User {
	Statement st = null;
	   ResultSet rs = null;
	   Connection con = conn.connDB();
	   PreparedStatement preparedStatement = null;
	

	public Patient() {
		super();
	}

	public Patient(int id, String username, String password, String type) {
		super(id, username, password, type);
	}
	
	public boolean register(String username,String password) throws SQLException  {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user "+ "(username,password,type) VALUES"+ "(?,?,?)";
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE username='" + username + "' ");
			
			while(rs.next()) {
				duplicate = true;
				Helper.showMsg("This username already exist.");
				break;
				
			}
			
			if(!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1,username);
				preparedStatement.setString( 2,password);
				preparedStatement.setString( 3,"patient");

				preparedStatement.executeUpdate();
				key = 1;

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (key==1)
			return true ;
		else
		 
		    return false ;
	}
	
	
	
	

}
