package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import entity.dock.Dock;

class validateDock {

	private HomeController hCtrl;

	@BeforeEach
	void setUp() throws Exception {
		hCtrl = new HomeController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"Center,2",
		"Pubgame,1",
		"center,0"
	})

	void test(String tmp, String result ) throws SQLException {
		List<Dock> dockList = hCtrl.searchDock(tmp);
		assertEquals(dockList.size(), Integer.parseInt(result));
		//fail("Not yet implemented");
	}

}
