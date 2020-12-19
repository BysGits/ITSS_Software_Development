package entity.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Configs;

public class RushOrder {

    private int shippingFees;
    private List listOrderMedia;
    private HashMap<String, String> rushDeliveryInfo;

    public RushOrder(){
        this.listOrderMedia = new ArrayList<>();
    }

    public RushOrder(List lstOrderMedia) {
        this.listOrderMedia = lstOrderMedia;
    }

    public void addOrderMedia(OrderMedia om){
        this.listOrderMedia.add(om);
    }

    public void removeOrderMedia(OrderMedia om){
        this.listOrderMedia.remove(om);
    }

    public List getlstOrderMedia() {
        return this.listOrderMedia;
    }

    public void setlstOrderMedia(List lstOrderMedia) {
        this.listOrderMedia = lstOrderMedia;
    }

    public void setShippingFees(int shippingFees) {
        this.shippingFees = shippingFees;
    }

    public int getShippingFees() {
        return shippingFees;
    }

    public HashMap getDeliveryInfo() {
        return rushDeliveryInfo;
    }

    public void setDeliveryInfo(HashMap deliveryInfo) {
        this.rushDeliveryInfo = deliveryInfo;
    }

    public int getAmount(){
        double amount = 0;
        for (Object object : listOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }
        return (int) (amount + (Configs.PERCENT_VAT/100)*amount);
    }
}
