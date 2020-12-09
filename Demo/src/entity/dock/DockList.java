package entity.dock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.bike.Bike;

public class DockList {
	
	private List<Dock> listDock;
	private static Dock dockInstance;
	
	public static Dock getDock() {
		if(dockInstance == null)
			try {
				dockInstance = new Dock();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return dockInstance;
	}
	
	private DockList() {
		listDock = new ArrayList<Dock>();
		
	}
	
	public void addDock(Dock dock) {
		listDock.add(dock);
	}
	
	public void removeDock(Dock dock) {
		listDock.remove(dock);
	}
	
	public List getDockList() {
		return listDock;
	}
	
	public void emptyDockList() {
		listDock.clear();
	}
	
	public int getTotalDock() {
		int total=0;
		for(Object obj : listDock) {
			total ++;
		}
		return total;
	}


}
