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
	
	private Statement stm;
	private int id;
	private String name;
	private String address;
	private int availableBikes;
	private int emptySlots;
	
	
	public Dock() throws SQLException {
		stm = ECOBIKEDB.getConnection().createStatement();
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

	public Dock setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
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
		return availableBikes;
	}

	public Dock setAvailableBikes(int availableBikes) {
		this.availableBikes = availableBikes;
		return this;
	}

	public int getEmptySlots() {
		return emptySlots;
	}

	public Dock setEmptySlots(int emptySlots) {
		this.emptySlots = emptySlots;
		return this;
	}

	public Dock getDockById(int id) throws SQLException {
		String sql = "SELECT * FROM ecobike.Dock WHERE Dock.id = " + id + ";";
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		if (res.next()) {
			
			return new Dock()
					.setId(res.getInt("id"))
					.setName(res.getString("name"))
					.setAddress(res.getString("address"))
					.setAvailableBikes(res.getInt("availableBikes"))
					.setEmptySlots(res.getInt("emptySlots"));
		}
		return null;
	}
	public List getAllDock() throws SQLException {
		
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery("SELECT * FROM Dock");
		ArrayList dockList = new ArrayList<>();
		
		while (res.next()) {
			Dock dock = new Dock()
					.setId(res.getInt("id"))
					.setName(res.getString("name"))
					.setAddress(res.getString("address"))
					.setAvailableBikes(res.getInt("availableBikes"))
					.setEmptySlots(res.getInt("emptySlots"));
			dockList.add(dock);
		}
		return dockList;
		
	}
	

	public Bike getBike() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
