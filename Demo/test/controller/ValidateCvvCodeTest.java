package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCvvCodeTest {

	private ReturnBikeController returnBikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		returnBikeController = new ReturnBikeController();
	}

	@ParameterizedTest
	@CsvSource({
		"123, true",
		"a12, false"
	})
	
	void test(String cvvCode, boolean expected) {
		boolean isValid = returnBikeController.validateCvvCode(cvvCode);
		assertEquals(expected, isValid);
	}

}
