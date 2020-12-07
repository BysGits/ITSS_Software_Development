package controller;

import entity.bike.*;

public class RentBikeController extends BaseController {
	
	public boolean validateCardCode (String cardCode) {
		
		// Check the cardCode has 17 characters
		if (cardCode.length() != 17) return false;
		
		return true;
	}
	
}
