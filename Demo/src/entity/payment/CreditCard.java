package entity.payment;

import java.sql.Timestamp;

public class CreditCard {

	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
	
	public CreditCard(String cardCode, String owner, String cvvCode, String dateExpired) {
		super();
		this.cardCode = cardCode;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}
	
}
