package entity.bike;

import java.sql.SQLException;

public class Bike {

	private int id;
	private String barcode;
	private int batteryLife;
	private boolean state;
	private BikeType type;
	
	public Bike() throws SQLException {
		
	}
	
	public Bike(int id, String barcode, BikeType type, int batteryLife, boolean state) throws SQLException {
		this.id = id;
		this.barcode = barcode;
		this.type = type;
		this.batteryLife = batteryLife;
		this.state = state;
	}

	// getter and setter 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public BikeType getType() {
		return type;
	}

	public void setType(BikeType type) {
		this.type = type;
	}	
}
