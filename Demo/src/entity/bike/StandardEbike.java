package entity.bike;

import java.sql.SQLException;

public class StandardEbike extends BikeType {
	
	private int battery;
	
	public StandardEbike() throws SQLException {
		
	}
	
//	public StandardEbike(int id, int pedals, int saddles, int rearSeats, int rentingFee, int depositFee, boolean state, int battery) throws SQLException {
//		super(id, pedals, saddles, rearSeats, rentingFee, depositFee, state);
//		this.battery = battery;
//	}
}
