package views.screen.history;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class HistoryScreenHandler extends BaseScreenHandler {

	@FXML
	private ImageView logo;
	
	
	
	public HistoryScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		
		logo.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
	}
	
	public void requestToViewHistory(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Rent History");
		show();
	}

}
