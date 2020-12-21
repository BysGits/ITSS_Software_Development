package utils;

public class Configs {

	
	// static resource
	public static final String HOME_PATH = "/views/fxml/home_1.fxml";
	public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
	public static final String CARD_PATH = "/views/fxml/card_1.fxml";
	public static final String DOCK_HOME_PATH = "/views/fxml/dock_item.fxml";
	public static final String DOCK_PATH = "/views/fxml/dock_detail.fxml";
	public static final String DOCK_BIKE_PATH = "/views/fxml/dock_bike_1.fxml";
	public static final String BIKE_INFO_PATH = "/views/fxml/bike_detail.fxml";
	public static final String VIEW_RENTING_BIKE_PATH = "/views/fxml/view_renting_bike.fxml";
	public static final String HISTORY_PATH = "/views/fxml/history_1.fxml";
	public static final String RETURN_POPUP_PATH = "/views/fxml/return_popup.fxml";
	public static final String HISTORY_ITEM_PATH = "/views/fxml/history_item.fxml";
	
	public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
	public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

	// demo data
	public static final String POST_DATA = "{"
			+ " \"secretKey\": \"BpW3BCBzuRo=\" ,"
			+ " \"transaction\": {"
			+ " \"command\": \"pay\" ,"
			+ " \"cardCode\": \"121319_group2_2020\" ,"
			+ " \"owner\": \"Group 2\" ,"
			+ " \"cvvCode\": \"228\" ,"
			+ " \"dateExpried\": \"1125\" ,"
			+ " \"transactionContent\": \" Pay money\" ,"
			+ " \"amount\": 1000000 "
			+ "}"
		+ "}";
	public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

	// database Configs
	public static final String DB_NAME = "ecobike";
	public static final String DB_USERNAME = System.getenv("DB_USERNAME");
	public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

	public static String CURRENCY = "VND";
	public static float PERCENT_VAT = 10;
	public static final String INVOICE_PATH = "/views/fxml/invoice.fxml";
}
