package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardCodeTest {

	private RentBikeController rentBikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		rentBikeController = new RentBikeController();
	}

	@ParameterizedTest
	@CsvSource({
		"abc, false"
	})
	
	
	void test(String cardCode, boolean expected) {
		boolean isValid = rentBikeController.validateCardCode(cardCode);
		assertEquals(isValid, expected);
	}

}
