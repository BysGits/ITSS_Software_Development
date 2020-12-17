package views.screen.home;

import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.rentBike.RentBikeScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.ViewDockController;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import javafx.scene.control.Button;

public class DockHandler extends BaseScreenHandler {

	@FXML
	protected Label dockName;
	
	@FXML
	protected Label dockAddress;
	
	@FXML 
	protected Label dockAvailableBikes;
	
	@FXML 
	protected Label dockEmptySlots;
	
	@FXML 
	protected Button chooseBtn;
	
	private static Logger LOGGER = Utils.getLogger(DockHandler.class.getName());
	private Dock dock;
	private HomeScreenHandler home;
	
//	public int getDockId() {
//		return this.dock.getId();
//	}
//	public Stage getStage() {
//		return home.getStage();
//	}
	
	public DockHandler(String screenPath, Dock dock, HomeScreenHandler home) throws IOException, SQLException{
		super(screenPath);
		this.dock = dock;
		this.home = home;
		
		
	
		chooseBtn.setOnMouseClicked(e -> {
		
			DockScreenHandler dockScreen;
			try {
				LOGGER.info("User clicked to view dock.");
				dockScreen = new DockScreenHandler(home.getStage(), Configs.DOCK_PATH, dock);
				dockScreen.setHomeScreenHandler(home);
				//System.out.println(dock.getId());
				dockScreen.requestToViewDock(home);
				
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		setDockInfo();
	}
	
	
	
	
	private void setDockInfo() throws SQLException {
		System.out.println(dock.getId());
		dockName.setText(dock.getName());
		dockAddress.setText(dock.getAddress());
		dockAvailableBikes.setText(Integer.toString(dock.getAvailableBikes()));
		dockEmptySlots.setText(Integer.toString(dock.getEmptySlots()));
	}
}
