package entity.dock;

import java.util.ArrayList;
import java.util.List;

public class DockList {
	
	private List<Dock> listDock;
	private static DockList dockInstance;
	
	public static DockList getDock() {
		if(dockInstance == null) dockInstance = new DockList();
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
