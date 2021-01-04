package entity.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import utils.Utils;

public class ECOBIKEDB {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;
	
	public static Connection getConnection() {
		if (connect != null) return connect;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String URL = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12384681";
			String USER = "sql12384681";
			String PASSWORD = "SaENPgBkGL";
//			String URL = "jdbc:mysql://localhost:3306/ecobike";
//			String USER = "root";
//			String PASSWORD = "120914";
			
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			if (connect == null) {
				System.out.print("TEST");
			}
			LOGGER.info("Connect database successfully");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return connect;
	}
	
	public static void main(String[] args) {
		ECOBIKEDB.getConnection();
	}
}
