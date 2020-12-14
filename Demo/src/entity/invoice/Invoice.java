package entity.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.db.ECOBIKEDB;
import entity.rent.Rent;
import utils.Utils;

public class Invoice {
	private static Logger LOGGER = Utils.getLogger(Invoice.class.getName());
	
	private int id;
	private Rent rent;
	private int totalAmount;
	
	public Invoice() {
		
	}
	
	public int getId() {
		return id;
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

	public void saveInvoice() {
		
	}

	public Invoice setRent(Rent rent) {
		this.rent = rent;
		return this;
	}
	
	public Invoice createNewInvoiceFromDB (ResultSet res) throws SQLException {
		return new Invoice()
				.setId(res.getInt("id"))
				.setRent(new Rent().getRentById(res.getInt("rentId")))
				.setTotalAmount(res.getInt("totalamount"));
	}
	
	public Invoice getInvoiceById (int id) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM ECOBIKE.INVOICE WHERE ID =" + id + ";";
		ResultSet res = stm.executeQuery(query);
		if(res.next()) {
			return createNewInvoiceFromDB(res);
		}else {
			return null;
		}
	}
	
	public List<Invoice> getAllInvoices() throws SQLException{
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM ECOBIKE.INVOICE;";
		ResultSet res = stm.executeQuery(query);
		
		while(res.next()) {
			invoiceList.add(createNewInvoiceFromDB(res));
		}
		return invoiceList;
	}
	
	public Invoice addInvoice (int id, Rent rent, int totalAmount) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "INSERT INTO TABLE ECOBIKE.INVOICE(ID, RENTID, TOTALAMOUNT) VALUE (" 
						+ id + "," + rent.getId() + "," + totalAmount + ";";
		stm.executeQuery(query);
		
		return new Invoice(id, rent, totalAmount);
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
