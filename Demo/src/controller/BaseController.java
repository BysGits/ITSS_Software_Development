package controller;

public class BaseController {
	public Bike checkBikeInDock(Bike bike) {
		return DockList.getDock().getBike();
	}

	
}
