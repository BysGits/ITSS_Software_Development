package controller;

import entity.bike.Bike;
import entity.dock.DockList;

public class BaseController {
	public Bike checkBikeInDock(Bike bike) {
		return DockList.getDock().getBike();
	}

	
}
