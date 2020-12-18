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
	private boolean type;
	private double currentFee;
	private double rentingTime;
	private double start;
	private double end;
	
	
	public Rent() throws SQLException {
		
	}
	
	public Rent(Bike bike) throws SQLException {
		this.bike = bike;
		this.currentFee = 0;
		this.start = System.currentTimeMillis();
	}
	
	
	public Rent(int id, Bike bike, int currentFee, int rentTime) throws SQLException {
		this.id = id;
		this.bike = bike;
		this.currentFee = currentFee;
		this.rentingTime = rentTime;
	}

	public Rent setStart() {
//		this.start = LocalDateTime.now();
		this.start = System.nanoTime();
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

	public double getCurrentFee() {
		return currentFee;
	}

	public Rent setCurrentFee(int currentFee) {
		this.currentFee = currentFee;
		return this;
	}

	public double getRentTime() {
		return rentingTime;
	}

	public Rent setRentTime(int rentTime) {
		this.rentingTime = rentTime;
		return this;
	}
	
//	public Rent createNewRentFromDB (ResultSet res) throws SQLException {
//		return new Rent()
//				.setId(res.getInt("id"))
//				.setBike(new Bike().getBikeById(res.getInt("bikeid")))
//				.setCurrentFee(res.getInt("currentfee"))
//				.setRentTime(res.getInt("rentTime"));
//	}
//	
//	public Rent getRentById(int id) throws SQLException{
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "SELECT * FROM ECOBIKE.RENT WHERE ID =" + id + ";";
//		ResultSet res = stm.executeQuery(query);
//		
//		if(res.next()) {
//			return createNewRentFromDB(res);
//		}else {
//			return null;
//		}
//	}
//	
//	public List<Rent> getAllRents() throws SQLException {
//			
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		ResultSet res = stm.executeQuery("SELECT * FROM ECOBIKE.RENT");
//		ArrayList<Rent> rentList = new ArrayList<Rent>();
//		
//		while (res.next()) {
//			Rent rent = createNewRentFromDB(res);
//			rentList.add(rent);
//		}
//		return rentList;
//	}
	
	public Rent addRent(int id, Bike bike, int currentFee, int rentTime) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "INSERT INTO TABLE ECOBIKE.RENT(ID, BIKEID, CURRENTFEE, RENTTIME) VALUE("
						+ id + "," + bike.getId() + "," + currentFee + "," + rentTime + ");";
		stm.executeQuery(query);
		
		return new Rent(id, bike, currentFee, rentTime);
	}

	public void updateRentFieldById(String tbname, int id, String field, Object value) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		if(value instanceof String) {
			value = "\"" + value + "\"";
		}
		stm.executeUpdate("update " + tbname + 
						" set" + " " + field + "=" + value + 
						" where id = " + id +";");
	}
	
	public void deleteRentById(int id) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "DELETE FROM ECOBIKE.RENT WHERE ID =" + id + ";";
		stm.executeQuery(query);
	}
	
//	public double calculateFee() {
//		// Standard rent: 0
//		// 24-hour rent: 1
//		if (!type) {
//			if (rentingTime < 10) {
//				return 0;
//			} else {
//				currentFee = 10000;
//				if (rentingTime > 30) {
//					currentFee += ((rentingTime - 30)/15)*3000;
//				}
//				return currentFee;
//			}
//		} else {
//			currentFee = 200000;
//			if (rentingTime < 12*60) {
//				currentFee = 200000 - (12 - rentingTime/60 + 1)
//			}
//		}
//	}
	
	
	
}
