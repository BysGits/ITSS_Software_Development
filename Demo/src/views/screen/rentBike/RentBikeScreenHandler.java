package views.screen.rentBike;

import java.io.IOException;
import java.util.logging.LogManager;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.card.CardScreenHandler;
import views.screen.home.HomeScreenHandler;


public class RentBikeScreenHandler extends BaseScreenHandler {
	
	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

	@FXML 
	private Button enterBtn;
	
	@FXML
	private ImageView ecoImage;
	
	@FXML 
	private AnchorPane bikeInfoPane;
	
	@FXML 
	private Button rentBtn;
	
	public RentBikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		enterBtn.setOnMouseClicked(e -> {
			LOGGER.info("User has just entered barcode and the program shows the bike's info");
			bikeInfoPane.setVisible(true);
		});
		
		ecoImage.setOnMouseClicked(e -> {
			LOGGER.info("User clicked the logo to return the homepage");
			homeScreenHandler.show();
		});
		
		rentBtn.setOnMouseClicked(e -> {
			CardScreenHandler cardScreen;
			try {
				LOGGER.info("User clicked to rent bike and navigate to input card info");
				cardScreen = new CardScreenHandler(this.stage, Configs.CARD_PATH);
				//cardScreen.setHomeScreenHandler(this);
				//cardScreen.setBController(new CardScreenHandler());
				cardScreen.requestToCardScreen(this);
			} catch(IOException e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	public void requestToRentBike(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Rent Bike Screen");
		
		show();
	}

	//private static Logger LOGGER = Utils.getLogger()
	
}
