package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import entity.rent.Rent;

import controller.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeInfoHandler;
import views.screen.dock.DockScreenHandler;
import views.screen.history.HistoryScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;
import views.screen.popup.ReturnPopUpHandler;
import utils.Configs;
import utils.Utils;
import controller.RentBikeController;
import controller.ViewDockController;
import entity.bike.Bike;
import entity.dock.Dock;
import views.screen.rentBike.RentBikeScreenHandler;


public class HomeScreenHandler extends BaseScreenHandler implements Initializable {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
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
	
	private Rent rent;
	

	public Button getViewBikeBtn() {
		return this.viewBikeBtn;
	}
	
	public Button getReturnBikeBtn() {
		return this.returnBikeBtn;
	}
	
	public Button getRentBikeBtn() {
		return this.rentBikeBtn;
	}
	
	public void getRenting(Rent rent) {
		this.rent = rent;
	}
	
	public List homeItems;

	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public HomeScreenHandler getHomeScreenHandler() {
		return this;
	}
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new HomeController());
		
		
		loadAllDocks();
		
		viewBikeBtn.setOnMouseClicked(e -> {
			RentBikeScreenHandler rentBikeScreen;
			
			try {
				LOGGER.info("User clicked to view renting bike's info and his/her renting");
				
				rentBikeScreen = new RentBikeScreenHandler(this.stage, Configs.VIEW_RENTING_BIKE_PATH, rent);
				rentBikeScreen.setHomeScreenHandler(this);
				rentBikeScreen.requestToNewScreen(this, "Rent Information Screen");
			} catch(IOException e1) {
				e1.printStackTrace();
			}
		});
		
		returnBikeBtn.setOnMouseClicked(e -> {
//			InvoiceScreenHandler invoiceScreen;
//			
//			try {
//				invoiceScreen = new InvoiceScreenHandler(this.stage, Configs.INVOICE_PATH, rent);
//				invoiceScreen.setHomeScreenHandler(this);
//				invoiceScreen.requestToViewInvoice(this);
//				
//			} catch (SQLException | IOException e1) {
//				e1.printStackTrace();
//			}
			ReturnPopUpHandler returnPopUp;
			
			try {
				returnPopUp = new ReturnPopUpHandler(new Stage(), Configs.RETURN_POPUP_PATH, rent);
				returnPopUp.setHomeScreenHandler(this);
				returnPopUp.requestToNewScreen(this, "Check again?");
			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		rentBikeBtn.setOnMouseClicked(e -> {
			
			BikeInfoHandler bikeInfoScreen;
			try {
				Bike bike = getBController().getBikeByBarcode(barcode.getText());
				if (bike == null) {
					System.out.println("This barcode does not exist");
				} else {
					LOGGER.info("User entered barcode and clicked rent bike");
					
					bikeInfoScreen = new BikeInfoHandler(this.stage, Configs.BIKE_INFO_PATH, bike, this);
					bikeInfoScreen.setHomeScreenHandler(this);
					bikeInfoScreen.requestToNewScreen(this, "Bike Detail Screen");
				}
				
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		searchBtn.setOnMouseClicked(e -> {
			this.homeItems.clear();
			flowPaneDock.getChildren().clear();
			
			try {
				List dockList = getBController().searchDock(search.getText());
				this.homeItems = new ArrayList<>();
				int count = 0;
				for (Object object : dockList) {
					Dock dock = (Dock) object;
					DockHandler d1 = new DockHandler(this.stage, Configs.DOCK_HOME_PATH, dock, this);
					this.homeItems.add(d1);
				}
			} catch (SQLException | IOException e1) {
				LOGGER.info("Errors occured: " + e1.getMessage());
				e1.printStackTrace();
			}
			addDockHome(this.homeItems);
		});
		
		historyBtn.setOnMouseClicked(e -> {
			HistoryScreenHandler historyScreen;
			
			try {
				historyScreen = new HistoryScreenHandler(this.stage, Configs.HISTORY_PATH);
				historyScreen.setHomeScreenHandler(this);
				historyScreen.requestToNewScreen(this, "History Screen");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public void setImage() {
		// fix image path caused by fxml
		
	}

	public void addDockHome(List docks) {
		ArrayList dockList = (ArrayList)((ArrayList) docks).clone();

		while (!dockList.isEmpty()) {
			DockHandler dock = (DockHandler) dockList.get(0);
			flowPaneDock.getChildren().add(dock.getContent());
			dockList.remove(dock);
		}	
	}
	
	public void loadAllDocks() {
		try {
			if (this.homeItems != null) {
				this.homeItems.clear();
				flowPaneDock.getChildren().clear();
			}
			List dockList = getBController().getAllDocks();
			this.homeItems = new ArrayList<>();
			
			for (Object object : dockList) {
				Dock dock = (Dock) object;
				DockHandler d1 = new DockHandler(this.stage, Configs.DOCK_HOME_PATH, dock, this);
				d1.setHomeScreenHandler(this);
				this.homeItems.add(d1);
			}
		} catch (SQLException | IOException e) {
			LOGGER.info("Errors occured: " + e.getMessage());
			e.printStackTrace();
		}
		
		addDockHome(this.homeItems);
	}

}
