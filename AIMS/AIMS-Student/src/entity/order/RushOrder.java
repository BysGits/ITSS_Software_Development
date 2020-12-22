package entity.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Configs;

/**
 * @author quan.dh176850
 *
 */
public class RushOrder {
	
	private int shippingFees;
	private List listRushOrderMedia;
    private HashMap<String, String> rushDeliveryInfo;

    public RushOrder(){
        this.listRushOrderMedia = new ArrayList<>();
    }

    public RushOrder(List lstOrderMedia) {
        this.listRushOrderMedia = lstOrderMedia;
    }

    public void addRushOrderMedia(OrderMedia om){
        this.listRushOrderMedia.add(om);
    }

    public void removeRushOrderMedia(OrderMedia om){
        this.listRushOrderMedia.remove(om);
    }
    
    public int getShippingFees() {
		return shippingFees;
	}

	public void setShippingFees(int shippingFees) {
		this.shippingFees = shippingFees;
	}

    public List getlstRushOrderMedia() {
        return this.listRushOrderMedia;
    }

    public void setlstRushOrderMedia(List lstRushOrderMedia) {
        this.listRushOrderMedia = lstRushOrderMedia;
    }

    public HashMap getRushDeliveryInfo() {
        return rushDeliveryInfo;
    }

    public void setRushDeliveryInfo(HashMap rushDeliveryInfo) {
        this.rushDeliveryInfo = rushDeliveryInfo;
    }

    public int getAmount(){
        double amount = 0;
        for (Object object : listRushOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }
        amount*=1.1;
        return (int) (amount + (Configs.PERCENT_VAT/100)*amount);
    }
}
