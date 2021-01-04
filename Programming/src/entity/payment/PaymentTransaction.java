package entity.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.db.ECOBIKEDB;

public class PaymentTransaction {
	
	private String errorCode;
	private CreditCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	private int balance;
	
	public PaymentTransaction() {}
	
	public PaymentTransaction(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public PaymentTransaction(String errorCode, CreditCard card, int balance) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public PaymentTransaction(String errorCode, CreditCard card, String transactionId, String transactionContent, int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionContent = transactionContent;
		this.transactionId = transactionId;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public PaymentTransaction setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	
	public CreditCard getCard() {
		return card;
	}

	public PaymentTransaction setCard(CreditCard card) {
		this.card = card;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public PaymentTransaction setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public PaymentTransaction setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
		return this;
	}

	public int getAmount() {
		return amount;
	}

	public PaymentTransaction setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public PaymentTransaction setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
		return this;
	}
}
