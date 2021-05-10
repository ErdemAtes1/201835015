package Model;

import DbHelper.DbConnection;

public class User {

	private int id;
	String username,password,type;
	DbConnection conn =  new DbConnection();
	
	
	
	public User(int id, String username, String password, String type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public User() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
