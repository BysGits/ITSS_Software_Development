package views.screen.card;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

import controller.HomeController;
import controller.RentBikeController;
import entity.account.Account;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.payment.CreditCard;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.dock.BikeHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

public class CardScreenHandler extends BaseScreenHandler {

	public static Logger LOGGER = Utils.getLogger(CardScreenHandler.class.getName());
	
	// JavaFX Components
	@FXML 
	private ImageView logo;
	
	@FXML
	private Button confirmBtn;
	
	@FXML
	private TextField cardCode;
	
	@FXML
	private TextField cvvCode;
	
	@FXML
	private TextField owner;
	
	@FXML
	private TextField dateExpired;
	
	@FXML
	private Button cancelBtn;
	
	// variables
	
	
	
	public CardScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}
	
	@Override
	public void load() {

		RentBikeController hController = new RentBikeController();
		
		logo.setOnMouseClicked(e -> this.getPreviousScreen().show());
		
	    cardCode.setText(account.getCardCode());
	    owner.setText(account.getOwner());
	
		
	    confirmBtn.setOnMouseClicked(e -> {
		
			try {
				String code = cardCode.getText();
				String ownerInput = owner.getText();
				String cvvInput = cvvCode.getText();
				String dateInput = dateExpired.getText();
				CreditCard card = hController.CreditCard(code, ownerInput, cvvInput, dateInput);
				if (hController.checkIfBikeisAvailable(bike)) {
					switch((hController).validateCreditCard(card, bike.getDepositFee())) {
					case 1: 
						PopupScreen.error("INVALID CARD");
						break;
					case 2: 
						PopupScreen.error("NOT ENOUGH BALANCE");
						break;
					default:
						PopupScreen.success("RENT BIKE SUCCESSFUL");
						Rent rent = hController.newRent(bike);
						hController.updateBikeState(rent, 1);
						hController.updateDockAfterRent(rent);
						hController.updateRentingState(bike, false);
	
						
						// Create new card instance
						homeScreenHandler.setState(1);
						homeScreenHandler.setCard(card);
						homeScreenHandler.getRenting(rent);
						this.stage.close();
						homeScreenHandler.requestToNewScreen(homeScreenHandler);
						homeScreenHandler.loadAllItems();
						homeScreenHandler.refreshButtons();
						break;
					}
				} else {
					PopupScreen.error("This bike has been rented by another user.");
				}	
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NumberFormatException e2) {
				try {
					PopupScreen.error("Please input every field before confirming!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		cancelBtn.setOnMouseClicked(e -> {
			this.stage.close();
		});
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Card Screen");
		show();
	}
	
	// getter and setter
	public RentBikeController getHController() {
		return (RentBikeController) super.getHController();
	}
	
}
