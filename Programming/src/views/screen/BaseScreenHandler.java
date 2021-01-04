package views.screen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.HomeController;
import entity.account.Account;
import entity.payment.CreditCard;
import entity.rent.Rent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.dock.DockScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.rentBike.ViewRentingBikeHandler;
import entity.dock.Dock;
import entity.bike.Bike;

public class BaseScreenHandler extends FXMLScreenHandler {
	
	private Scene scene;
	private BaseScreenHandler prev;
	protected Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected DockScreenHandler dockScreenHandler;
	protected ViewRentingBikeHandler rentBikeScreenHandler;
	protected Hashtable<String, String> messages;
	private HomeController hController;
	
	// variables
	protected Rent rent;
	protected CreditCard card;
	protected int state;
	protected Map<String, String> response;
	protected String transactionId;
	protected Account account;
	protected Dock dock;
	protected Bike bike;
	
	protected BaseScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}
	
	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}
	
	
	// Getter and Setter
	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}
	
	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}
	
	public void setHController(HomeController hController) {
		this.hController = hController;
	}
	
	public HomeController getHController() {
		return this.hController;
	}
	
	public HomeScreenHandler getHomeScreenHandler() {
		return homeScreenHandler;
	}
	
	public DockScreenHandler getDockScreenHandler() {
		return dockScreenHandler;
	}
	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}
	
	public void setDockScreenHandler(DockScreenHandler DockScreenHandler) {
		this.dockScreenHandler = DockScreenHandler;
	}
	
	public void setDock(Dock dock) {
		this.dock = dock;
	}
	
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	public BaseScreenHandler setAccount(Account account ) {
		this.account = account;
		return this;
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		
		this.stage.setScene(this.scene);
		this.stage.show();
	}
	
	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}
	
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		show();		
	}
	
	public void setInfo() throws SQLException {
		
	}
	
	// getter and setter
	public Map<String, String> getResponse() {
		return response;
	}

	public void setResponse(Map<String, String> response) {
		this.response = response;
	}
	
	public CreditCard getCard() {
		return card;
	}
	
	public void setCard(CreditCard card) {
		this.card = card;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void getRenting(Rent rent) {
		this.rent = rent;
	}

	public Account getAccount() {
		return account;
	}
	
	public void load() throws SQLException {
		
	}
	
	public void setRent(Rent rent) {
		this.rent = rent;
	}
	
	public void loadAllItems() throws SQLException {
		
	}
	
	
	
	
}
