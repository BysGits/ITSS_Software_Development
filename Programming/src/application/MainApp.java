package application;
	
import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Configs;
import views.screen.login.LoginScreenHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class MainApp extends Application {
	
	@FXML
	ImageView logo;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// initialize the scene
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// load splash screen with fade in effect
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);
			
			// finish splash with fade out effect
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);
			
			// after fade in, start fade out
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});
			
			// after fade out, load actual content
//			fadeOut.setOnFinished((e) -> {
//				try {
//					LoginScreenHandler loginHandler = new LoginScreenHandler(primaryStage, Configs.HOME_PATH);
////					HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.LOGIN_PATH);
//					loginHandler.setScreenTitle("Home Screen");
//					primaryStage.setOnCloseRequest(e1 -> {
//						if (loginHandler.getState() == 1) {
//							e1.consume();
//							try {
//								PopupScreen.error("Please return bike before exiting");
//							} catch (IOException e2) {
//								e2.printStackTrace();
//							}
//						}
//					});
//					//homeHandler.setImage();
//					homeHandler.show();
//					
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			});
			fadeOut.setOnFinished((e) -> {
				try {
					LoginScreenHandler loginHandler = new LoginScreenHandler(primaryStage, Configs.LOGIN_PATH);
					loginHandler.setScreenTitle("Login Screen");
					loginHandler.show();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
