package views.screen.bike;

import java.io.IOException;
import java.util.logging.Logger;

import entity.bike.BikeType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

public class BikeInfoHandler extends BaseScreenHandler {

	public static Logger LOGGER = Utils.getLogger(BikeInfoHandler.class.getName());
	
	@FXML
	private Label barcode;
	
	@FXML
	private Label type;
	
	@FXML
	private Label feature;
	
	@FXML
	private Label battery;
	
	@FXML 
	private Label dock;
	
	@FXML
	private Label rentingFee;
	
	@FXML
	private Label depositFee;
	
	private BikeType bike;
	
	public BikeInfoHandler(Stage stage, String screenPath, BikeType bike) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
	}
	
	public void requestToViewBikeInfo(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Bike Information");
		show();
	}
	
}
