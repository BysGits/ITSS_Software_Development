package entity.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import entity.bike.Bike;
import entity.db.ECOBIKEDB;
import entity.payment.CreditCard;
import entity.rent.Rent;
import utils.Utils;

public class Invoice {
	private static Logger LOGGER = Utils.getLogger(Invoice.class.getName());
	
	private Rent rent;
	
	// invoice variables
	private int id;
	private int totalAmount;
	private int totalTime;
	private int bikeId;
	private int startDockId;
	private int endDockId;
	private int accountId;
	
	public int getAccountId() {
		return accountId;
	}

	public Invoice setAccountId(int accountId) {
		this.accountId = accountId;
		return this;
	}

	private String cardCode;
	private String owner;
	private String transactionId;
	private String createdAt;
	
	
	
	public Invoice() {}
	
	public Invoice (int id, Rent rent, int amount, String transactionId) {
		this.id = id;
		this.rent = rent;
		this.totalAmount = amount;
		this.transactionId = transactionId;
	}

//	public void saveInvoice(Rent rent, int totalAmount) {
//		
//	}
	
	
	
	// getter and setter
	
	public String getCreatedAt() {
		return createdAt;
	}

	public Invoice setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public int getTotalAmount() {
		return totalAmount;
	}
	
	public String getCardCode() {
		return cardCode;
	}

	public Invoice setCardCode(String cardCode) {
		this.cardCode = cardCode;
		return this;
	}

	public String getOwner() {
		return owner;
	}

	public Invoice setOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public int getTotalTime() {
		return totalTime;
	}
	
	public int getBikeId() {
		return bikeId;
	}
	
	public int getStartDockId() {
		return startDockId;
	}
	
	public int getEndDockId() {
		return endDockId;
	}
	
	public int getId() {
		return id;
	}
	
	public Invoice setRent(Rent rent) {
		this.rent = rent;
		return this;
	}
	
	public Invoice setBikeId(int id) {
		this.bikeId = id;
		return this;
	}
	
	public Invoice setStartDockId(int id) {
		this.startDockId = id;
		return this;
	}
	
	public Invoice setEndDockId(int id) {
		this.endDockId = id;
		return this;
	}
	
	public Invoice setTotalTime(int totalTime) {
		this.totalTime = totalTime;
		return this;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public Invoice setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public Invoice setId(int id) {
		this.id = id;
		return this;
	}
	
	public Invoice(Rent rent) {
		this.rent = rent;
	}
	
	public Rent getRent() {
		return rent;
	}
	
	public Invoice setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

	
}
