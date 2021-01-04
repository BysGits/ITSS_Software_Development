package views.screen.popup;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class ReturnFailPopUpHandler extends BaseScreenHandler {
	
	@FXML
	private Button btn;
	

	public ReturnFailPopUpHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
//		 TODO Auto-generated constructor stub
		btn.setOnMouseClicked(e -> {
			this.stage.close();
		});
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Return Failed!!!");
		show();
	}
	
	

}
