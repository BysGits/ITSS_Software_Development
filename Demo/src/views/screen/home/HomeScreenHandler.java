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
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.dock.DockScreenHandler;
import utils.Configs;
import utils.Utils;
import controller.RentBikeController;
import controller.ViewDockController;
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
			List dockList = getBController().getAllDock();
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
			RentBikeScreenHandler rentBikeScreen;
			try {
				LOGGER.info("User clicked to rent bike");
				Stage newStage = new Stage();
				rentBikeScreen = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_PATH);
				rentBikeScreen.setHomeScreenHandler(this);
				rentBikeScreen.setBController(new RentBikeController());
				rentBikeScreen.requestToRentBike(this);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
