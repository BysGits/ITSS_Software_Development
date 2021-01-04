package entity.dock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.db.ECOBIKEDB;
import utils.Utils;

import entity.bike.Bike;

public class Dock {
	
	private static Logger LOGGER = Utils.getLogger(Dock.class.getName());
	
	private int id;
	private String name;
	private String address;
	private int availableBikes;
	private int emptySlots;
	
	
	public Dock(){}
	
	public Dock(int id, String name, String address, int availableBikes, int emptySlots) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.availableBikes = availableBikes;
		this.emptySlots = emptySlots;
	}

	// getter and setter
	public int getId() {
		return this.id;
	}

	public Dock setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public Dock setName(String name) {
		this.name = name;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Dock setAddress(String address) {
		this.address = address;
		return this;
	}

	public int getAvailableBikes() {
		return this.availableBikes;
	}

	public Dock setAvailableBikes(int availableBikes) {
		this.availableBikes = availableBikes;
		return this;
	}

	public int getEmptySlots() {
		return this.emptySlots;
	}

	public Dock setEmptySlots(int emptySlots) {
		this.emptySlots = emptySlots;
		return this;
	}
}
