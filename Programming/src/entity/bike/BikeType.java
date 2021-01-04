package entity.bike;

public class BikeType {
	
	private int typeId;
	private String type;
	private int pedals;
	private int saddles;
	private int rearSeats;
	private int depositFee;
	
	public BikeType() {}
	
	public BikeType(int typeId, String type, int pedals, int saddles, int rearSeats, int depositFee) {
		this.typeId = typeId;
		this.type = type;
		this.pedals = pedals;
		this.saddles = saddles;
		this.rearSeats = rearSeats;
		this.depositFee = depositFee;
	}

	public String getFeatures() {
		switch(getTypeId()) {
		case 1 : case 2: 
			return getPedals() + " pair of pedals, " + getSaddles() + " saddle, " + getRearSeats() + " rear seat.";
		case 3 : case 4: 
			return getPedals() + " pairs of pedals, " + getSaddles() + " saddles, " + getRearSeats() + " rear seat.";
		default:
			return null;
		}
	}
	// getter and setter
	public int getTypeId() {
		return typeId;
	}

	public BikeType setTypeId(int typeId) {
		this.typeId = typeId;
		return this;
	}

	public String getType() {
		return type;
	}

	public BikeType setType(String type) {
		this.type = type;
		return this;
	}

	public int getPedals() {
		return pedals;
	}

	public BikeType setPedals(int pedals) {
		this.pedals = pedals;
		return this;
	}

	public int getSaddles() {
		return saddles;
	}

	public BikeType setSaddles(int saddles) {
		this.saddles = saddles;
		return this;
	}

	public int getRearSeats() {
		return rearSeats;
	}

	public BikeType setRearSeats(int rearSeats) {
		this.rearSeats = rearSeats;
		return this;
	}

	public int getDepositFee() {
		return depositFee;
	}

	public BikeType setDepositFee(int depositFee) {
		this.depositFee = depositFee;
		return this;
	}
	
	
	
	
	
}
