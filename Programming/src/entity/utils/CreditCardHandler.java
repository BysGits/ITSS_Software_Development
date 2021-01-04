package entity.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.ECOBIKEDB;
import entity.payment.CreditCard;

public class CreditCardHandler {
	
	public static CreditCard assignFromDB(ResultSet res) throws SQLException {
		return new CreditCard()
				.setCardCode(res.getString("cardCode"))
				.setCvvCode(res.getInt("cvvCode"))
				.setOwner(res.getString("owner"))
				.setDateExpired(res.getString("dateExpired"))
				.setId(res.getInt("id"));
	}
	
	public static CreditCard getCreditCardByCardcode (String cardCode) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM card WHERE cardCode = '" + cardCode + "';";
		ResultSet res = stm.executeQuery(query);
		
		if (res.next()) {
			return assignFromDB(res);
		}
		
		return null;
	}
	
}
