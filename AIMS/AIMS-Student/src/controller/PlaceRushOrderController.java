package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import entity.order.RushOrder;
import controller.*;

public class PlaceRushOrderController extends BaseController{
	
	private PlaceOrderController poc;

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeRushOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public RushOrder createRushOrder() throws SQLException{
        RushOrder rushOrder = new RushOrder();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            rushOrder.getlstRushOrderMedia().add(orderMedia);
        }
        return rushOrder;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(RushOrder rushOrder) {
        return new Invoice(rushOrder);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public int processRushDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Rush Delivery Info");
        LOGGER.info(info.toString());
        return validateRushDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public int validateRushDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	if(!validateName(info.get("nameR"))) return 1;
    	else if(!validatePhoneNumber(info.get("phoneR"))) return 2;
    	else if(!validateAddress(info.get("addressR"))) return 3;
    	else if(!validateTime(info.get("timeR"))) return 4;
    	else return 0;
    }
    
    /**
     * The method validates the phone number
     * @param phoneNumber
     * @return boolean
     */
    public boolean validatePhoneNumber(String phoneNumber) {
    	if(phoneNumber.length() != 10) return false;
    	if(!phoneNumber.startsWith("0")) return false;
    	try {
    		Integer.parseInt(phoneNumber);
    		
    	}catch(NumberFormatException e) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * The method validates the name
     * @param name
     * @return boolean
     */
    public boolean validateName(String name) {
    	String regx = "^[\\p{L} .'-]+$";
    	try {
	        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(name);
	        return matcher.find();
    	}catch(Exception e) {
    		return false;
    	}
    }
    
    /**
     * The method validates the address
     * @param address
     * @return boolean
     */
    public boolean validateAddress(String address) {
    	if(address.equals("")) return false;
		for(int i=0 ; i<address.length(); i++) {
			if(!Character.isLetter(address.charAt(i)))
				return false;
		}
		return true;
    	
    }
    
    
    public boolean validateTime(String time) {
    	String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]"; 
        Pattern p = Pattern.compile(regex); 
  
        // If the time is empty 
        // return false 
        if (time == null) { 
            return false; 
        } 
  
        // Pattern class contains matcher() method 
        // to find matching between given time 
        // and regular expression. 
        Matcher m = p.matcher(time); 
  
        // Return if the time 
        // matched the ReGex 
        return m.matches(); 
    }
    

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateRushShippingFee(RushOrder rushOrder){
    	if(rushOrder.getAmount() > 100000) return 0;
    	Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * rushOrder.getAmount() );
        fees= (int)(fees*1.69);
        LOGGER.info("Order Amount: " + rushOrder.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
