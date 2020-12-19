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
		
		dockChoice.setValue(Dock.getDockById(rent.getBike().getDockId()).getName());
		setInfo();
		
		confirmBtn.setOnMouseClicked(e -> {
			try {
				System.out.println(rent.getBike().getDockId());
				getBController().returnBike(rent, dockChoice.getSelectionModel().getSelectedItem());
				System.out.println(dockChoice.getSelectionModel().getSelectedItem());
				System.out.println(rent.getBike().getDockId());
				homeScreenHandler.loadAllDocks();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.stage.close();
			
		});
	}

	public void requestToPopUpReturnBike(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Return bike?");
		show();
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
