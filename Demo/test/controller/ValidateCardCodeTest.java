package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardCodeTest {

	private ReturnBikeController returnBikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		returnBikeController = new ReturnBikeController();
	}

	@ParameterizedTest
	@CsvSource({
		"1233, false"
	})
	

	void test(String cardCode, boolean expected) {
		boolean isValid = returnBikeController.validateCardCode(cardCode);
		assertEquals(expected, isValid);
	}

}
