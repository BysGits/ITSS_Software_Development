package entity.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.account.Account;
import entity.db.ECOBIKEDB;

public class AccountHandler {
	
	public static Account assignFromDB(ResultSet res) throws SQLException {
		return new Account()
				.setId(res.getInt("id"))
				.setUsername(res.getString("username"))
				.setPassword(res.getString("password"))
				.setOwner(res.getString("owner"))
				.setUsing(res.getBoolean("isUsing"))
				.setCardCode(res.getString("cardCode"))
				.setAge(res.getString("age"))
				.setGender(res.getString("gender"));
		}
	
	public static Account getAccount(String username, String password) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM account WHERE username = '" + username + "' AND password = '" + password + "';"; 
		ResultSet res = stm.executeQuery(query);
		
		if (res.next()) {
			return assignFromDB(res);
		}
		
		return null;
	}
	
	public Account getAccountByUsername(String username) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM account WHERE username = '" + username + "';";
		ResultSet res = stm.executeQuery(query);
		
		if (res.next()) {
			return assignFromDB(res);
		}
		
		return null;
	}
	
	public static void newAccount(String username, String password) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "INSERT INTO account(username, password, isAdmin) VALUE('" + username + "','" + password + "',false);"; 
		stm.execute(query);
	}
	
	public static void updateAccountInfo(Account account) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "UPDATE account SET age = '"
				+ account.getAge() + "', gender = '" + account.getGender() + "', owner = '" 
				+ account.getOwner() + "', cardCode = '" + account.getCardCode() + "' WHERE id = "
				+ account.getId() + ";";
		
		stm.executeUpdate(query);
	}
	
	public static void updateUsingState(Account account, boolean state) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "UPDATE account SET isUsing = " + state + " WHERE id = " + account.getId() + ";";
		stm.executeUpdate(query);
	}
	
	
}
