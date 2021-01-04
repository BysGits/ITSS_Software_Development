package entity.rent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.bike.Bike;
import entity.bike.Bike;
import entity.db.ECOBIKEDB;
import entity.dock.Dock;
import utils.Utils;

public class Rent {
	private static Logger LOGGER = Utils.getLogger(Rent.class.getName());	
	
	private int id;
	private Bike bike;
	private int currentFee;
	private int rentingTime;
	private int startDockId;
	private int endDockId;
	
	private double start;
	private double end;
	private int pauseTime;
	
	public Rent() {}
	
	public Rent(Bike bike) {
		this.bike = bike;
		this.currentFee = 0;
		this.start = System.currentTimeMillis();
	}
	
	
	public Rent(int id, Bike bike, int currentFee, int rentTime) {
		this.id = id;
		this.bike = bike;
		this.currentFee = currentFee;
		this.rentingTime = rentTime;
	}

	public Rent setStart() {
		this.start = System.currentTimeMillis();
		return this;
	}
	
	public double getStart() {
		return start;
	}
	
	public Rent setEnd() {
		this.end = System.nanoTime();
		return this;
	}
	
	public double getEnd() {
		return end;
	}
	// getter and setter
	public int getId() {
		return id;
	}

	public Rent setId(int id) {
		this.id = id;
		return this;
	}

	public Bike getBike() {
		return bike;
	}

	public Rent setBike(Bike bike) {
		this.bike = bike;
		return this;
	}

	public int getCurrentFee() {
		return currentFee;
	}

	public Rent setCurrentFee(int currentFee) {
		this.currentFee = currentFee;
		return this;
	}

	public int getRentTime() {
		return rentingTime;
	}

	public Rent setRentTime(int rentTime) {
		this.rentingTime = rentTime;
		return this;
	}
	
	
	public int getStartDockId() {
		return startDockId;
	}

	public Rent setStartDockId(int startDockId) {
		this.startDockId = startDockId;
		return this;
	}

	public int getEndDockId() {
		return endDockId;
	}

	public Rent setEndDockId(int endDockId) {
		this.endDockId = endDockId;
		return this;
	}


	public int getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(int pauseTime) {
		this.pauseTime = pauseTime;
	}

	public int getType() {
		return 1;
	}
	
}
