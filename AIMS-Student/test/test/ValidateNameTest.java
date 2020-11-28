package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceRushOrderController;

class ValidateNameTest {
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"Dao Hong Quan,true",
		"1 Dai Co Viet,false",
		"Vũ C.Duy,true",
		"K.Dinh. 123,false"
	})
	@Test
	void test(String name, boolean expected) {
		boolean isValid = placeRushOrderController.validateName(name);
		assertEquals(expected, isValid);
		//fail("Not yet implemented");
	}

}
