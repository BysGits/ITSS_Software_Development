package views.screen.rentBike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.LogManager;

import java.util.logging.Logger;

import controller.HomeController;
import entity.bike.Bike;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.card.CardScreenHandler;
import views.screen.home.HomeScreenHandler;


public class RentBikeScreenHandler extends BaseScreenHandler {
	
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
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	public RentBikeScreenHandler(Stage stage, String screenPath, Rent rent) throws IOException {
		super(stage, screenPath);
		this.rent = rent;
		// TODO Auto-generated constructor stub
		
		ecoImage.setOnMouseClicked(e -> {
			LOGGER.info("User clicked the logo to return the homepage");
			homeScreenHandler.show();
		});
		
		setRentInfo();
		
	}
	
	public void requestToRentBike(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Rent Bike Screen");
		
		show();
	}
	
	public void setRentInfo() {
		Bike bike = this.rent.getBike();
		this.rent.setRentTime((int) (System.currentTimeMillis() - this.rent.getStart())/1000);
		System.out.println(this.rent.getStart());
		System.out.println(System.currentTimeMillis());
		

		barcode.setText(bike.getBarcode());
		type.setText(bike.getType());
		feature.setText(bike.getPedals() + " pedal, " + bike.getSaddles() + " saddle, " + bike.getRearSeats() + " rear seat");
		battery.setText(Integer.toString(bike.getBattery()));
		
		fee.setText(Double.toString(rent.calculateFee()));
		time.setText(Integer.toString(this.rent.getRentTime()));
	}
}
