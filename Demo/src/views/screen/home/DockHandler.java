package views.screen.home;

import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.Utils;
import javafx.scene.control.Button;

public class DockHandler extends FXMLScreenHandler {

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
	
	public DockHandler(String screenPath, Dock dock, HomeScreenHandler home) throws IOException, SQLException{
		super(screenPath);
		this.dock = dock;
		this.home = home;
		
		setDockInfo();
	}
	
	private void setDockInfo() throws SQLException {
		
		dockName.setText(dock.getName());
		dockAddress.setText(dock.getAddress());
		dockAvailableBikes.setText(Integer.toString(dock.getAvailableBikes()));
		dockEmptySlots.setText(Integer.toString(dock.getEmptySlots()));
	}
}
