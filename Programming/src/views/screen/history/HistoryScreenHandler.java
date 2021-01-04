package views.screen.history;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.HomeController;
import entity.account.Account;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;


public class HistoryScreenHandler extends BaseScreenHandler {

	
	@FXML
	private Button homeBtn;
	
	@FXML
	private FlowPane vboxInvoice;
	
	private List<HistoryHandler> historyItems;
	
	public HistoryScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);	
	}
	
	@Override
	public void load() {
		
        setHController(new HomeController());
		
    	homeScreenHandler.getStage().setOnCloseRequest(e -> {
			System.out.println("State: " + state);
			try {
				if (state == 0) {
					getHController().updateUsingState(account, false);	
				} else {
					PopupScreen.warning("Please return bike before exiting");
					e.consume();
				}
			} catch (IOException | SQLException  e1) {
				e1.printStackTrace();
			}
		});
    	
		homeBtn.setOnMouseClicked(e -> {
			homeScreenHandler.requestToNewScreen(homeScreenHandler);
			try {
				homeScreenHandler.loadAllItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		try {
			List<Invoice> invoiceList = getHController().getAllInvoices(account);
			this.historyItems = new ArrayList<>();
			
			System.out.println(invoiceList.size());
            for (Object object : invoiceList) {
				
				Invoice invoice = (Invoice) object;
				HistoryHandler h1 = new HistoryHandler(Configs.HISTORY_ITEM_PATH, invoice);
				h1.load();
				this.historyItems.add(h1);
			} 
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		addInvoice(this.historyItems);
	}
	
	public void addInvoice(List invoices) {
		ArrayList invoiceList = (ArrayList)((ArrayList) invoices).clone();
		
		while (!invoiceList.isEmpty()) {
			HistoryHandler invoice = (HistoryHandler) invoiceList.get(0);
			vboxInvoice.getChildren().add(invoice.getContent());
			invoiceList.remove(invoice);
		}
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		System.out.println(homeScreenHandler + " CHECK");
		setPreviousScreen(prevScreen);
		setScreenTitle("History Screen");
		setHomeScreenHandler(homeScreenHandler);
		setAccount(account);
		show();
	}
	
	
	
	

}
