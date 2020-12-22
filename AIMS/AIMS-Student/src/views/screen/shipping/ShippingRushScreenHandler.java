package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import common.exception.InvalidDeliveryInfoException;
import controller.PlaceRushOrderController;
import entity.invoice.Invoice;
import entity.order.RushOrder;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceRushScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;
import views.screen.popup.PopupScreen;

public class ShippingRushScreenHandler extends BaseScreenHandler implements Initializable {

	@FXML
	private Label screenTitleR;

	@FXML
	private TextField nameR;

	@FXML
	private TextField phoneR;

	@FXML
	private TextField addressR;

	@FXML
	private TextField instructionsR;

	@FXML
	private ComboBox<String> provinceR;
	
	@FXML
	private DatePicker dateR;
	
	@FXML
	private TextField timeR;
	
	private RushOrder rushOrder;
	
	public ShippingRushScreenHandler(Stage stage, String screenPath, RushOrder rushOrder) throws IOException {
		super(stage, screenPath);
		this.rushOrder = rushOrder;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		nameR.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                content.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
		this.provinceR.getItems().addAll(Configs.PROVINCES);
	}

	@FXML
	void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {
		
		if(provinceR.getValue() == null) {
			PopupScreen.error("Invalid City");
			return;
		}
		if(dateR.getValue() == null) {
			PopupScreen.error("Invalid Date");
			return;
		}

		// add info to messages
		HashMap messages = new HashMap<>();
		messages.put("nameR", nameR.getText());
		messages.put("phoneR", phoneR.getText());
		messages.put("addressR", addressR.getText());
		messages.put("dateR", dateR.getValue().toString());
		messages.put("timeR", timeR.getText());
		messages.put("instructionsR", instructionsR.getText());
		messages.put("provinceR", provinceR.getValue());
		try {
			// process and validate delivery info
			int check = getBController().processRushDeliveryInfo(messages);
			
			switch(check) {
			case 1:
				PopupScreen.error("Invalid Name");
				return;
			case 2:
				PopupScreen.error("Invalid Phone Number");
				return;
			case 3:
				PopupScreen.error("Invalid Address");
				return;
			case 4:
				PopupScreen.error("Invalid Time");
				return;
			default:
				break;
			}
		} catch (InvalidDeliveryInfoException e) {
			throw new InvalidDeliveryInfoException(e.getMessage());
		}
	
		// calculate shipping fees
		int shippingFees = getBController().calculateRushShippingFee(rushOrder);
		rushOrder.setShippingFees(shippingFees);
		rushOrder.setRushDeliveryInfo(messages);
		
		// create invoice screen
		Invoice invoice = getBController().createInvoice(rushOrder);
		BaseScreenHandler InvoiceScreenHandler = new InvoiceRushScreenHandler(this.stage, Configs.INVOICE_RUSH_SCREEN_PATH, invoice);
		InvoiceScreenHandler.setPreviousScreen(this);
		InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
		InvoiceScreenHandler.setScreenTitle("Invoice Rush Screen");
		InvoiceScreenHandler.setBController(getBController());
		InvoiceScreenHandler.show();
	}

	public PlaceRushOrderController getBController(){
		return (PlaceRushOrderController) super.getBController();
	}

	public void notifyError(){
		// TODO: implement later on if we need
	}

}
