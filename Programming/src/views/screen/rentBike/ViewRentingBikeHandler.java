package views.screen.rentBike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import controller.RentBikeController;
import entity.bike.Bike;
import entity.rent.Rent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.popup.ReturnPopUpHandler;


public class ViewRentingBikeHandler extends BaseScreenHandler  {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

	@FXML 
	private Button enterBtn;
	
	@FXML
	private ImageView ecoImage;
	
	@FXML 
	private AnchorPane bikeInfoPane;
	
	@FXML 
	private Button rentBtn;
	
	@FXML
	private Label barcode;
	
	@FXML
	private Label type;
	
	@FXML
	private Label feature;
	
	@FXML
	private Label time;
	
	@FXML
	private Label fee;
	
	@FXML
	private Label battery;
	
	private Rent rent;
	
	
	
	public ViewRentingBikeHandler(Stage stage, String screenPath, Rent rent) throws IOException {
		super(stage, screenPath);
		this.rent = rent;
	}
	
	@Override
	public void load() {
        setHController(new RentBikeController());
		
    	this.stage.setOnCloseRequest(e -> {
			try {
				if (state == 0) {
					getHController().updateUsingState(account, false);	
				} else {
					PopupScreen.warning("Please return bike before exiting");
					e.consume();
				}
			} catch (IOException | SQLException  e1) {
				e1.printStackTrace();
			}
		});
    	
		ecoImage.setOnMouseClicked(e -> {
			LOGGER.info("User clicked the logo to return the homepage");
			homeScreenHandler.requestToNewScreen(homeScreenHandler);
			try {
				homeScreenHandler.loadAllItems();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		Timeline tl = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			Stage s;
			setInfo();
			if(this.rent.getBike().getBattery() <= 10 && this.rent.getBike().getBattery() > 0) {
				
				try {
					PopupScreen.warning("PLEASE RETURN BIKE");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (this.rent.getBike().getBattery() == 0 && (this.rent.getBike().getTypeId() == 2 || this.rent.getBike().getTypeId() == 4)) {}
			
		}), new KeyFrame(Duration.seconds(1))
		);
		
		tl.setCycleCount(this.rent.getBike().getBattery()*6);
		tl.play();
	}
	@Override
	public void setInfo() {
		Bike bike = this.rent.getBike();
		this.rent.setRentTime((int) (System.currentTimeMillis() - this.rent.getStart())/1000);
		
		if (getHController().updateBattery(this.rent) == -1) {
			battery.setText("N/A");
		} else {
			battery.setText(Integer.toString(getHController().updateBattery(this.rent)));
		}
		
		barcode.setText(bike.getBarcode());
		type.setText(bike.getType());
		feature.setText(bike.getFeatures());
		fee.setText(Double.toString(getHController().calculateFee(this.rent)));
		time.setText(Integer.toString(this.rent.getRentTime()));
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("View Renting Bike Screen");
		show();
	}
	
	// getter and setter
	public RentBikeController getHController() {
		return (RentBikeController) super.getHController();
	}
}
