package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.ECOBIKEDB;

public class BikeType {
	
	protected Statement stm;
	protected int id;
	protected String name;
	protected int pedals;
	protected int saddles;
	protected int rearSeats;
	protected int rentingFee;
	protected int depositFee;
	// boolean motor;
	
	public BikeType() throws SQLException {
		stm = ECOBIKEDB.getConnection().createStatement();
	}
	
	public BikeType(int id, String name, int pedals, int saddles, int rearSeats, int rentingFee, int depositFee) throws SQLException {
		this.id = id;
		this.name = name;
		this.pedals = pedals;
		this.saddles = saddles;
		this.rearSeats = rearSeats;
		this.rentingFee = rentingFee;
		this.depositFee = depositFee;
		//this.motor = motor;
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

	public int getPedals() {
		return pedals;
	}

	public void setPedals(int pedals) {
		this.pedals = pedals;
	}

	public int getSaddles() {
		return saddles;
	}

	public void setSaddles(int saddles) {
		this.saddles = saddles;
	}

	public int getRearSeats() {
		return rearSeats;
	}

	public void setRearSeats(int rearSeats) {
		this.rearSeats = rearSeats;
	}

	public int getRentingFee() {
		return rentingFee;
	}

	public void setRentingFee(int rentingFee) {
		this.rentingFee = rentingFee;
	}

	public int getDepositFee() {
		return depositFee;
	}

	public void setDepositFee(int depositFee) {
		this.depositFee = depositFee;
	}
	
}
