package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.bike.Bike;
import entity.db.ECOBIKEDB;
import entity.dock.Dock;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import entity.rent.Rent;
import entity.utils.CreditCardHandler;
import entity.utils.DockHandler;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import views.screen.popup.PopupScreen;

import java.sql.Statement;

public class RentBikeController extends HomeController {
	
	public RentBikeController() {}

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;
	
//	public boolean validateCardCode(String cardCode) {
//		
//		// check the cardCode has 17 characters
//		if (cardCode.length() != 17) return false;
//		return true;
//	}
//	
//	public boolean validateCvvCode(String cvvCode) {
//		
//		// check the cvvCode has 3 digits
//		if (cvvCode.length() != 3) return false;
//		
//		// check the cvvCode 
//		
//		try {
//			Integer.parseInt(cvvCode);
//		} catch(NumberFormatException e) {
//			return false;
//		}
//		
//		return true;
//	}
//	
//	public boolean validateBarcode(String barcode) {
//		if (barcode.length() != 6) return false;
//		return true;
//	}
//	
//	public boolean validateDeliveryTime(String date) {
//		int monthInt;
//		int dateInt;
//		String monthString;
//		String dateString;
//		int[] thirtyOneDayMonths = {1,3,5,7,8,10,12};
//		
//		try {
//			if(date.length() != 4) {
//				return false;
//			}else {
//				monthString = date.substring(0, 2);
//				dateString = date.substring(2);
//				monthInt = Integer.parseInt(monthString);
//				dateInt = Integer.parseInt(dateString);
//				
//				if(!(1 <= monthInt && monthInt <= 12) || !(1 <= dateInt && dateInt <= 31)) { 
//					return false;
//				}else {
//					if((!Arrays.stream(thirtyOneDayMonths).anyMatch(i->i==monthInt))&& (dateInt == 31)){
//						return false;
//					}
//					else if(monthInt == 2 && dateInt > 28) {
//						return false;
//					}else {
//						return true;
//					}
//				}
//			}
//		}catch(NumberFormatException e) {
//			return false;
//		}
//	}
	
	public Map<String,String> payDepositFee (int amount, String contents, CreditCard card) throws IOException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "RENT BIKE FAILED!");
		try {

			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);
			result.put("ERROR_RENT", transaction.getErrorCode());
			if(transaction.getErrorCode().equals("01")) {
				result.put("RESULT", "INVALID CARD");
				return result;
			}
			else if(transaction.getErrorCode().equals("02")) {
				result.put("RESULT", "NOT ENOUGH BALANCE");
				return result;
			}
			
			result.put("TRANSACTION_ID", transaction.getTransactionId());
			result.put("AMOUNT", String.valueOf(transaction.getAmount()));
			result.put("TRANSACTION_CONTENT", transaction.getTransactionContent());
			result.put("RESULT", "RENT BIKE SUCCESSFUL!");
			
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
			
		}
		return result;
	}
	
	
	/**
	 * validate card
	 * @param card
	 * @param depositFee
	 * @return
	 * @throws IOException
	 */
	public int validateCreditCard(CreditCard card, int depositFee) throws IOException {
		
		String contents = "pay deposit fee";
		Map<String, String> response = payDepositFee(depositFee, contents, card);
		
		if (response.get("RESULT").equals("INVALID CARD")) {
			return 1;
		}
		
		if (response.get("RESULT").equals("NOT ENOUGH BALANCE")) {
			return 2;
		}
		
		return 0;
	}
	

	
	/**
	 * CardScreenHandler
	 * @param rent
	 * @throws SQLException
	 */
	public void updateDockAfterRent(Rent rent) throws SQLException {
		Dock dock = DockHandler.getDockById(rent.getBike().getDockId());
		DockHandler.updateDockAfterRent(dock);
	}
	
	/**
	 * CardScreenHandler
	 * @param bike
	 * @return
	 * @throws SQLException
	 */
	public Rent newRent(Bike bike) throws SQLException {
		return new Rent(bike);
	}
	
	
	
	
	
	
	
	
}
