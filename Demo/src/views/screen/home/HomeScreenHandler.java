package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import controller.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import utils.Configs;
import controller.RentBikeController;

import views.screen.rentBike.RentBikeScreenHandler;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable{
	
	@FXML 
	private Button rentBikeBtn;
	
	@FXML
	private Button returnBikeBtn;
	
	@FXML
	private Button viewBikeBtn;
	
	@FXML 
	private ImageView ecoImage;

	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new HomeController());
		
		rentBikeBtn.setOnMouseClicked(e -> {
			RentBikeScreenHandler rentBikeScreen;
			try {
				//Stage newStage = new Stage();
				rentBikeScreen = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_PATH);
				rentBikeScreen.setHomeScreenHandler(this);
				rentBikeScreen.setBController(new RentBikeController());
				rentBikeScreen.requestToRentBike(this);
				//this.stage.set
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public void setImage() {
		// fix image path caused by fxml
		
	}
	
}
