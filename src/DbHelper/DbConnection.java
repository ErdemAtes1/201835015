package DbHelper;
import java.sql.*;

public class DbConnection {
     Connection c = null;
     
     public DbConnection() {}
     
     public Connection  connDB() {
    	 try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3307/201835015?user=root&password=");
			return c;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	 
    	 return c;
     }
	
}
