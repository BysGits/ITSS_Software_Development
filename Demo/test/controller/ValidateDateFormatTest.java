package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateDateFormatTest {

	private RentBikeController rentBikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		rentBikeController = new RentBikeController();
	}

	@ParameterizedTest
	@CsvSource({
		"123-12-2020,false",
		"12/12/2020,true",
		"12-12-2020,false",
		"32/01/2020,false",
		"30/02/2020,false",
		"29/02/2020,true",
		"18/13/2020,false"
	})
	void test(String date, boolean expected) {
		boolean isValid = rentBikeController.validateDeliveryTime(date);
		assertEquals(expected, isValid);
	}

}
