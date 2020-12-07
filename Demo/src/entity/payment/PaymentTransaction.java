package entity.payment;

public class PaymentTransaction {
	
	private String errorCode;
	private CreditCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
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
}
