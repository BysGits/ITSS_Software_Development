package subsystem.interbank;

import java.io.IOException;
import java.util.Map;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.InvalidTransactionAmountException;
import common.exception.InvalidVersionException;
import common.exception.NotEnoughBalanceException;
import common.exception.NotEnoughTransactionInfoException;
import common.exception.SuspiciousTransactionException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import utils.Configs;
import utils.MyMap;
import utils.Utils;
import views.screen.popup.PopupScreen;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "ARMJx36wQRs=";
	private static final String SECRET_KEY = "BpW3BCBzuRo=";
	private static final String PAY_COMMAND = "pay";
	private static final String REFUND = "refund";
	private static final String VERSION = "1.0.0";

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();
	
	public PaymentTransaction reset(CreditCard card) throws IOException {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.put("cardCode", card.getCardCode());
			transaction.put("owner", card.getOwner());
			transaction.put("cvvCode", card.getCvvCode());
			transaction.put("dateExpired", card.getDateExpired());
						
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}

		String responseText = interbankBoundary.query(Configs.RESET_URL, generateData(transaction));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return requestViewBalance(response);
		
		//return null;
	}
	public PaymentTransaction refund(CreditCard card, int amount, String contents) throws IOException {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		transaction.put("command", REFUND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
		
		//return null;
	}
	
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) throws IOException {
		Map<String, Object> transaction = new MyMap();

		//transaction la mot map (de de dang chuyen sang JSON dua vao API cua interbank) voi cac truong va cac gia tri rieng cua no
		try {
			//toMyMap: bien mot Object thanh kieu Map
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		//Them tiep cac truong vao Map transaction
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		//Request gui den Interbank API
		//requestMap la mot map chua map transactin va truong version
		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		
		//query tra ve mot string response
		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			//Chuyen cai string response thanh map de con dua vao makePaymentTransaction
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}

	private PaymentTransaction makePaymentTransaction(MyMap response) throws IOException {

		if (response.get("transaction") ==  null) {
			return new PaymentTransaction((String) response.get("errorCode"));
		}
			

		MyMap transcation = (MyMap) response.get("transaction");
		
		//Tao mot object CreditCard tu response
		CreditCard card = new CreditCard((String) transcation.get("cardCode"), (String) transcation.get("owner"),
				Integer.parseInt((String) transcation.get("cvvCode")), (String) transcation.get("dateExpired"));
		
		//Tao mot object PaymentTransaction tu response
		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
				(String) transcation.get("transactionId"), (String) transcation.get("transactionContent"),
				Integer.parseInt((String) transcation.get("amount")), (String) transcation.get("createdAt"));

		switch (trans.getErrorCode()) {
		case "00":
			break;
		case "01":
			throw new InvalidCardException();
		case "02":
			throw new NotEnoughBalanceException();
		case "03":
			throw new InternalServerErrorException();
		case "04":
			throw new SuspiciousTransactionException();
		case "05":
			throw new NotEnoughTransactionInfoException();
		case "06":
			throw new InvalidVersionException();
		case "07":
			throw new InvalidTransactionAmountException();
		default:
			throw new UnrecognizedException();
		}

		return trans;
	}
	
	private PaymentTransaction requestViewBalance(MyMap response) throws IOException {
		//Tao mot object CreditCard tu response
		CreditCard card = new CreditCard((String) response.get("cardCode"), (String) response.get("owner"),
				Integer.parseInt((String) response.get("cvvCode")), (String) response.get("dateExpired"));
		//Tao mot object PaymentTransaction tu response
		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
				  Integer.parseInt((String)response.get("balance")));

		switch (trans.getErrorCode()) {
		case "00":
			break;
		case "01":
			throw new InvalidCardException();
		case "02":
			throw new NotEnoughBalanceException();
		case "03":
			throw new InternalServerErrorException();
		case "04":
			throw new SuspiciousTransactionException();
		case "05":
			throw new NotEnoughTransactionInfoException();
		case "06":
			throw new InvalidVersionException();
		case "07":
			throw new InvalidTransactionAmountException();
		default:
			throw new UnrecognizedException();
		}

		return trans;
	}

}
