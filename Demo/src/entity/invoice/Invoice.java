package entity.invoice;

import entity.rent.Rent;

public class Invoice {
	
	private Rent rent;
	private int amount;
	
	public Invoice() {
		
	}
	
	public Invoice(Rent rent) {
		this.rent = rent;
	}
	
	public Rent getRent() {
		return rent;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void saveInvoice() {
		
	}
}
