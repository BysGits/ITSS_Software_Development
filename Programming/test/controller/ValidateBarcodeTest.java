package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import entity.bike.Bike;

class ValidateBarcodeTest {
	
	private HomeController hCtrl;

	@BeforeEach
	void setUp() throws Exception {
		hCtrl = new HomeController();
	}

	@ParameterizedTest
	@CsvSource({
		"ST001,Standard Bike",
		"SE001,Standard E-Bike",
		"TB001,Twin Bike",
		"TE001,Twin E-Bike",
		"ST100,"
	})
	
	void test(String barcode, String result) throws SQLException {
		Bike bike = hCtrl.getBikeByBarcode(barcode);
		try {
			assertEquals(result, bike.getType());
		}catch(NullPointerException e) {
			assertEquals(result, null);
		}
	}

}
