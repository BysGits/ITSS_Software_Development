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
	private int id;
	private String name;
	private int pedals;
	private int saddles;
	private int rearSeats;
	private int rentingFee;
	private int depositFee;
	private boolean motor;

	public BikeType() throws SQLException {
//		stm = ECOBIKEDB.getConnection().createStatement();
	}

	public BikeType(int id) throws SQLException{
		this.id = id;
	}
	
	public BikeType(int id, String name, int pedals, int saddles, int rearSeats, int rentingFee, int depositFee, boolean motor) throws SQLException {
		this.id = id;
		this.name = name;
		this.pedals = pedals;
		this.saddles = saddles;
		this.rearSeats = rearSeats;
		this.rentingFee = rentingFee;
		this.depositFee = depositFee;
		this.motor = motor;
	}

	// getter and setter
	public int getId() {
		return id;
	}

	public BikeType setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public BikeType setName(String name) {
		this.name = name;
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

	public boolean isMotor() {
		return motor;
	}

	public BikeType setMotor(boolean motor) {
		this.motor = motor;
		return this;
	}
	
	public BikeType createNewBikeTypeFromDB (ResultSet res) throws SQLException{
		int tmpId = res.getInt("id");
		String tmpName = res.getString("name");
		int tmpPedals = res.getInt("pedals");
		int tmpSaddels = res.getInt("saddels");
		int tmpRearSeats = res.getInt("rearSeats");
		int tmpRentingFee = res.getInt("rentingFee");
		int tmpDepositFee = res.getInt("depositFee");
		boolean tmpMotor = res.getBoolean("motor");
		
		return new BikeType(tmpId, tmpName, tmpPedals, tmpSaddels, tmpRearSeats, tmpRentingFee, tmpDepositFee, tmpMotor);
	}
	
	public BikeType getBikeTypeById(int id) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM ECOBIKE.BIKETYPE WHERE ID = " + id + ";";
		ResultSet res = stm.executeQuery(query);
		
		if(res.next()) {
			return createNewBikeTypeFromDB(res);
		}else {
			return null;
		}
	}
	
	public List<BikeType> getAllBikeTypes() throws SQLException{
		ArrayList<BikeType> bikeTypeList = new ArrayList<BikeType>();
		
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM ECOBIKE.BIKETYPE";
		ResultSet res = stm.executeQuery(query);
		
		while(res.next()) {
			BikeType bikeType = createNewBikeTypeFromDB(res);
			bikeTypeList.add(bikeType);
		}
		
		return bikeTypeList;
	}
	
}
