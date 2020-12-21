package views.screen;

import java.io.IOException;
import java.util.Hashtable;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.dock.DockScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.rentBike.RentBikeScreenHandler;

public class BaseScreenHandler extends FXMLScreenHandler {
	
	private Scene scene;
	private BaseScreenHandler prev;
	protected Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected DockScreenHandler dockScreenHandler;
	protected RentBikeScreenHandler rentBikeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseController bController;
	
	protected BaseScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}
	
	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}
	
	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}
	
	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
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
	
	public void setBController(BaseController bController) {
		this.bController = bController;
	}
	
	public BaseController getBController() {
		return this.bController;
	}
	
	public void forward(Hashtable messages) {
		this.messages = messages;
	}
	
	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}
	
	public void setDockScreenHandler(DockScreenHandler DockScreenHandler) {
		this.dockScreenHandler = DockScreenHandler;
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public void requestToNewScreen(BaseScreenHandler prevScreen, String title) {
		setPreviousScreen(prevScreen);
		setScreenTitle(title);
		show();		
	}
}
