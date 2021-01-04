package entity.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

interface BaseHandler {
	
	public void assignFromDB(ResultSet res, Object obj);
}
