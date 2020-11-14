package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.*;

public interface InterbankInterface {
	public abstract PaymentTransaction payOrder(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;
	
	public abstract PaymentTransaction refund(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;
	
	public abstract int getBalance(CreditCard card, String contents) throws PaymentException, UnrecognizedException;
}
