package entity.dock;

import java.sql.SQLException;

import entity.bike.Bike;

public class Dock {
	
	private int id;
	private String name;
	private String address;
	private String area;
	private int availableBikes;
	private int emptySlots;
	
	
	public Dock() throws SQLException {
		
	}
	
	public Dock(int id, String name, String address, int availableBikes, int emptySlots) throws SQLException {
		this.id = id;
		this.name = name;
		this.address = address;
		this.availableBikes = availableBikes;
		this.emptySlots = emptySlots;
	}

	// getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(int availableBikes) {
		this.availableBikes = availableBikes;
	}

	public int getEmptySlots() {
		return emptySlots;
	}

	public void setEmptySlots(int emptySlots) {
		this.emptySlots = emptySlots;
	}
	

	public Bike getBike() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
