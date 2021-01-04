package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.account.AccountScreenHandler;
import views.screen.bike.BikeInfoHandler;
import views.screen.history.HistoryScreenHandler;
import views.screen.login.LoginScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.popup.ReturnPopUpHandler;
import utils.Configs;
import utils.Utils;
import entity.account.Account;
import entity.bike.Bike;
import entity.dock.Dock;
import views.screen.rentBike.ViewRentingBikeHandler;


public class HomeScreenHandler extends BaseScreenHandler {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	// JavaFX Components
	@FXML 
	private Button rentBikeBtn;
	
	@FXML
	private Button returnBikeBtn;
	
	@FXML
	private Button viewBikeBtn;
	
	@FXML 
	private ImageView ecoImage;
	
	@FXML
	private FlowPane flowPaneDock;
	
	@FXML
	private TextField barcode;
	
	@FXML
	private TextField search;
	
	@FXML
	private Button searchBtn;
	
	@FXML
	private Button historyBtn;
	
	@FXML
	private MenuItem accountBtn;
	
	@FXML
	private MenuItem signOut;
	
	
	// Variables
	private List<DockHandler> homeItems;
	
	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		setHomeScreenHandler(this);
	}
	
	@Override
	public void load() throws SQLException {
		
		setHController(new HomeController());
		
		loadAllItems();
		
		ecoImage.setOnMouseClicked(e -> {
			try {
				loadAllItems();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		});
		
		viewBikeBtn.setOnMouseClicked(e -> {
			try {
				loadViewRentingBikeScreen();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		returnBikeBtn.setOnMouseClicked(e -> loadReturnBikeScreen());
		
		rentBikeBtn.setOnMouseClicked(e -> {

			try {
				//getHController: goi ra home controller cua class nay
				Bike bike = getHController().getBikeByBarcode(barcode.getText());
				
				if (bike == null) {
					PopupScreen.error("Invalid barcode");
				} else if (!getHController().checkIfBikeisAvailable(bike)) {
					PopupScreen.error("This bike has been rented by another user");
				} else loadBikeInfoScreen(bike);
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		searchBtn.setOnMouseClicked(e -> showSearchResult());
		
		historyBtn.setOnMouseClicked(e -> {
			try {
				loadHistoryScreen();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		accountBtn.setOnAction(e -> loadAccountScreen());
		
		signOut.setOnAction(e -> {
			try {
				getHController().updateUsingState(account, false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			getPreviousScreen().requestToNewScreen(getPreviousScreen());
		});
	}

	
	// Methods
	public void addDockHome(List docks) {
		ArrayList dockList = (ArrayList)((ArrayList) docks).clone();

		while (!dockList.isEmpty()) {
			DockHandler dock = (DockHandler) dockList.get(0);
			flowPaneDock.getChildren().add(dock.getContent());
			dockList.remove(dock);
		}	
	}
	
	@Override
	public void loadAllItems() throws SQLException {
		
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
		
		try {
			if (this.homeItems != null) {
				this.homeItems.clear();
				flowPaneDock.getChildren().clear();
			}
			List<Dock> dockList = getHController().getAllDocks();
			this.homeItems = new ArrayList<>();
			
			for (Object object : dockList) {
				Dock dock = (Dock) object;
				DockHandler d1 = new DockHandler(this.stage, Configs.DOCK_HOME_PATH);
				d1.setAccount(account);
				d1.setState(state);
				d1.setHomeScreenHandler(this);
				d1.setDock(dock);
				d1.load();
				this.homeItems.add(d1);
			}
		} catch (SQLException | IOException e) {
			LOGGER.info("Errors occured: " + e.getMessage());
			e.printStackTrace();
		}
		
		addDockHome(this.homeItems);
		setInfo();
	}
	
	private void loadViewRentingBikeScreen() throws SQLException {
		BaseScreenHandler viewRentingBikeScreen;
		
		try {
			LOGGER.info("User clicked to view renting bike's info and his/her renting");
			
			viewRentingBikeScreen = new ViewRentingBikeHandler(this.stage, Configs.VIEW_RENTING_BIKE_PATH, rent);
			viewRentingBikeScreen.setHomeScreenHandler(this);
			viewRentingBikeScreen.setState(state);
			viewRentingBikeScreen.requestToNewScreen(this);
			viewRentingBikeScreen.load();
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void loadReturnBikeScreen() {
		BaseScreenHandler returnPopUp;
		
		try {
			returnPopUp = new ReturnPopUpHandler(new Stage(), Configs.RETURN_POPUP_PATH, rent);
			returnPopUp.setAccount(account);
			returnPopUp.setHomeScreenHandler(this);
			returnPopUp.setState(state);
			returnPopUp.requestToNewScreen(this);
			returnPopUp.load();
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void showSearchResult() {
		this.homeItems.clear();
		flowPaneDock.getChildren().clear();
		
		try {
			List<Dock> dockList = getHController().searchDock(search.getText());
			this.homeItems = new ArrayList<>();

			for (Object object : dockList) {
				Dock dock = (Dock) object;
				
				DockHandler d1 = new DockHandler(this.stage, Configs.DOCK_HOME_PATH);
				d1.setHomeScreenHandler(this);
				d1.setState(state);
				d1.setAccount(account);
				d1.setDock(dock);
				d1.load();
				this.homeItems.add(d1);
			}
		} catch (SQLException | IOException e1) {
			LOGGER.info("Errors occured: " + e1.getMessage());
			e1.printStackTrace();
		}
		addDockHome(this.homeItems);
	}
	
	private void loadBikeInfoScreen(Bike bike) {
		BaseScreenHandler bikeInfoScreen;
		try {
				LOGGER.info("User entered barcode and clicked rent bike");
				
				bikeInfoScreen = new BikeInfoHandler(this.stage, Configs.BIKE_INFO_PATH);
				//set trong java, ke thua tu BaseScreenHandler
				bikeInfoScreen.setBike(bike);
				bikeInfoScreen.setState(state);
				bikeInfoScreen.setHomeScreenHandler(this);
				bikeInfoScreen.setAccount(account);
				bikeInfoScreen.requestToNewScreen(this);
				bikeInfoScreen.load();
			
			
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void loadHistoryScreen() throws SQLException {
		BaseScreenHandler historyScreen;
		
		try {
			historyScreen = new HistoryScreenHandler(this.stage, Configs.HISTORY_PATH);
			historyScreen.setAccount(account);
			historyScreen.setState(state);
			historyScreen.setHomeScreenHandler(this);
			historyScreen.requestToNewScreen(this);
			historyScreen.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void loadAccountScreen() {
		try {
			BaseScreenHandler accountHandler = new AccountScreenHandler(new Stage(), Configs.ACCOUNT_PATH);
			accountHandler.setAccount(account);
			accountHandler.requestToNewScreen(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Home Screen");
		show();
	}
	
	public void refreshButtons() {
		if (state == 0) {
			getRentBikeBtn().setDisable(false);
			getReturnBikeBtn().setDisable(true);
			getViewBikeBtn().setDisable(true);
		} else {
			getRentBikeBtn().setDisable(true);
			getReturnBikeBtn().setDisable(false);
			getViewBikeBtn().setDisable(false);
		}
	}
	// Getters and Setters
	public Button getViewBikeBtn() {
		return this.viewBikeBtn;
	}
	
	public Button getReturnBikeBtn() {
		return this.returnBikeBtn;
	}
	
	public Button getRentBikeBtn() {
		return this.rentBikeBtn;
	}
	
	public Stage getStage() {
		return this.stage;
	}



	



	
}
