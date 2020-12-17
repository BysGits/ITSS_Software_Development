package views.screen.bike;

import java.io.IOException;
import java.util.logging.Logger;

import entity.bike.BikeType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.DockScreenHandler;

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
	
	@FXML
	private ImageView logo;
	
	private BikeType bike;
	
	public BikeInfoHandler(Stage stage, String screenPath, BikeType bike) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		
		logo.setOnMouseClicked(e -> {
			dockScreenHandler.show();
		});
		setBikeInfo();
	}
	
	public void requestToViewBikeInfo(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		System.out.println(prevScreen);
		setScreenTitle("Bike Information");
		show();
	}

//	public void setHomeScreenHandler(DockScreenHandler home) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public void setBikeInfo() {
		barcode.setText(this.bike.getBarcode());
		type.setText(this.bike.getType());
		feature.setText(this.bike.getPedals() + " pedal, " + this.bike.getSaddles() + " saddle, " + this.bike.getRearSeats() + " rear seat");
		battery.setText(Integer.toString(this.bike.getBattery()));
		dock.setText(Integer.toString(this.bike.getDockId()));
		rentingFee.setText(Integer.toString(this.bike.getRentingFee()));
		depositFee.setText(Integer.toString(this.bike.getDepositFee()));
		
	}
	
}
