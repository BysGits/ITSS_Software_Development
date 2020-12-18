package views.screen.card;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import controller.HomeController;
import entity.bike.Bike;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class CardScreenHandler extends BaseScreenHandler {

	@FXML 
	private ImageView logo;
	
	@FXML
	private Button confirmBtn;
	
	private Bike bike;
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	public CardScreenHandler(Stage stage, String screenPath, Bike bike, HomeScreenHandler home) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		setBController(new HomeController());
		// TODO Auto-generated constructor stub
		logo.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
		
		
		// When click confirm button:
		// - Check the card's information
		// - Create new Rent object
		// - Enable the ViewBike button and ReturnBike button in homepage
		confirmBtn.setOnMouseClicked(e -> {
			try {
				// Create new rent
				Rent rent = getBController().newRent(bike);
				
				home.getRenting(rent);
				home.show();
				home.setReturnBikeBtnAble();
				home.setViewBikeBtnAble();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public void requestToCardScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Card Screen");
		show();
	}
	
	//public void requestToCardScreen()

	
}
