package entity.bike;

import java.sql.SQLException;

public class Bike extends BikeType {
	
	// bike variables
	private int id;
	private int typeId;
	private int dockId;
	private String barcode;
	private boolean isAvailable;
	private int battery;

	public Bike() {}
	
	public Bike(int id, int typeId, String type, String barcode, int pedals, int saddles, int rearSeats, int rentingFee, int depositFee, boolean isAvailable, int dockId, int battery) throws SQLException {
		super(typeId, type, pedals, saddles, rearSeats, depositFee);
		this.id = id;
		this.typeId = typeId;
		this.barcode = barcode;
		this.isAvailable = isAvailable;
		this.dockId = dockId;
		this.battery = battery;
	}
	
//	public String getFeatures() {
//		return super.getFeatures();
//	}

	// getter and setter
	
	public int getId() {
		return id;
	}

	public Bike setId(int id) {
		this.id = id;
		return this;
	}

	public int getTypeId() {
		return typeId;
	}

	public Bike setTypeId(int typeId) {
		this.typeId = typeId;
		return this;
	}

	public int getDockId() {
		return dockId;
	}

	public Bike setDockId(int dockId) {
		this.dockId = dockId;
		return this;
	}

	public String getBarcode() {
		return barcode;
	}

	public Bike setBarcode(String barcode) {
		this.barcode = barcode;
		return this;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public Bike setState(boolean state) {
		this.isAvailable = state;
		return this;
	}

	public int getBattery() {
		return battery;
	}

	public Bike setBattery(int battery) {
		this.battery = battery;
		return this;
	}
	
	
	
	
	
	
	
}
