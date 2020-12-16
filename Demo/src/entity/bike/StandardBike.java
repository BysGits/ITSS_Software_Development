package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.db.ECOBIKEDB;

public class StandardBike extends BikeType {

	public StandardBike() throws SQLException {
		
	}
	
//	public StandardBike(int id, String type, String barcode, int pedals, int saddles, int rearSeats, int rentingFee, int depositFee, boolean state, int dockId) throws SQLException {
//		super(id, type, barcode, pedals, saddles, rearSeats, rentingFee, depositFee, state, dockId);
//	}
	
	@Override
	public List<BikeType> getBikeByDockId(int dockId) throws SQLException {
		Statement stm = ECOBIKEDB.getConnection().createStatement();
		String query = "SELECT * FROM BIKE WHERE typeId = 1 AND dockId = " + dockId + ";";
		ResultSet res = stm.executeQuery(query);
		ArrayList<BikeType> bikeList = new ArrayList<BikeType>();
		
		while (res.next()) {
			BikeType bike = createNewBikeFromDB(res);
			bikeList.add(bike);
		}
		return bikeList;
	}
}
