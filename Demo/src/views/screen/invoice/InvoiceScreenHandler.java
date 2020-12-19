package views.screen.invoice;

import java.io.IOException;
import java.sql.SQLException;

import controller.HomeController;
import entity.dock.Dock;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class InvoiceScreenHandler extends BaseScreenHandler{

	
	@FXML
	private ImageView logo;
	
	@FXML
	private Label barcode;
	
	@FXML
	private Label type;
	
	@FXML
	private Label feature;
	
	@FXML
	private Label startDock;
	
	@FXML
	private Label endDock;
	
	@FXML
	private Label time;
	
	@FXML
	private Label fee;
	
	@FXML
	private Button returnBtn;
	
	@FXML
	private Button backBtn;
	
	private Rent rent;
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	public InvoiceScreenHandler(Stage stage, String screenPath, Rent rent) throws IOException, SQLException {
		super(stage, screenPath);
		this.rent = rent;
		
		logo.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
		
		returnBtn.setOnMouseClicked(e -> {
			homeScreenHandler.show();
			homeScreenHandler.getRentBikeBtn().setDisable(false);
			homeScreenHandler.getReturnBikeBtn().setDisable(true);
			homeScreenHandler.getViewBikeBtn().setDisable(true);
		});
		
		backBtn.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
		
		setInvoiceInfo();
		
		
	}

	public void requestToViewInvoice(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Invoice Screen");
		
		show();
	}
	
	public void setInvoiceInfo() throws SQLException {
		barcode.setText(rent.getBike().getBarcode());
		type.setText(rent.getBike().getType());
		feature.setText(rent.getBike().getPedals() + " pedal, " + rent.getBike().getSaddles() + " saddle, " + rent.getBike().getRearSeats() + " rear seat");
		startDock.setText(Dock.getDockById(rent.getBike().getDockId()).getName());
		fee.setText(Double.toString(rent.getCurrentFee()));
		time.setText(Double.toString(rent.getRentTime()));
	}
	
}
