package views.screen.popup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.payment.CreditCard;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

public class ReturnPopUpHandler extends BaseScreenHandler {

	
	@FXML
	private Label barcode;
	
	@FXML
	private Label time;
	
	@FXML
	private Label type;
	
	@FXML
	private Label fee;
	
	@FXML
	private ComboBox<String> dockBox;
	
	@FXML
	private Button confirmBtn;
	
	@FXML
	private TextField returnDock;
	
	@FXML
	private Button backBtn;
	
	private Map<String, String> response;
	private Rent rent;

	
	public ReturnPopUpHandler(Stage stage, String screenPath, Rent rent) throws IOException, SQLException {
		super(stage, screenPath);
		this.rent = rent;
	}

	public boolean checkCardBalance() throws IOException {
		String contents = "pay rent";
		CreditCard card = homeScreenHandler.getCard();
		
		homeScreenHandler.setResponse(getHController().payRent(this.rent.getBike().getDepositFee(), getHController().calculateFee(this.rent), contents, card));
		this.response = homeScreenHandler.getResponse();
		if (this.response.get("RESULT_PAY").equals("PAYMENT FAILED!")) {
			PopupScreen.error(this.response.get("RESULT_PAY"));
			return false;
		}
		PopupScreen.success(this.response.get("RESULT_PAY"));
		return true;
	}
	
	@Override
	public void load() throws SQLException {
		
        setHController(new ReturnBikeController());
		
		ReturnBikeController hController = getHController();
		
		List<Dock> dockList = hController.getAllDocks();
		System.out.println(dockList.size());
		for (Object object : dockList) {
			Dock dock = (Dock) object;
			
			dockBox.getItems().add(dock.getName());
		}
		
		setInfo();
		
		backBtn.setOnMouseClicked(e-> this.stage.close());
		
		confirmBtn.setOnMouseClicked(e -> {
			try {
				
				if(dockBox.getValue() == null) {
					PopupScreen.error("Please choose a dock to return");
				} else {

					if(checkCardBalance()) {
						this.stage.close();
						Dock dock = hController.getDockByName(dockBox.getValue());
						rent.setStartDockId(rent.getBike().getDockId());
						
						
						if (hController.checkIfDockIsFree(dock.getId())) {
							rent.setEndDockId(dock.getId());
							rent.setCurrentFee(hController.calculateFee(rent));
							hController.updateDockAfterReturn(dock.getId());
							hController.updateBikeState(rent, dock.getId());
							hController.updateRentingState(rent.getBike(), true);

							hController.createInvoice(rent, this.response);
							InvoiceScreenHandler invoiceScreen = new InvoiceScreenHandler(homeScreenHandler.getStage(), Configs.INVOICE_PATH);
							invoiceScreen.setHomeScreenHandler(homeScreenHandler);
							invoiceScreen.setRent(rent);
							homeScreenHandler.setState(0);
							invoiceScreen.setState(0);
							invoiceScreen.requestToNewScreen(this);
							invoiceScreen.load();
							
						} else {
							ReturnFailPopUpHandler returnFailPopUp;
							Stage newStage = new Stage();
							returnFailPopUp = new ReturnFailPopUpHandler(newStage, Configs.POPUP_RETURN_FAIL_PATH);
							returnFailPopUp.requestToNewScreen(this);
						}
					}
				}
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
			
		});
	}
	
	@Override
	public void setInfo() {
		Bike bike = this.rent.getBike();
		
		this.rent.setRentTime((int) (System.currentTimeMillis() - this.rent.getStart())/1000);
		this.rent.setCurrentFee(getHController().calculateFee(this.rent));
		
		barcode.setText(bike.getBarcode());
		type.setText(bike.getType());
		fee.setText(Double.toString(this.rent.getCurrentFee()));
		time.setText(Integer.toString(this.rent.getRentTime()));
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Check again?");
		show();
	}
	
	
	// getter and setter
	public ReturnBikeController getHController() {
		return (ReturnBikeController) super.getHController();
	}
	
	
	
}
