package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import entity.bike.Bike;
import entity.db.ECOBIKEDB;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankSubsystem;
import java.sql.Statement;

public class RentBikeController extends BaseController {
	
	private InterbankSubsystem interbankSubsystem = new InterbankSubsystem();
	
	public boolean validateCardCode(String cardCode) {
		
		// check the cardCode has 17 characters
		if (cardCode.length() != 17) return false;
		return true;
	}
	
	public boolean validateCvvCode(String cvvCode) {
		
		// check the cvvCode has 3 digits
		if (cvvCode.length() != 3) return false;
		
		// check the cvvCode 
		
		try {
			Integer.parseInt(cvvCode);
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean validateBarcode(String barcode) {
		if (barcode.length() != 6) return false;
		return true;
	}
	
	public boolean validateDeliveryTime(String date) {
		int monthInt;
		int dateInt;
		String monthString;
		String dateString;
		int[] thirtyOneDayMonths = {1,3,5,7,8,10,12};
		
		try {
			if(date.length() != 4) {
				return false;
			}else {
				monthString = date.substring(0, 2);
				dateString = date.substring(2);
				monthInt = Integer.parseInt(monthString);
				dateInt = Integer.parseInt(dateString);
				
				if(!(1 <= monthInt && monthInt <= 12) || !(1 <= dateInt && dateInt <= 31)) { 
					return false;
				}else {
					if((!Arrays.stream(thirtyOneDayMonths).anyMatch(i->i==monthInt))&& (dateInt == 31)){
						return false;
					}
					else if(monthInt == 2 && dateInt > 28) {
						return false;
					}else {
						return true;
					}
				}
			}
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public PaymentTransaction payDepositFee (Bike bike, CreditCard card) {
		int depositFee = bike.getDepositFee();
		String content = "pay deposit fee for renting bike";
		return interbankSubsystem.payOrder(card, depositFee, content);
	}
	
	public int checkCreditCard (String cardCode, String owner, int cvv, String dateExpired ) throws SQLException{
		CreditCard card = new CreditCard().getCreditCard(cardCode);
		
		if(card == null) {
			return 1;
		}else {
			if(card.getOwner() != owner) {
				return 2;
			}else if (card.getCvvCode() != cvv) {
				return 3;
			}else if (card.getDateExpired() != dateExpired) {
				return 4;
			}else {
				return 0;
			}
		}
	}
	
	public 
	
}
