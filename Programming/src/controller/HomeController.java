package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import entity.account.Account;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.invoice.Invoice;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import entity.rent.Rent;
import entity.utils.AccountHandler;
import entity.utils.BikeHandler;
import entity.utils.DockHandler;
import entity.utils.InvoiceHandler;
import subsystem.interbank.InterbankSubsystemController;
import utils.Configs;

public class HomeController {
	
	public HomeController() {}
	
	private int batteryTime = 60*10;
	/**
	 * this method gets all Dock in DB and return back to home to display (HomeScreenHandler)
	 * @return List[Media]
	 * @throws SQLException
	 */
	public List<Dock> getAllDocks() throws SQLException {
		return DockHandler.getAllDocks();
	}
	
	/**
	 * this method get dock in DB by id and return back to home to display 
	 * BikeInfoHandler
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Dock getDockById(int id) throws SQLException {
		return DockHandler.getDockById(id);
	}
	
	public Dock getDockByName(String name) throws SQLException {
		return DockHandler.getDockByName(name);
	}
	
	public Bike getBikeById(int id) throws SQLException {
		return BikeHandler.getBikeById(id);
	}
	/**
	 * this method get all bikes in a dock and return back to the Dock Screen to display
	 * DockScreenHandler
	 * @param dockId
	 * @return
	 * @throws SQLException
	 */
	public List<Bike> getAllBikesByDockId(int dockId) throws SQLException {
		return BikeHandler.getBikeByDockId(dockId);
	}
	
	/**
	 * this method get bike with a specified dock and return back to the screen to display
	 * HomeScreenHandler
	 * @param barcode
	 * @return
	 * @throws SQLException
	 */
	
	public Bike getBikeByBarcode(String barcode) throws SQLException {
		return BikeHandler.getBikeByBarcode(barcode);
	}
	
	/**
	 * this method searches the docks contains the keyword and return back to the screen to display
	 * HomeScreenHandler
	 * @param tmp
	 * @return
	 * @throws SQLException
	 */
	public List<Dock> searchDock(String input) throws SQLException {
		return DockHandler.searchDock(input);
	}
	
	/** 
	 * this method gets all invoices from database
	 * @return
	 * @throws SQLException
	 */
	public List<Invoice> getAllInvoices(Account account) throws SQLException {
		return InvoiceHandler.getAllInvoices(account);
	}

	
	/**
	 * 
	 * @param rent
	 * @throws SQLException
	 */
	public int calculateFee(Rent rent) {
		// Standard rent: 0
		int rentingTime = rent.getRentTime();
		int currentFee = rent.getCurrentFee();
		int tmp = 0;
		switch (rent.getType()) {
		case 1:
			if (rentingTime < 10) {
				currentFee = 0;
			} else {
				currentFee = 10000;
				if (rentingTime > 30) {
					tmp = (rentingTime - 30)%15;
					if (tmp == 0) {
						currentFee += ((rentingTime - 30)/15)*3000;
					} else {
						currentFee += ((rentingTime - 30)/15 + 1)*3000;
					}
				}
			}
			break;
		case 2:
			currentFee = 200000;
			if (rentingTime < 12*60) {
				if (rentingTime % 60 == 0) {
					currentFee = 200000 - (12 - rentingTime/60)*10000;
				} else {
					currentFee = 200000 - (12 - rentingTime/60 + 1)*10000;
				}
				
			}
			
			if (rentingTime > 24*60) {
				if ((rentingTime - 24*60)%15 == 0) {
					currentFee += ((rentingTime - 24*60)/15)*2000;
				} else {
					currentFee += ((rentingTime - 24*60)/15 + 1)*2000;
				}
			}
			break;
		default:
			break;
		}
		
		if (rent.getBike().getTypeId() == 2 || rent.getBike().getTypeId() == 3) {
			return (int) ((int) currentFee*1.5);
		}
		
		if (rent.getBike().getTypeId() == 4) {
			return (int) ((int) currentFee*2);
		}
		return currentFee;
	}
	
	public int updateBattery(Rent rent) {
		
		if (rent.getBike().getTypeId() == 2 || rent.getBike().getTypeId() == 4) {
			double usedBattery = (double) rent.getRentTime()/batteryTime;
			int currentBattery = (int)(rent.getBike().getBattery() - usedBattery*100 + 1);
			if (currentBattery < 0) currentBattery = 0;
			return currentBattery;
		}
		
		return -1;
	}
	
//	public void checkBikeAfterReturn(Rent rent, int dockId) throws SQLException {
//		Bike bike = rent.getBike();
//		
//		if (bike.getBattery() <= 10 && (bike.getTypeId() == 2 || bike.getTypeId() == 4)) {
//			bike.setState(true);
//		} else {
//			bike.setState(false);
//		}
		
		
//		public void updateBike(Rent rent, int dockId) throws SQLException {
//			Bike bike = rent.getBike();
//			
//			if (bike.getTypeId() == 2 || bike.getTypeId() == 4) {
//				BikeHandler.updateBike(bike, dockId, updateBattery(rent));
//			} else {
//				BikeHandler.updateBike(bike, dockId);
//			}
//			
//		}
//	}
	
	public void updateBikeState(Rent rent, int newDockId) throws SQLException {
		Bike bike = rent.getBike();
		int newBattery = updateBattery(rent);
		

		if (bike.getTypeId() == 2 || bike.getTypeId() == 4) {
			if (bike.getBattery() <= 20) {
				BikeHandler.updateBike(bike, false);
				BikeHandler.updateBike(bike, newDockId, newBattery);
			} else {
				BikeHandler.updateBike(bike, true);
				BikeHandler.updateBike(bike, newDockId, newBattery);
			}
		} else {
			BikeHandler.updateBike(bike, newDockId);
		}
	}
	
	public void updateRentingState(Bike bike, boolean state) throws SQLException {
		BikeHandler.updateBike(bike, state);
	}

    public boolean checkIfAccountExists(String username, String password) throws SQLException {
		
		Account account = AccountHandler.getAccount(username, password);
		
		if (account == null) return false;
		
		else {
			return true;
		}
	}
	
	public Account getAccount(String username, String password) throws SQLException {
		return AccountHandler.getAccount(username, password);
	}
	
	public boolean newAccount(String username, String password1, String password2) throws SQLException {
		if (password1.equals(password2)) {
			AccountHandler.newAccount(username, password1);
			return true;
		} 
		
		return false;
	}
	
	public void updateAccount(Account account) throws SQLException {
		AccountHandler.updateAccountInfo(account);
	}
	
	
	public Map<String,String> resetBalance(CreditCard card) throws IOException {
		InterbankSubsystemController ctrl = new InterbankSubsystemController();
		PaymentTransaction pt = ctrl.reset(card);
		
		Map<String,String> result = new Hashtable<String,String>();
		if(pt.getErrorCode().equals("01")) {
			result.put("result","Invalid card");
			return result;
		}
		result.put("balance", String.valueOf(pt.getBalance()));
		return result;
	}
	
	public void updateUsingState(Account account, boolean state) throws SQLException {
		AccountHandler.updateUsingState(account, state);
	}


	public boolean checkIfBikeisAvailable(Bike bike) throws SQLException {
		return BikeHandler.getBikeById(bike.getId()).isAvailable();
	}
	
	public CreditCard CreditCard(String cardCode, String owner, String cvv, String dateExpired) {
		return new CreditCard(cardCode, owner, Integer.parseInt(cvv), dateExpired);
	}

	
}
