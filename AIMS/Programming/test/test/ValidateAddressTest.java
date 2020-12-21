package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceRushOrderController;

class ValidateAddressTest {
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"45 Australia St.Addition Hills, Binan, Laguna,true",
		"1 Dai Co Viet ^^,false",
		"135 N.Ha ,Dong Da,Ha Noi,true",
		"K.Dinh. 123,false"
	})
	@Test
	void test(String address, boolean expected) {
		boolean isValid = placeRushOrderController.validateAddress(address);
		assertEquals(expected, isValid);
		//fail("Not yet implemented");
	}

}
