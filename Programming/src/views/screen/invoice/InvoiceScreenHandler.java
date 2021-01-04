package views.screen.invoice;

import java.io.IOException;
import java.sql.SQLException;

import controller.HomeController;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

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
	private Button homeBtn;
	
	public InvoiceScreenHandler(Stage stage, String screenPath) throws IOException, SQLException {
		super(stage, screenPath);
	}
	
	@Override
	public void load() throws SQLException {
		
		setHController(new HomeController());
		
		logo.setOnMouseClicked(e -> {
			
			homeScreenHandler.requestToNewScreen(homeScreenHandler);
			homeScreenHandler.refreshButtons();
			try {
				homeScreenHandler.loadAllItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		homeBtn.setOnMouseClicked(e -> {
			
			homeScreenHandler.requestToNewScreen(homeScreenHandler);
			homeScreenHandler.refreshButtons();
			try {
				homeScreenHandler.loadAllItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		setInfo();
	}
	
	@Override
	public void setInfo() throws SQLException {
		Bike bike = rent.getBike();
		Dock startDock1 = getHController().getDockById(rent.getStartDockId());
		Dock endDock1 = getHController().getDockById(rent.getEndDockId());
		
		
		barcode.setText(bike.getBarcode());
		type.setText(bike.getType());
		feature.setText(bike.getFeatures());
		startDock.setText(startDock1.getName());
		endDock.setText(endDock1.getName());
		fee.setText(Double.toString(rent.getCurrentFee()));
		time.setText(Double.toString(rent.getRentTime()));
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Invoice Screen");
		show();
	}
	
}
