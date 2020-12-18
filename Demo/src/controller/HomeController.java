package controller;

import java.sql.SQLException;
import java.util.List;

import entity.bike.BikeType;
import entity.dock.Dock;

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
	 * this method get all bikes in a dock and return back to the Dock Screen to display
	 * @param dockId
	 * @return
	 * @throws SQLException
	 */
	public List getAllBikesByDockId(int dockId) throws SQLException {
		return new BikeType().getBikeByDockId(dockId);
	}
	
	/**
	 * this method get bike with a specified dock and return back to the screen to display
	 * @param barcode
	 * @return
	 * @throws SQLException
	 */
	
	public BikeType getBikeByBarcode(String barcode) throws SQLException {
		return new BikeType().getBikeByBarcode(barcode);
	}
	
	public List<Dock> searchDock(String tmp) throws SQLException {
		return new Dock().searchDock(tmp);
	}
}
