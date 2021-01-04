package entity.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.db.ECOBIKEDB;
import entity.dock.Dock;
import utils.Configs;

public class DockHandler {

	public static Dock assignFromDB(ResultSet res) throws SQLException {
		return new Dock()
				.setId(res.getInt("id"))
				.setName(res.getString("name"))
				.setAddress(res.getString("address"))
				.setAvailableBikes(res.getInt("availableBikes"))
				.setEmptySlots(res.getInt("emptySlots"));
	}
	
	public static Dock getDockById(int id) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM dock WHERE id = " + id + ";";
		ResultSet res = stm.executeQuery(query);
		
		if (res.next()) {
			return assignFromDB(res);
		}
		
		return null;
	}
	
	public static Dock getDockByName(String name) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM dock WHERE name = '" + name + "';";
		ResultSet res = stm.executeQuery(query);
		
		if (res.next()) {
			return assignFromDB(res);
		}
		
		return null;
	}
	
	public static List<Dock> getAllDocks() throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM dock WHERE id != 1;";
		ResultSet res = stm.executeQuery(query);
		ArrayList<Dock> dockList = new ArrayList<Dock>();
		
		
		while (res.next()) {
			Dock dock =  assignFromDB(res);
			dockList.add(dock);
		}
		
		return dockList;
	}
	
	public static List<Dock> searchDock(String input) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM dock WHERE (name LIKE '%" + input 
					 	+ "%' OR address LIKE '%" + input + "%') AND id != 1;";
		
		ResultSet res = stm.executeQuery(query);
		ArrayList<Dock> dockList = new ArrayList<Dock>();
		
		while (res.next()) {
			Dock dock = assignFromDB(res);
			dockList.add(dock);
		}
		return dockList;
	}
	
	public static void updateDockAfterRent(Dock dock) throws SQLException {
		int availableBikes = dock.getAvailableBikes();
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "UPDATE dock SET availableBikes = " + (availableBikes - 1 ) 
						+ ", emptySlots = " + (Configs.DOCK_SLOT - availableBikes + 1) + " WHERE id = " + dock.getId() + ";";
		stm.executeUpdate(query);
	}
	
	public static void updateDockAfterReturn(Dock dock) throws SQLException {
		int availableBikes = dock.getAvailableBikes();
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "UPDATE dock SET availableBikes = " + (availableBikes + 1 ) 
						+ ", emptySlots = " + (Configs.DOCK_SLOT - availableBikes - 1) + " WHERE id = " + dock.getId() + ";";
		stm.executeUpdate(query);
	}
	
	// ------------------------
//	public Dock addDock(int id, String name, String address, int availableBikes, int emptySlots ) throws SQLException {
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "INSERT INTO TABLE dock(id, name, address, availableBikes, emptySlots) VALUE("
//						+ id + "," + name + ",\"" + address + "\"" + availableBikes + "," + emptySlots + ");";
//		stm.executeQuery(query);
//		
//		return new Dock(id, name, address, availableBikes, emptySlots);
//		
//	}
//	
//	public void updateBikeFieldById(String tbname, int id, String field, Object value) throws SQLException{
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		if(value instanceof String) {
//			value = "\"" + value + "\"";
//		}
//		stm.executeUpdate("update " + tbname + 
//						" set" + " " + field + "=" + value + 
//						" where id = " + id +";");
//	}
//
//	public void deleteDockById(int id) throws SQLException {
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "DELETE FROM dock WHERE id = " + id + ";";
//		stm.executeQuery(query);
//	}
}
