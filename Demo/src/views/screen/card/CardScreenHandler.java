package views.screen.card;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import controller.HomeController;
import controller.RentBikeController;
import entity.bike.Bike;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class CardScreenHandler extends BaseScreenHandler {

	@FXML 
	private ImageView logo;
	
	@FXML
	private Button confirmBtn;
	
	@FXML
	private TextField cardCode;
	
	@FXML
	private TextField owner;
	
	@FXML
	private TextField cvvCode;
	
	@FXML
	private TextField dateExpired;
	
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
		// - Enable the ViewBike button and ReturnBike button in home page
//		confirmBtn.setOnMouseClicked(e -> {
//			try {
//				
//				RentBikeController tempController = new RentBikeController();
//				switch(tempController.checkCreditCardInfo(cardCode.getText(), owner.getText(), Integer.parseInt(cvvCode.getText()), dateExpired.getText())) {
//					case 1:
//						System.out.println("Invalid card code");
//						break;
//					case 2:
//						//System.out.println(owner.g);
//						
//						System.out.println("Invalid owner");
//						break;
//					case 3:
//						System.out.println("Invalid card cvv");
//						break;
//					case 4:
//						System.out.println("Invalid date expired");
//						break;
//					default:
//						// Create new rent
//						System.out.println("OK!");
//						Rent rent = getBController().newRent(bike);
//						System.out.println(System.currentTimeMillis());
//						// Enable the ViewBike button and ReturnBike button in home page
//						home.getRenting(rent);
//						home.show();
//						home.getReturnBikeBtn().setDisable(false);
//						home.getViewBikeBtn().setDisable(false);
//						home.getRentBikeBtn().setDisable(true);
//						
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		});
		
		confirmBtn.setOnMouseClicked(e -> {
			Rent rent;
			try {
				rent = getBController().newRent(bike);
				System.out.println(System.currentTimeMillis());
				// Enable the ViewBike button and ReturnBike button in home page
				home.getRenting(rent);
				home.show();
				home.getReturnBikeBtn().setDisable(false);
				home.getViewBikeBtn().setDisable(false);
				home.getRentBikeBtn().setDisable(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
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
