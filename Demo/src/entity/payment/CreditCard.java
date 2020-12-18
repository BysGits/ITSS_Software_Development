package entity.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import entity.db.ECOBIKEDB;

public class CreditCard {

	private String cardCode;
	private String owner;
	private int cvvCode;
	private String dateExpired;
	
	public CreditCard(){
		
	}
	public CreditCard(String cardCode, String owner, int cvvCode, String dateExpired) {
		super();
		this.cardCode = cardCode;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}
	
	public void deductMoney () {
		
	}

	public String getCardCode() {
		return cardCode;
	}

	public CreditCard setCardCode(String cardCode) {
		this.cardCode = cardCode;
		return this;
	}

	public String getOwner() {
		return owner;
	}

	public CreditCard setOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public int getCvvCode() {
		return cvvCode;
	}

	public CreditCard setCvvCode(int cvvCode) {
		this.cvvCode = cvvCode;
		return this;
	}

	public String getDateExpired() {
		return dateExpired;
	}

	public CreditCard setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
		return this;
	}
	
	public CreditCard getCreditCard(String cardCode) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM ECOBIKE.CARD WHERE CARDCODE = " + cardCode + ";";
		
		ResultSet res = stm.executeQuery(query);
		
		if (res.next()) {
			return new CreditCard()
					.setCardCode(res.getString("cardCode"))
					.setCvvCode(res.getInt("cvv"))
					.setOwner(res.getString("owner"))
					.setDateExpired(res.getString("dateExpired"));
			
		}
		
		return null;
	}
	
}
