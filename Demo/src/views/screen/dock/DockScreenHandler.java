package views.screen.dock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import entity.bike.BikeType;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class DockScreenHandler extends BaseScreenHandler implements Initializable {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
	
	@FXML
	private FlowPane flowPaneBike;
	
	@FXML
	private ImageView logo;
	
	private List dockItems;
	private Dock dock;
	private Stage stage;
	
	
	public Stage getStage() {
		return homeScreenHandler.getStage();
	}
	
	public DockScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException {
		super(stage, screenPath);
		this.dock = dock;
		
		
		setBController(new HomeController());
		
		try {
			List bikeList = getBController().getAllBikesByDockId(dock.getId());
            this.dockItems = new ArrayList<>();
			
			for (Object object : bikeList) {
				
				BikeType bike = (BikeType) object;
				BikeHandler b1 = new BikeHandler(Configs.DOCK_BIKE_PATH, bike, this);
				this.dockItems.add(b1);
			}
		} catch (SQLException | IOException e) {
			LOGGER.info("Errors occured: " + e.getMessage());
			e.printStackTrace();
		}
		
		logo.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
		
		addBike(this.dockItems);
	}
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
//	public void setDockScreenHandler(DockScreenHandler dockScreenHandler) {
//		
//	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		setBController(new HomeController());
//		
//		
//		try {
//			List bikeList = getBController().getAllBikesByDockId(1);
//			
//			this.dockItems = new ArrayList<>();
//			
//			for (Object object : bikeList) {
//				
//				BikeType bike = (BikeType) object;
//				BikeHandler b1 = new BikeHandler(Configs.DOCK_BIKE_PATH, bike, this);
//				this.dockItems.add(b1);
//			}
//			System.out.println(this.dockItems.size());
//		} catch (SQLException | IOException e) {
//			LOGGER.info("Errors occured: " + e.getMessage());
//			e.printStackTrace();
//		}
		
		//addBike(this.dockItems);
	}
	public void addBike(List bikes) {
		ArrayList bikeList = (ArrayList)((ArrayList) bikes).clone();
		while (!bikeList.isEmpty()) {
			BikeHandler bike = (BikeHandler) bikeList.get(0);
			flowPaneBike.getChildren().add(bike.getContent());
			bikeList.remove(bike);
		}
	}

	public void requestToViewDock(BaseScreenHandler prevScreen) {
		// TODO Auto-generated method stub
		setPreviousScreen(prevScreen);
		setScreenTitle("Dock Screen");
		
		show();
		
	}
	
}
