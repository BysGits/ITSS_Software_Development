package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.db.ECOBIKEDB;
import entity.dock.Dock;

public class BikeType {
	
//	private Statement stm;
	protected int id;
	protected String type;
	protected String barcode;
	protected int pedals;
	protected int saddles;
	protected int rearSeats;
	protected int rentingFee;
	protected int depositFee;
	protected boolean state;
	protected int dockId;
	protected int battery;

	public BikeType() throws SQLException {
//		stm = ECOBIKEDB.getConnection().createStatement();
	}

	public BikeType(int id) throws SQLException{
		this.id = id;
	}
	
	public BikeType(int id, String type, String barcode, int pedals, int saddles, int rearSeats, int rentingFee, int depositFee, boolean state, int dockId, int battery) throws SQLException {
		this.id = id;
		this.type = type;
		this.barcode = barcode;
		this.pedals = pedals;
		this.saddles = saddles;
		this.rearSeats = rearSeats;
		this.rentingFee = rentingFee;
		this.depositFee = depositFee;
		this.state = state;
		this.dockId = dockId;
		this.battery = battery;
	}

	// getter and setter
	public int getId() {
		return id;
	}

	public BikeType setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getType() {
		return type;
	}
	
	public BikeType setType(String type) {
		this.type = type;
		return this;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public BikeType setBarcode(String barcode) {
		this.barcode = barcode;
		return this;
	}

	public int getPedals() {
		return pedals;
	}

	public BikeType setPedals(int pedals) {
		this.pedals = pedals;
		return this;
	}

	public int getSaddles() {
		return saddles;
	}

	public BikeType setSaddles(int saddles) {
		this.saddles = saddles;
		return this;
	}

	public int getRearSeats() {
		return rearSeats;
	}

	public BikeType setRearSeats(int rearSeats) {
		this.rearSeats = rearSeats;
		return this;
	}

	public int getRentingFee() {
		return rentingFee;
	}

	public BikeType setRentingFee(int rentingFee) {
		this.rentingFee = rentingFee;
		return this;
	}

	public int getDepositFee() {
		return depositFee;
	}

	public BikeType setDepositFee(int depositFee) {
		this.depositFee = depositFee;
		return this;
	}
	
	public boolean getState() {
		return state;
	}
	
	public BikeType setState(boolean state) {
		this.state = state;
		return this;
	}
	
	public int getDockId() {
		return dockId;
	}
	
	public BikeType setDockId(int dockId) {
		this.dockId = dockId;
		return this;
	}

	public int getBattery() {
		return battery;
	}
	
	public BikeType createNewBikeFromDB (ResultSet res) throws SQLException{
		int tmpId = res.getInt("id");
		String tmpType = res.getString("type");
		String tmpBarcode = res.getString("barcode");
		int tmpPedals = res.getInt("pedals");
		int tmpSaddles = res.getInt("saddles");
		int tmpRearSeats = res.getInt("rearSeats");
		int tmpRentingFee = res.getInt("rentingFee");
		int tmpDepositFee = res.getInt("depositFee");
		boolean tmpState = res.getBoolean("state");
		int tmpDockId = res.getInt("dockId");
		int tmpBattery = res.getInt("battery");
		
		return new BikeType(tmpId, tmpType, tmpBarcode, tmpPedals, tmpSaddles, tmpRearSeats, tmpRentingFee, tmpDepositFee, tmpState, tmpDockId, tmpBattery);
	}
//	
//	public BikeType getBikeById(int id) throws SQLException{
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "SELECT * FROM BIKE WHERE ID = " + id + ";";
//		ResultSet res = stm.executeQuery(query);
//		
//		
//		
//	}
//	
//	public List<BikeType> getAllBikeTypes() throws SQLException{
//		ArrayList<BikeType> bikeTypeList = new ArrayList<BikeType>();
//		
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "SELECT * FROM BIKETYPE";
//		ResultSet res = stm.executeQuery(query);
//		
//		while(res.next()) {
//			BikeType bikeType = createNewBikeTypeFromDB(res);
//			bikeTypeList.add(bikeType);
//		}
//		
//		return bikeTypeList;
//	}
//	
	
	public List<BikeType> getBikeByDockId(int dockId) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM BIKE INNER JOIN BIKETYPE ON BIKE.typeId = BIKETYPE.Id WHERE dockId = " + dockId + ";";
		ResultSet res = stm.executeQuery(query);
		ArrayList<BikeType> bikeList = new ArrayList<BikeType>();
		
		while (res.next()) {
			BikeType bike = createNewBikeFromDB(res);
			bikeList.add(bike);
		}
		return bikeList;
	}
	
	public BikeType getBikeByBarcode(String barcode) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM BIKE INNER JOIN BIKETYPE ON BIKE.typeId = BIKETYPE.Id WHERE BIKE.barcode = '" + barcode + "';";
		ResultSet res = stm.executeQuery(query);
		
		
		if (res.next()) {
			System.out.println("CHECK1");
			return createNewBikeFromDB(res);
		} else {
			return null;
		}
		
		
		
		
	}
}
