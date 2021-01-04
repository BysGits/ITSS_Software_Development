package entity.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.bike.Bike;
import entity.db.ECOBIKEDB;

public class BikeHandler {

	public static Bike assignFromDB (ResultSet res) throws SQLException {
		return (Bike) new Bike()
				.setId(res.getInt("id"))
				.setTypeId(res.getInt("typeId"))
				.setBarcode(res.getString("barcode"))
				.setState(res.getBoolean("state"))
				.setBattery(res.getInt("battery"))
				.setDockId(res.getInt("dockId"))
				.setType(res.getString("type"))
				.setPedals(res.getInt("pedals"))
				.setSaddles(res.getInt("saddles"))
				.setRearSeats(res.getInt("rearSeats"))
				.setDepositFee(res.getInt("depositFee"));
	}
	
	public static void updateBike(Bike bike, int newDockId, int newBattery) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "UPDATE bike SET dockId = " + newDockId +  ", battery = " + newBattery + " WHERE id = " + bike.getId() + ";";
		stm.executeUpdate(query);
	}
	
	public static void updateBike(Bike bike, int newDockId) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "UPDATE bike SET dockId = " + newDockId + " WHERE id = " + bike.getId() + ";";
		stm.executeUpdate(query);
	}
	
	public static void updateBike(Bike bike, boolean state) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "UPDATE bike SET state = " + state + " WHERE id = " + bike.getId() + ";";
		stm.executeUpdate(query);
	}
	
	public static List<Bike> getBikeByDockId(int dockId) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM bike INNER JOIN biketype ON bike.typeId = biketype.Id WHERE dockId = " + dockId + ";";
		ResultSet res = stm.executeQuery(query);
		ArrayList<Bike> bikeList = new ArrayList<Bike>();
		
		while (res.next()) {
			Bike bike = assignFromDB(res);
			bikeList.add(bike);
		}
		return bikeList;
	}
	
	public static Bike getBikeByBarcode(String barcode) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM bike INNER JOIN biketype ON bike.typeId = biketype.Id WHERE bike.barcode = '" + barcode + "';";
		ResultSet res = stm.executeQuery(query);
		
		
		if (res.next()) {
			return assignFromDB(res);
		}
		
		return null;
	}
	
	public static Bike getBikeById(int bikeId) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM bike INNER JOIN biketype ON bike.typeId = biketype.id WHERE bike.id = " + bikeId + ";";
		ResultSet res = stm.executeQuery(query);
		if (res.next()) {
			return assignFromDB(res);
		} 
		
		return null;
	}
}
