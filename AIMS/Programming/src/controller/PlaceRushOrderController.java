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

public class PlaceRushOrderController {
	
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
    public RushOrder createOrder() throws SQLException{
        RushOrder order = new RushOrder();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processRushDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Rush Delivery Info");
        LOGGER.info(info.toString());
        validateRushDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateRushDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	
    }
    
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
    
    public boolean validateAddress(String address) {
    	return address.matches("[\\p{Punct}&&[#,.()-]]+\\d*+\\s?+[\\p{Alpha}+\\s?]*" );
    }
    

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
    	poc = new PlaceOrderController();
    	int rand = poc.calculateShippingFee(order);
        //Random rand = new Random();
        int fees = (int)( rand * 1.069);
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
