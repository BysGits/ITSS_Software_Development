package entity.rent;

import java.sql.SQLException;

import entity.bike.Bike;

public class Rent {
	
	private int id;
	private Bike bike;
	private int currentFee;
	private int rentTime;
	
	public Rent() throws SQLException {
		
	}
	
	public Rent(int id, Bike bike, int currentFee, int rentTime) throws SQLException {
		this.id = id;
		this.bike = bike;
		this.currentFee = currentFee;
		this.rentTime = rentTime;
	}

	// getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public int getCurrentFee() {
		return currentFee;
	}

	public void setCurrentFee(int currentFee) {
		this.currentFee = currentFee;
	}

	public int getRentTime() {
		return rentTime;
	}

	public void setRentTime(int rentTime) {
		this.rentTime = rentTime;
	}
	
	
}
