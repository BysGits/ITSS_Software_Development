package entity.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.bike.Bike;
import entity.db.ECOBIKEDB;
import entity.rent.Rent;
import utils.Utils;

public class Invoice {
	private static Logger LOGGER = Utils.getLogger(Invoice.class.getName());
	
	private int id;
	private Rent rent;
	private int totalAmount;
	private int totalTime;
	private int bikeId;
	private int startDockId;
	private int endDockId;
	
	public int getId() {
		return id;
	}
	
	public Invoice() {
		
	}
	
	public Invoice (int id, Rent rent, int amount) {
		this.id = id;
		this.rent = rent;
		this.totalAmount = amount;
	}

	public Invoice setId(int id) {
		this.id = id;
		return this;
	}
	
	public Invoice(Rent rent) {
		this.rent = rent;
	}
	
	public Rent getRent() {
		return rent;
	}
	
	public int getTotalAmount() {
		return totalAmount;
	}

	public Invoice setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

	public void saveInvoice(Rent rent, int totalAmount) {
		
	}

	public Invoice setRent(Rent rent) {
		this.rent = rent;
		return this;
	}
	
	public Invoice setBikeId(int id) {
		this.bikeId = id;
		return this;
	}
	
	public Invoice setStartDockId(int id) {
		this.startDockId = id;
		return this;
	}
	
	public Invoice setEndDockId(int id) {
		this.endDockId = id;
		return this;
	}
	
	public Invoice setTotalTime(int totalTime) {
		this.totalTime = totalTime;
		return this;
	}
	
	public Invoice createNewInvoiceFromDB (ResultSet res) throws SQLException {
		return new Invoice()
				.setId(res.getInt("id"))
				.setStartDockId(res.getInt("startDockId"))
				.setEndDockId(res.getInt("endDockId"))
				.setBikeId(res.getInt("bikeId"))
				.setTotalTime(res.getInt("totalTime"))
				.setTotalAmount(res.getInt("totalAmount"));
	}
//	
//	public Invoice getInvoiceById (int id) throws SQLException {
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "SELECT * FROM ECOBIKE.INVOICE WHERE ID =" + id + ";";
//		ResultSet res = stm.executeQuery(query);
//		if(res.next()) {
//			return createNewInvoiceFromDB(res);
//		}else {
//			return null;
//		}
//	}
//	
//	public List<Invoice> getAllInvoices() throws SQLException{
//		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
//		
//		Statement stm = ECOBIKEDB.getConnection().createStatement();
//		String query = "SELECT * FROM ECOBIKE.INVOICE;";
//		ResultSet res = stm.executeQuery(query);
//		
//		while(res.next()) {
//			invoiceList.add(createNewInvoiceFromDB(res));
//		}
//		return invoiceList;
//	}
//	
	public static void addInvoice (Rent rent) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "INSERT INTO  INVOICE(bikeId, totalAmount, totalTime) VALUE (" 
						 + rent.getBike().getId() + "," + rent.getCurrentFee() +  "," + rent.getRentTime() + ");";
		stm.execute(query);
		//return new Invoice(id, rent, totalAmount);
	}
	
	public void updateInvoiceFieldById(String tbname, int id, String field, Object value) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		if(value instanceof String) {
			value = "\"" + value + "\"";
		}
		stm.executeUpdate("update " + tbname + 
						" set" + " " + field + "=" + value + 
						" where id = " + id +";");
	}
	
	public void deleteInvoiceById(int id) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "DELETE FROM ECOBIKE.INVOICE WHERE ID = " + id + ";";
		stm.executeQuery(query);
	}
}
