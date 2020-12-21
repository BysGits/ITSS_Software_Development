package views.screen.popup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.HomeController;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.rent.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

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
	private ChoiceBox<String> dockChoice;
	
	@FXML
	private Button confirmBtn;
	
	@FXML
	private TextField returnDock;
	
	public HomeController getBController() {
		return (HomeController) super.getBController();
	}
	
	private Rent rent;
	public ReturnPopUpHandler(Stage stage, String screenPath, Rent rent) throws IOException, SQLException {
		super(stage, screenPath);
		this.rent = rent;
		
		setBController(new HomeController());
		
		List dockList = getBController().getAllDocks();
		
		for (Object object : dockList) {
			Dock dock = (Dock) object;
			
			dockChoice.getItems().add(dock.getName());
		}
		
		dockChoice.setAccessibleText("Choose dock");
		setInfo();
		
		confirmBtn.setOnMouseClicked(e -> {
			try {
				
//				getBController().returnBike(rent, dockChoice.getSelectionModel().getSelectedItem());
				System.out.println(returnDock.getText());
				Dock dock = Dock.getDockByName(returnDock.getText());
				System.out.println(dock.getId());
				getBController().setNewDock(rent, dock.getId());
				getBController().returnBike(rent, dock.getId());
				
				getBController().createInvoice(rent);
				homeScreenHandler.loadAllDocks();
				homeScreenHandler.getRentBikeBtn().setDisable(false);
				homeScreenHandler.getReturnBikeBtn().setDisable(true);
				homeScreenHandler.getViewBikeBtn().setDisable(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.stage.close();
			
		});
	}

	
	public void setInfo() {
		Bike bike = this.rent.getBike();
		this.rent.setRentTime((int) (System.currentTimeMillis() - this.rent.getStart())/1000);
		System.out.println(this.rent.getStart());
		System.out.println(System.currentTimeMillis());
		

		barcode.setText(bike.getBarcode());
		type.setText(bike.getType());
		
		fee.setText(Double.toString(rent.calculateFee()));
		time.setText(Integer.toString(this.rent.getRentTime()));
	}
	
	
}
