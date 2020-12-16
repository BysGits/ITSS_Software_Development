package entity.bike;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.db.ECOBIKEDB;
import entity.dock.Dock;
import javafx.scene.media.Media;
import utils.Utils;

public class Bike {
	
	private static Logger LOGGER = Utils.getLogger(Bike.class.getName());
	
//	private Statement stm;
	private int id;
	private String barcode;
	private int batteryLife;
	private Dock dock;
	private boolean state;
	private BikeType type;
	
	public Bike() throws SQLException {
//		stm = ECOBIKEDB.getConnection().createStatement();
	}
	
	public Bike(int id) throws SQLException{
		this.id = id;
		
//		stm = ECOBIKEDB.getConnection().createStatement();
	}
	
	public Bike(int id, String barcode, int batteryLife, Dock dock, boolean state,  BikeType type) throws SQLException {
		this.id = id;
		this.barcode = barcode;
		this.type = type;
		this.batteryLife = batteryLife;
		this.dock = dock;
		this.state = state;
		this.type = type;
		
//		stm = ECOBIKEDB.getConnection().createStatement();
	}

	// getter and setter 
	public int getId() {
		return id;
	}

	public Bike setId(int id) {
		this.id = id;
		return this;
	}

	public String getBarcode() {
		return barcode;
	}

	public Bike setBarcode(String barcode) {
		this.barcode = barcode;
		return this;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public Bike setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
		return this;
	}

	public Dock getDock() {
		return dock;
	}

	public Bike setDock(Dock dock) {
		this.dock = dock;
		return this;
	}
	
	public boolean isState() {
		return state;
	}

	public Bike setState(boolean state) {
		this.state = state;
		return this;
	}

	public BikeType getType() {
		return type;
	}

	public Bike setType(BikeType type) {
		this.type = type;
		return this;
	}
	
//	public Bike createNewBikeFromDB (ResultSet res) throws SQLException {
//		int tmpId = res.getInt("id");
//		String tmpBarcode = res.getString("barcode");
//		int tmpBatteryLife = res.getInt("batterylife");
//		Dock tmpDock = new Dock().getDockById(res.getInt("dockid"));
//		boolean tmpState = res.getBoolean("state");
//		BikeType tmpBikeType = new BikeType().getBikeByDockId(res.getInt("typeid"));
//		
//		return new Bike(tmpId, tmpBarcode, tmpBatteryLife, tmpDock, tmpState, tmpBikeType);
//	}
//	
//	public Bike getBikeById(int id) throws SQLException{
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "SELECT * FROM ECOBIKE.BIKE WHERE ID = " + id + ";";
//		ResultSet res = stm.executeQuery(query);
//		
//		if(res.next()) {
//			return createNewBikeFromDB(res);
//		}else {
//			return null;
//		}
//	}
//	
//	public List<Bike> getAllBikes() throws SQLException{
//		ArrayList<Bike> bikeList = new ArrayList<Bike>();
//		
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "SELECT * FROM ECOBIKE.BIKE;";
//		ResultSet res = stm.executeQuery(query);
//		
//		while(res.next()) {
//			Bike bike = new Bike()
//					.setId(res.getInt("id"))
//					.setBarcode(res.getString("barcode"))
//					.setBatteryLife(res.getInt("batterylife"))
//					.setDock(new Dock().getDockById(res.getInt("dockid")))
//					.setState(res.getBoolean("state"))
//					.setType(new BikeType().getBikeTypeById(res.getInt("typeid")));
//			
//			bikeList.add(bike);
//		}
//		
//		return bikeList;
//	}
//	
	public Bike addBike(int id, String barcode, int batteryLife, Dock dock, boolean state,  BikeType type) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "INSERT INTO TABLE ECOBIKE.BIKE(ID, BARCODE, BATTERYLIFE, DOCK, STATE, TYPEID) VALUE (" 
						+ id + ",\"" + barcode + "\"," + batteryLife
						+ dock.getId() + "," + state + "," + type.getId() + ";";
		stm.executeQuery(query);
		
		return new Bike(id, barcode, batteryLife, dock, state, type);
	}
	
	public void updateBikeFieldById(String tbname, int id, String field, Object value) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		if(value instanceof String) {
			value = "\"" + value + "\"";
		}
		stm.executeUpdate("update " + tbname + 
						" set" + " " + field + "=" + value + 
						" where id = " + id +";");
	}
	
	public void deleteBikeById(int Id) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "DELETE FROM ECOBIKE.BIKE WHERE ID = " + id + ";";
		stm.executeQuery(query);		
	}
}
