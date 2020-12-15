package views.screen.bike;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class BikeScreenHandler extends BaseScreenHandler {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private FlowPane flowPaneBike;
	
	public BikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		
		
	}
	
	public void addBike(List bikes) {
		ArrayList bikeList = (ArrayList)((ArrayList) bikes).clone();
		
		while (!bikeList.isEmpty()) {
			BikeHandler bike = (BikeHandler) bikeList.get(0);
			flowPaneBike.getChildren().add(bike.getContent());
			bikeList.remove(bike);
		}
	}

	public void requestToViewDock(BaseScreenHandler prevScreen) {
		// TODO Auto-generated method stub
		setPreviousScreen(prevScreen);
		setScreenTitle("Dock Screen");
		
		show();
		
	}
	
}
