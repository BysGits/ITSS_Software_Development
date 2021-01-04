/**
 * 
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.dock.Dock;
import entity.invoice.Invoice;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import entity.rent.Rent;
import entity.utils.DockHandler;
import entity.utils.InvoiceHandler;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

/**
 * @author tkend
 *
 */
public class ReturnBikeController extends HomeController{
	
	public ReturnBikeController() {}
	/**
	 * Represent the card used for payment
	 */

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;
	
	public Map<String,String> refundDepositFee (int amount, String contents, CreditCard card) throws IOException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT_REFUND", "REFUND FAILED!");
		try {

			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.refund(card, amount, contents);
			if(transaction == null) return result;
			
			result.put("REFUND_ID", transaction.getTransactionId());
			result.put("DEPOSIT", String.valueOf(transaction.getAmount()));
			result.put("REFUND_CONTENT", transaction.getTransactionContent());
			result.put("RESULT_REFUND", "REFUND SUCCESSFUL!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
			
		}
		return result;
	}
	
	public Map<String,String> payRent (int depositFee, int amount, String contents, CreditCard card) throws IOException {
		// First, refund deposit fee
		Map<String, String> result = refundDepositFee(depositFee, contents, card);
		result.put("RESULT_PAY", "PAYMENT FAILED!");
		if (result.get("RESULT_REFUND").equals("REFUND FAILED!")) return result;
		System.out.println("Return deposit fee: " + result.get("DEPOSIT"));
		
		try {
			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);
			if(transaction.getErrorCode().equals("01")) {
				result.put("RESULT_PAY", "INVALID CARD");
				return result;
			}
			else if(transaction.getErrorCode().equals("02")) {
				result.put("RESULT_PAY", "NOT ENOUGH BALANCE");
				return result;
			}
			
			result.put("PAY_ID", transaction.getTransactionId());
			result.put("AMOUNT", String.valueOf(transaction.getAmount()));
			result.put("TRANSACTION_CONTENT", transaction.getTransactionContent());
			result.put("createdAt", transaction.getCreatedAt());
			result.put("cardCode", transaction.getCard().getCardCode());
			result.put("owner", transaction.getCard().getOwner());
			result.put("RESULT_PAY", "PAYMENT SUCCESSFUL!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
			
		}
		
		return result;
	}
	
	/**
	 * ReturnPopUpScreenHandler
	 * @param rent
	 * @throws SQLException
	 */
	public void createInvoice(Rent rent, Map<String, String> response) throws SQLException {
		InvoiceHandler.addInvoice(rent, response);
	}
	
	/**
	 * ReturnPopUpHandler
	 * @param rent
	 * @param id
	 * @throws SQLException
	 */
	public void updateDockAfterReturn(int id) throws SQLException {
		Dock dock = DockHandler.getDockById(id);
		DockHandler.updateDockAfterReturn(dock);
	}
	
	public boolean checkIfDockIsFree(int id) throws SQLException {
		Dock dock = DockHandler.getDockById(id);
		
		if (dock.getEmptySlots() == 0) {
			return false;
		} 
		return true;
	}
	
	
	
	
	
	
	
	
}
