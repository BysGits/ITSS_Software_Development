package views.screen.invoice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

import common.exception.ProcessInvoiceException;
import controller.PaymentController;
import entity.invoice.Invoice;
import entity.order.OrderMedia;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

public class InvoiceRushScreenHandler extends BaseScreenHandler {

	private static Logger LOGGER = Utils.getLogger(InvoiceRushScreenHandler.class.getName());

	@FXML
	private Label pageTitleR;

	@FXML
	private Label nameR;

	@FXML
	private Label phoneR;

	@FXML
	private Label provinceR;

	@FXML
	private Label addressR;
	
	@FXML
	private Label dateR;
	
	@FXML
	private Label timeR;

	@FXML
	private Label instructionsR;

	@FXML
	private Label subtotalR;

	@FXML
	private Label shippingFeesR;

	@FXML
	private Label totalR;

	@FXML
	private VBox vboxItemsR;
	
	

	private Invoice invoice;

	public InvoiceRushScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		this.invoice = invoice;
		setInvoiceInfo();
	}

	private void setInvoiceInfo(){
		HashMap<String, String> deliveryInfo = invoice.getRushOrder().getRushDeliveryInfo();
		nameR.setText(deliveryInfo.get("nameR"));
		provinceR.setText(deliveryInfo.get("provinceR"));
		instructionsR.setText(deliveryInfo.get("instructionsR"));
		addressR.setText(deliveryInfo.get("addressR"));
		phoneR.setText(deliveryInfo.get("phoneR"));
		dateR.setText(deliveryInfo.get("dateR"));
		timeR.setText(deliveryInfo.get("timeR"));
		subtotalR.setText(Utils.getCurrencyFormat(invoice.getRushOrder().getAmount()));
		shippingFeesR.setText(Utils.getCurrencyFormat(invoice.getRushOrder().getShippingFees()));
		int amount = invoice.getRushOrder().getAmount() + invoice.getRushOrder().getShippingFees();
		totalR.setText(Utils.getCurrencyFormat(amount));
		invoice.setAmount(amount);
		invoice.getRushOrder().getlstRushOrderMedia().forEach(rushOrderMedia -> {
			try {
				MediaInvoiceScreenHandler mis = new MediaInvoiceScreenHandler(Configs.INVOICE_MEDIA_SCREEN_PATH);
				mis.setOrderMedia((OrderMedia) rushOrderMedia);
				vboxItemsR.getChildren().add(mis.getContent());
			} catch (IOException | SQLException e) {
				System.err.println("errors: " + e.getMessage());
				throw new ProcessInvoiceException(e.getMessage());
			}
			
		});

	}

	@FXML
	void confirmRushInvoice(MouseEvent event) throws IOException {
		BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, invoice);
		paymentScreen.setBController(new PaymentController());
		paymentScreen.setPreviousScreen(this);
		paymentScreen.setHomeScreenHandler(homeScreenHandler);
		paymentScreen.setScreenTitle("Payment Screen");
		paymentScreen.show();
		LOGGER.info("Confirmed rush invoice");
	}
}
