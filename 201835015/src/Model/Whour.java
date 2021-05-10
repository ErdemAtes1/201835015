package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DbHelper.DbConnection;

public class Whour {
	
	private int  id, doctor_id;
	private String doctor_username,wdate,status;
	DbConnection conn = new DbConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Whour(int id, int doctor_id, String doctor_username, String wdate, String status) {
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_username = doctor_username;
		this.wdate = wdate;
		this.status = status;
	}
	
	public Whour () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_username() {
		return doctor_username;
	}

	public void setDoctor_username(String doctor_username) {
		this.doctor_username = doctor_username;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

	
	
	
}
