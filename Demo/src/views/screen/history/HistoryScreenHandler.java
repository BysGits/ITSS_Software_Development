package views.screen.history;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class HistoryScreenHandler extends BaseScreenHandler {

	
	@FXML
	private Button homeBtn;
	
	
	public HistoryScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		
		homeBtn.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
		
		
	}

}
