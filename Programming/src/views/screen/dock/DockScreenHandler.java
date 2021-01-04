package views.screen.dock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.HomeController;
import entity.account.Account;
import entity.bike.Bike;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

public class DockScreenHandler extends BaseScreenHandler {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private FlowPane flowPaneBike;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private Button homeBtn;
	
	@FXML
	private Button exitBtn;
	
	@FXML
	private Label dockName;
	
	@FXML
	private Label dockAddress;
	
	@FXML
	private Label dockAvailBikes;
	
	@FXML
	private Label dockEmptySlots;

	
	private List<BikeHandler> dockItems;
	
	public DockScreenHandler(Stage stage, String screenPath) throws IOException, SQLException {
		super(stage, screenPath);
		
	}
	
	@Override
	public void load() throws SQLException {
		setHController(new HomeController());
		
		loadAllItems();

		homeScreenHandler.getStage().setOnCloseRequest(e -> {
			System.out.println("State: " + state);
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
		
		
		logo.setOnMouseClicked(e -> {
			homeScreenHandler.requestToNewScreen(homeScreenHandler);
			try {
				homeScreenHandler.loadAllItems();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		homeBtn.setOnMouseClicked(e->{
			homeScreenHandler.requestToNewScreen(homeScreenHandler);
			try {
				homeScreenHandler.loadAllItems();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		exitBtn.setOnMouseClicked(e->{
			homeScreenHandler.requestToNewScreen(homeScreenHandler);
			try {
				homeScreenHandler.loadAllItems();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	// methods
	public void addBike(List bikes) {
		ArrayList bikeList = (ArrayList)((ArrayList) bikes).clone();
		while (!bikeList.isEmpty()) {
			BikeHandler bike = (BikeHandler) bikeList.get(0);
			flowPaneBike.getChildren().add(bike.getContent());
			bikeList.remove(bike);
		}
	}
	
	@Override
	public void loadAllItems() throws SQLException {
		
		if (this.dockItems != null) {
			this.dockItems.clear();
			flowPaneBike.getChildren().clear();
		}
		
        List<Bike> bikeList = getHController().getAllBikesByDockId(dock.getId());
		
        try {	
        	
            this.dockItems = new ArrayList<>();
			
			for (Object object : bikeList) {
				
				Bike bike = (Bike) object;
				BikeHandler b1 = new BikeHandler(Configs.DOCK_BIKE_PATH);
				b1.setDockScreenHandler(this);
				b1.setState(state);
				b1.setHomeScreenHandler(homeScreenHandler);
				b1.setAccount(account);
				b1.setBike(bike);
				b1.load();
				this.dockItems.add(b1);
			}
		} catch (SQLException | IOException e) {
			LOGGER.info("Errors occured: " + e.getMessage());
			e.printStackTrace();
		}
        
        addBike(this.dockItems);
        setInfo();
	}

	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Dock Screen");
		show();
	}
	
	@Override 
	public void setInfo() throws SQLException {
		Dock dock1 = getHController().getDockById(dock.getId());
		System.out.println(dock1.getAvailableBikes() - dock.getAvailableBikes());
		dockName.setText(dock1.getName());
		dockAddress.setText(dock1.getAddress());
		dockAvailBikes.setText(Integer.toString(dock1.getAvailableBikes()));
		dockEmptySlots.setText(Integer.toString(dock1.getEmptySlots()));
		
	}
	// getter and setter
	
	public Stage getStage() {
		return homeScreenHandler.getStage();
	}


	
	
	
	
}
