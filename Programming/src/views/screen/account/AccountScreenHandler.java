package views.screen.account;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.account.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class AccountScreenHandler extends BaseScreenHandler {

	@FXML
	private Label username;
	
	@FXML
	private Label owner;
	
	@FXML
	private Label age;
	
	@FXML
	private Label gender;
	
	@FXML
	private TextField usernameTF;
		
	@FXML
	private TextField ownerTF;
	
	@FXML
	private TextField ageTF;
	
	@FXML
	private TextField genderTF;
	
	@FXML
	private GridPane showPane;
	
	@FXML
	private GridPane editPane;
	
	@FXML
	private Button editBtn;
	
	@FXML
	private Button doneBtn;
	
	public AccountScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		
		editBtn.setOnMouseClicked(e -> {
			showPane.setVisible(false);
			editPane.setVisible(true);
			doneBtn.setVisible(true);
			doneBtn.setDisable(false);
			editBtn.setDisable(true);
			editBtn.setVisible(false);	
		});
		
		doneBtn.setOnMouseClicked(e -> {	
			this.stage.close();
			showPane.setVisible(true);
			editPane.setVisible(false);
			doneBtn.setVisible(false);
			editBtn.setDisable(false);
			doneBtn.setDisable(true);
		});
		
		setInfo();
	}

	@Override
	public void requestToNewScreen(BaseScreenHandler prevScreen) {
		setPreviousScreen(prevScreen);
		setScreenTitle("Account Screen");
		show();
	}
	
	@Override
	public void setInfo() {
		
		username.setText(this.account.getUsername());
		owner.setText(this.account.getOwner());
		age.setText(this.account.getAge());
		gender.setText(this.account.getGender());
	}
}
