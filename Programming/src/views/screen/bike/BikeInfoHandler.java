package views.screen.bike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import controller.HomeController;
import entity.account.Account;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.BikeHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;
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
	private Button rentBtn;
	
	@FXML
	private Button exitBtn;
	
	@FXML
	private ImageView bikeImg;
	
	
	public BikeInfoHandler(Stage stage, String screenPath) throws IOException, SQLException {
		super(stage, screenPath);
	}
	
	@Override
	public void load() throws SQLException {
		
		setHController(new HomeController());
		
		homeScreenHandler.getStage().setOnCloseRequest(e -> {
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
		
		rentBtn.setOnMouseClicked(e -> {
			
			try {
				BaseScreenHandler cardScreen;
				if (getHController().checkIfBikeisAvailable(bike)) {
					LOGGER.info("User clicked rent button and navigated to the card screen.");
					Stage newStage = new Stage();
					cardScreen = new CardScreenHandler(newStage, Configs.CARD_PATH);
					cardScreen.setState(state);
					cardScreen.setAccount(account);
					cardScreen.setBike(bike);
					cardScreen.setHomeScreenHandler(homeScreenHandler);
					cardScreen.requestToNewScreen(this);
					cardScreen.load();
				} else {
					PopupScreen.error("This bike has been rented by another one");
				}
				
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		exitBtn.setOnMouseClicked(e -> {
			getPreviousScreen().requestToNewScreen(getPreviousScreen());
			try {
				getPreviousScreen().loadAllItems();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		setInfo();
	}
	@Override
	public void setInfo() throws SQLException {
		
		barcode.setText(bike.getBarcode());
		type.setText(bike.getType());
		feature.setText(bike.getFeatures());
		battery.setText(Integer.toString(bike.getBattery()));
		dock.setText(getHController().getDockById(bike.getDockId()).getName());
		depositFee.setText(Integer.toString(bike.getDepositFee()));
		
		switch (bike.getTypeId()) {
		case 1:  setImage(bikeImg, "assets/images/ST.png"); break;
		case 2: setImage(bikeImg, "assets/images/SE.png"); break;
		case 3: setImage(bikeImg, "assets/images/TB.png"); break;
		case 4: setImage(bikeImg, "assets/images/TE.png"); break;
		default: break;
		}
		
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Bike Info Screen");
		show();
	}
	
}
