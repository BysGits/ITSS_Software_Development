package views.screen.home;

import views.screen.BaseScreenHandler;
import views.screen.dock.DockScreenHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import entity.account.Account;
import entity.dock.Dock;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import javafx.scene.control.Button;

public class DockHandler extends BaseScreenHandler {

	@FXML
	protected Text dockName;
	
	@FXML
	protected Text dockAddress;
	
	@FXML 
	protected Label dockAvailableBikes;
	
	@FXML 
	protected Label dockEmptySlots;
	
	@FXML 
	protected Button chooseBtn;
	
	private static Logger LOGGER = Utils.getLogger(DockHandler.class.getName());
	
	public Button getChooseBtn() {
		return this.chooseBtn;
	}

	public DockHandler(Stage stage, String screenPath) throws IOException, SQLException {
		super(stage, screenPath);
	}
	
	@Override
	public void load() throws SQLException {
		
		chooseBtn.setOnMouseEntered(e->{
			chooseBtn.setEffect(new DropShadow());
		});
		
		chooseBtn.setOnMouseExited(e->{
			chooseBtn.setEffect(null);
		});
		
		chooseBtn.setOnMouseClicked(e -> {
			BaseScreenHandler dockScreen;
			try {
				LOGGER.info("User clicked to view dock.");

				try {
					dockScreen = new DockScreenHandler(homeScreenHandler.getStage(), Configs.DOCK_PATH);
					dockScreen.setDock(dock);
					dockScreen.setState(state);
					dockScreen.setAccount(account);
					dockScreen.setHomeScreenHandler(homeScreenHandler);
					dockScreen.requestToNewScreen(homeScreenHandler);
					dockScreen.load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		setInfo();
	}
	
	
	@Override
	public void setInfo() throws SQLException {
		dockName.setText(dock.getName());
		dockAddress.setText(dock.getAddress());
		dockAvailableBikes.setText(Integer.toString(dock.getAvailableBikes()));
		dockEmptySlots.setText(Integer.toString(dock.getEmptySlots()));
	}

	
}
