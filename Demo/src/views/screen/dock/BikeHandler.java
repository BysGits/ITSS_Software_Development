package views.screen.dock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeInfoHandler;
import views.screen.home.DockHandler;
import views.screen.home.HomeScreenHandler;

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
	
	private Bike bike;
	private DockScreenHandler home;
	
	public BikeHandler(String screenPath, Bike bike, DockScreenHandler home, HomeScreenHandler homeScreenHadler) throws IOException, SQLException {
		super(screenPath);
		this.bike = bike;
		this.home = home;
		// TODO Auto-generated constructor stub
		rentBtn.setOnMouseClicked(e -> {
			BikeInfoHandler bikeInfoScreen;
			try {
				LOGGER.info("User clicked to view bike information");
		
				bikeInfoScreen = new BikeInfoHandler(home.getStage(), Configs.BIKE_INFO_PATH, bike, homeScreenHandler);
				bikeInfoScreen.setDockScreenHandler(home);
				bikeInfoScreen.setHomeScreenHandler(homeScreenHandler);
				bikeInfoScreen.requestToNewScreen(home, "Bike Detail Screen");
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
		});
		setBikeInfo();
	}
	
	
	public void setBikeInfo() throws SQLException {
		barcode.setText(bike.getBarcode());
		bikeType.setText(bike.getType());
		if (bike.getBattery() == 0) {
			battery.setText("N/A");
		} else {
			battery.setText(Integer.toString(bike.getBattery()));
		}
		
	}


}
