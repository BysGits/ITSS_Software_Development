package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCvvCodeTest {
	
	private RentBikeController rentBikeController;

	@BeforeEach
	void setUp() throws Exception {
		rentBikeController = new RentBikeController();
	}

	@ParameterizedTest
	@CsvSource({
		
	})
	void test(String cvvCode, boolean expected) {
		boolean isValid = rentBikeController.validateCvvCode(cvvCode);
		assertEquals(isValid, expected);
	}

}
