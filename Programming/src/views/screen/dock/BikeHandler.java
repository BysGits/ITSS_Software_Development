package views.screen.dock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeInfoHandler;
import views.screen.home.DockHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;
import entity.account.Account;

public class BikeHandler extends BaseScreenHandler {

	private static Logger LOGGER = Utils.getLogger(DockHandler.class.getName());
	
	@FXML
	protected Label barcode;
	
	@FXML
	private Label bikeType;
	
	@FXML
	private Label battery;
	
	@FXML
	private Button rentBtn;

	
	public Button getRentBtn() {
		return rentBtn;
	}
	
	public BikeHandler(String screenPath) throws IOException, SQLException {
		super(screenPath);
	}
	
	@Override
	public void load() throws SQLException {
		System.out.println("BikeHanlder: " + account.getCardCode());
		setHController(new HomeController());
		
		if (state == 1) {
			rentBtn.setDisable(true);
		} else if (!getHController().checkIfBikeisAvailable(bike)) {
			rentBtn.setDisable(true);
		}
		
		rentBtn.setOnMouseClicked(e -> {
			
			try {
				BikeInfoHandler bikeInfoScreen;
				if (getHController().checkIfBikeisAvailable(bike)) {
					LOGGER.info("User clicked to view bike information");
					
					bikeInfoScreen = new BikeInfoHandler(dockScreenHandler.getStage(), Configs.BIKE_INFO_PATH);
					bikeInfoScreen.setBike(bike);
					bikeInfoScreen.setState(state);
					bikeInfoScreen.setAccount(account);
					bikeInfoScreen.setDockScreenHandler(dockScreenHandler);
					bikeInfoScreen.setHomeScreenHandler(homeScreenHandler);
					bikeInfoScreen.requestToNewScreen(dockScreenHandler);
					bikeInfoScreen.load();
				} else {
					PopupScreen.error("This bike has been rented by another one");
				}
				
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		setInfo();
	}
	
	@Override
	public void setInfo() throws SQLException {
		barcode.setText(bike.getBarcode());
		bikeType.setText(bike.getType());
		
		if (bike.getBattery() == 0) {
			battery.setText("N/A");
		} else {
			battery.setText(Integer.toString(bike.getBattery()));
		}
	}



}
