package common.exception;

import java.io.IOException;

import views.screen.popup.PopupScreen;

public class NotEnoughBalanceException extends PaymentException{

	public NotEnoughBalanceException() throws IOException {
		super("ERROR: Not enough balance in card!");
		PopupScreen.error("ERROR: Not enough balance in card!");
	}

}
