package views.screen.bike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import controller.HomeController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.card.CardScreenHandler;

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
	
	@FXML
	private Button rentBtn;
	
	@FXML
	private Button exitBtn;
	
	private Bike bike;
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	public BikeInfoHandler(Stage stage, String screenPath, Bike bike, HomeScreenHandler home) throws IOException, SQLException {
		super(stage, screenPath);
		this.bike = bike;
		
		setBController(new HomeController());
		
		logo.setOnMouseClicked(e -> {
//			this.getPreviousScreen().show();
			homeScreenHandler.show();
		});
		
		rentBtn.setOnMouseClicked(e -> {
			
			CardScreenHandler cardScreen;
			try {
				LOGGER.info("User clicked rent button and navigated to the card screen.");
				cardScreen = new CardScreenHandler(this.getPreviousScreen().getStage(), Configs.CARD_PATH, bike, home);
				cardScreen.setHomeScreenHandler(home);
				cardScreen.requestToCardScreen(this);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
	
	public void setBikeInfo() throws SQLException {
		barcode.setText(this.bike.getBarcode());
		type.setText(this.bike.getType());
		feature.setText(this.bike.getPedals() + " pedal, " + this.bike.getSaddles() + " saddle, " + this.bike.getRearSeats() + " rear seat");
		battery.setText(Integer.toString(this.bike.getBattery()));
		dock.setText(getBController().getDockById(this.bike.getDockId()).getName());
		rentingFee.setText(Integer.toString(this.bike.getRentingFee()));
		depositFee.setText(Integer.toString(this.bike.getDepositFee()));
		
	}
	
}
