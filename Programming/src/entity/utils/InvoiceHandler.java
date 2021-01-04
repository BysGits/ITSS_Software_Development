package entity.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.account.Account;
import entity.db.ECOBIKEDB;
import entity.invoice.Invoice;
import entity.rent.Rent;

public class InvoiceHandler {

	public static Invoice assignFromDB (ResultSet res) throws SQLException {
		return new Invoice()
				.setId(res.getInt("id"))
				.setStartDockId(res.getInt("startDockId"))
				.setEndDockId(res.getInt("endDockId"))
				.setBikeId(res.getInt("bikeId"))
				.setTotalTime(res.getInt("totalTime"))
				.setTotalAmount(res.getInt("totalAmount"))
				.setCardCode(res.getString("cardCode"))
				.setOwner(res.getString("owner"))
				.setTransactionId(res.getString("transactionId"))
				.setCreatedAt(res.getString("createdAt"))
				.setAccountId(res.getInt("accountId"));
	}

	public static void addInvoice (Rent rent, Map<String, String> response) throws SQLException{
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "INSERT INTO  invoice(bikeId, totalAmount, totalTime, startDockId, endDockId, cardCode, transactionId, owner, createdAt) VALUE (" 
						 + rent.getBike().getId() + "," + rent.getCurrentFee() +  "," + rent.getRentTime() + "," + rent.getStartDockId() + "," 
				         + rent.getEndDockId() + ",'" + response.get("cardCode") + "', '" + response.get("PAY_ID")+  "','" + response.get("owner") 
				         + "', '" + response.get("createdAt") + "');";
		stm.execute(query);
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
		String query = "DELETE FROM invoice WHERE id = " + id + ";";
		stm.executeQuery(query);
	}
	
	public static List<Invoice> getAllInvoices(Account account) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM invoice WHERE accountId = "+ account.getId() +";";
		ResultSet res = stm.executeQuery(query);
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		
		while (res.next()) {
			Invoice invoice = assignFromDB(res);
			invoiceList.add(invoice);
		}
		
		return invoiceList;
	}
}
