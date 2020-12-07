package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateBarcodeTest {
	
	private RentBikeController rentBikeController;

	@BeforeEach
	void setUp() throws Exception {
		rentBikeController = new RentBikeController();
	}

	@ParameterizedTest
	@CsvSource({
		
	})
	
	void test(String barcode, boolean expected) {
		boolean isValid = rentBikeController.validateBarcode(barcode);
		assertEquals(isValid, expected);
	}

}
