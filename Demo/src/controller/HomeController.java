package controller;

import java.sql.SQLException;
import java.util.List;

import entity.bike.Bike;
import entity.dock.Dock;
import entity.rent.Rent;

public class HomeController extends BaseController {

	/**
	 * this method gets all Dock in DB and return back to home to display
	 * @return List[Media]
	 * @throws SQLException
	 */
	public List getAllDocks() throws SQLException {
		return new Dock().getAllDocks();
	}
	
	/**
	 * this method get dock in DB by id and return back to home to display
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Dock getDockById(int id) throws SQLException {
		return new Dock().getDockById(id);
	}
	
	/**
	 * this method get all bikes in a dock and return back to the Dock Screen to display
	 * @param dockId
	 * @return
	 * @throws SQLException
	 */
	public List getAllBikesByDockId(int dockId) throws SQLException {
		return new Bike().getBikeByDockId(dockId);
	}
	
	/**
	 * this method get bike with a specified dock and return back to the screen to display
	 * @param barcode
	 * @return
	 * @throws SQLException
	 */
	
	public Bike getBikeByBarcode(String barcode) throws SQLException {
		return new Bike().getBikeByBarcode(barcode);
	}
	
	/**
	 * this method searches the docks contains the keyword and return back to the screen to display
	 * @param tmp
	 * @return
	 * @throws SQLException
	 */
	public List<Dock> searchDock(String tmp) throws SQLException {
		return new Dock().searchDock(tmp);
	}
	
	public Rent newRent(Bike bike) throws SQLException {
		return new Rent(bike);
	}
	
	public void returnBike(Rent rent, String name) throws SQLException {
		Dock dock = Dock.getDockByName(name);
		System.out.println("ChECK");
		rent.getBike().setNewDock(dock.getId());
	}
	
	public void createInvoice(Rent rent) throws SQLException {
		
	}
}
