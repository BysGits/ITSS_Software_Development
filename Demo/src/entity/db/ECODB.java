package entity.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ECODB {

	private static Connection connect;
	
	public static Connection getConnection() {
		if (connect != null) return connect;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sonoo";
			connect = DriverManager.getConnection(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public static void main(String[] args) {
		ECODB.getConnection();
	}
}
