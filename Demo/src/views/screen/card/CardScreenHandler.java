package views.screen.card;

import java.io.IOException;

import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class CardScreenHandler extends BaseScreenHandler {

	public CardScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	
	public void requestToCardScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Card Screen");
		show();
	}
	
	//public void requestToCardScreen()

	
}
