package controller;

import java.sql.SQLException;
import java.util.Arrays;

import entity.bike.BikeType;

public class RentBikeController extends BaseController {
	
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
	
	public boolean validateBarcode(String barcode) {
		if (barcode.length() != 6) return false;
		return true;
	}
	
	public boolean validateDeliveryTime(String dateString) {
		try {
			String[] delieveryDate = dateString.split("/");
			int date = Integer.parseInt(delieveryDate[0]);
			int month = Integer.parseInt(delieveryDate[1]);
			int year = Integer.parseInt(delieveryDate[2]);
			int[] thirtyOneDayMonths = {1,3,5,7,8,10,12};
			
			if(!(1 <= month && month <= 12) || !(1 <= date && date <= 31)) { 
				return false;
			}else {
				if((!Arrays.stream(thirtyOneDayMonths).anyMatch(i->i==month))&& (date == 31)){
					return false;
				}
				else if(month == 2 && date > 28) {
					if (date == 29) {
						if(year % 4 == 0) {
							if(year%100 == 0) {
								if(year%400 == 0) {
									return true;
								}else {
									return false;
								}
							}else {
								return true;
							}
						}else {
							return false;
						}
					}else {
						return false;
					}
				}
				else {
					return true;
				}
			}
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	
}
