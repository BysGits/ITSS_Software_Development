package views.screen.history;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class HistoryHandler extends BaseScreenHandler {
	
	public static Logger LOGGER = Utils.getLogger(HistoryHandler.class.getName());
	
	@FXML
	private Label rentalCode;
	
	@FXML
	private Label barcode;
	
	@FXML
	private Label time;
	
	@FXML
	private Label fee;
	
	@FXML
	private Label start;
	
	@FXML
	private Label end;
	
	private Invoice invoice;

	public HistoryHandler(String screenPath, Invoice invoice) throws IOException, SQLException {
		super(screenPath);	
		this.invoice = invoice;
	}
	
	@Override
	public void load() throws SQLException {
		setHController(new HomeController());
		setInfo();
	}
	
	@Override
	public void setInfo() throws SQLException {

		Bike bike = getHController().getBikeById(this.invoice.getBikeId());

		barcode.setText(bike.getBarcode());
		time.setText(Integer.toString(this.invoice.getTotalTime()));
		fee.setText(Integer.toString(this.invoice.getTotalAmount()));
		start.setText(getHController().getDockById(this.invoice.getStartDockId()).getName());
		end.setText(getHController().getDockById(this.invoice.getEndDockId()).getName());
		rentalCode.setText(this.invoice.getTransactionId().substring(0, 7).toUpperCase() + bike.getBarcode());
	}



}
