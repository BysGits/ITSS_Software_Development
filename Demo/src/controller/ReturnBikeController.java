/**
 * 
 */
package controller;

/**
 * @author tkend
 *
 */
public class ReturnBikeController {
	public boolean validateCardCode(String cardCode) {
		
		// check the cardCode has 17 characters
		if (cardCode.length() != 17) return false;
		return true;
	}
	
	public boolean validateCvvCode(String cvvCode) {
		
		// check the cvvCode has 3 digits
		if (cvvCode.length() != 3) return false;
		
		// check the cvvCode 
		
		try {
			Integer.parseInt(cvvCode);
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
