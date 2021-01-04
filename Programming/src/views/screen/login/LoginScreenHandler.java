package views.screen.login;

import java.io.IOException;
import java.sql.SQLException;

import controller.HomeController;
import entity.account.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

public class LoginScreenHandler extends BaseScreenHandler {

	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button signInBtn;
	
	public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
		setHController(new HomeController());
		
		signInBtn.setOnMouseClicked(e -> {
			BaseScreenHandler homeHandler;
			try {
				String username = this.username.getText();
				String password = this.password.getText();
				if (getHController().checkIfAccountExists(username, password)) {

					Account account = getHController().getAccount(username, password);
					
					if (account.isUsing()) {
						PopupScreen.error("This account is already logged in!!");
					} else {
						getHController().updateUsingState(account, true);
						homeHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
						homeHandler.setAccount(account);
						homeHandler.setState(0);
						homeHandler.requestToNewScreen(this);
						homeHandler.load();
					}
				} else {
					PopupScreen.error("Username or password is wrong");
				}

			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			} 
			
			
		});
		
//		signUpBtn.setOnMouseClicked(e -> {
//			SignUpScreenHandler signUpHandler;
//			
//			try {
//				signUpHandler = new SignUpScreenHandler(this.stage, Configs.SIGNUP_PATH);
//				signUpHandler.requestToNewScreen(this, "Sign-up Screen");
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			
//		});
	}
	
	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		username.setText("");
		password.setText("");
		setPreviousScreen(prevScreen);
		setScreenTitle("Login Screen");
		show();
	}

}
