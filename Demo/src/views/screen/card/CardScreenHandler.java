package views.screen.card;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class CardScreenHandler extends BaseScreenHandler {

	@FXML 
	private ImageView logo;
	
	public CardScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		logo.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
	}
	
	public void requestToCardScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Card Screen");
		show();
	}
	
	//public void requestToCardScreen()

	
}
