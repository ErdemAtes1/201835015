package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Management extends User{

	Statement st = null;
	   ResultSet rs = null;
	   Connection con = conn.connDB();
	   PreparedStatement preparedStatement = null;
	
	public Management(int id, String username, String password, String type) {
		super(id, username, password, type);
		// TODO Auto-generated constructor stub
	}

	
	public Management() {
		
	}
	
	public ArrayList<User> getDoctorList () throws SQLException {
	   ArrayList<User> list = new ArrayList<>();
	   
	   User obj;	  
	   try {
		   st = con.createStatement();
		   rs = st.executeQuery("SELECT * FROM user WHERE type= 'doctor'");
		   while(rs.next()) {
			   
			   obj = new  User (rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("type"));
			   list.add(obj);
		   }
	   } catch (SQLException e) {
		   
		   e.printStackTrace();
	   }
	   
	   return list;
	   
		
	}
	
	public boolean addDoctor(String username,String password) throws SQLException {
		
		String query = "INSERT INTO user" + "(username,password,type) VALUES" + "(?,?,? )";
		boolean key = false ;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,username);
			preparedStatement.setString(2,password);
			preparedStatement.setString(3,"doctor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return  true;
		else
		    return false;
	}
	
	
public boolean deleteDoctor(int	 id) throws SQLException {
		
		String query = "DELETE FROM user WHERE id= ? ";
		boolean key = false ;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return  true;
		else
		    return false;
	}
	
}
 