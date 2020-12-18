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
import utils.Configs;
import utils.Utils;
import controller.RentBikeController;
import controller.ViewDockController;
import entity.bike.BikeType;
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
	
	
	private List homeItems;

	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}
	
	public Stage getStage() {
		//System.out.println(this.stage.toString());
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
		
		try {
			List dockList = getBController().getAllDocks();
			this.homeItems = new ArrayList<>();
			int count = 0;
			for (Object object : dockList) {
				Dock dock = (Dock) object;
				DockHandler d1 = new DockHandler(Configs.DOCK_HOME_PATH, dock, this);
				this.homeItems.add(d1);
			}
		} catch (SQLException | IOException e) {
			LOGGER.info("Errors occured: " + e.getMessage());
			e.printStackTrace();
		}
		
		rentBikeBtn.setOnMouseClicked(e -> {

			BikeInfoHandler bikeInfoScreen;
			try {
				BikeType bike = getBController().getBikeByBarcode(barcode.getText());
				if (bike == null) {
					System.out.println("This barcode does not exist");
				} else {
					LOGGER.info("User entered barcode and clicked rent bike");
					
					bikeInfoScreen = new BikeInfoHandler(this.stage, Configs.BIKE_INFO_PATH, bike);
					bikeInfoScreen.setHomeScreenHandler(this);
					bikeInfoScreen.requestToViewBikeInfo(this);
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
				System.out.println(search.getText());
				this.homeItems = new ArrayList<>();
				int count = 0;
				for (Object object : dockList) {
					Dock dock = (Dock) object;
					DockHandler d1 = new DockHandler(Configs.DOCK_HOME_PATH, dock, this);
					this.homeItems.add(d1);
				}
			} catch (SQLException | IOException e1) {
				LOGGER.info("Errors occured: " + e1.getMessage());
				e1.printStackTrace();
			}
			addDockHome(this.homeItems);
		});
		addDockHome(this.homeItems);
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

}
