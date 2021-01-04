package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import entity.payment.CreditCard;

class validateCard {

	private RentBikeController rentCtrl;
	private int check;
	@BeforeEach
	void setUp() throws Exception {
		rentCtrl = new RentBikeController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"121319_group2_2020,Group 2,228,1125,40000,0",
		"121319_group2_2020,Group 1,228,1125,40000,1",
		"121319_group2_2020,Group 2,228,1125,4000000,2"
	})

	void test(String cardCode, String owner, String cvvCode, String dateExpired, String depositFee, String result) {
		CreditCard card = new CreditCard(cardCode, owner, Integer.parseInt(cvvCode), dateExpired);
		
		try {
			check = rentCtrl.validateCreditCard(card, Integer.parseInt(depositFee));
			assertEquals(check, Integer.parseInt(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//rentCtrl.resetBalance(card);
		
		//fail("Not yet implemented");
	}

}
