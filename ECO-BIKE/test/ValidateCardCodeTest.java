import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.RentBikeController;
import controller.ReturnBikeController;

class ValidateCardCodeTest {

	private RentBikeController rentBikeController;
	private ReturnBikeController returnBikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		rentBikeController = new RentBikeController();
		returnBikeController = new ReturnBikeController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"abc, false",
		"abasdfadfa, false"
	})


	void test(String cardCode, boolean expected) {
		boolean isValid = rentBikeController.validateCardCode(cardCode);
		assertEquals(expected, isValid);
	}

}