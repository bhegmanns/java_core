package de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class DBOrderDisplay extends Schritt {

	public static final String ORDER_ID = "ORDER_ID";
	
	public DBOrderDisplay(){
		super(new String[]{}, new String[]{DBConnectionErstellen.DB_CONNECTION, ORDER_ID}, new String[]{});
	}
	@Override
	protected void schrittAktionAusfuehren(Cache cache,
			SchrittResult schrittresult) {
		Connection dbConnection = cache.getFromCache(DBConnectionErstellen.DB_CONNECTION, Connection.class);
		
		try{
		Statement st = dbConnection.createStatement();
		ResultSet resultSet = st.executeQuery("select * from orderkern.wp_order o where o.id = " + cache.getFromCache(ORDER_ID, String.class));
		displayResultSet(resultSet);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void displayResultSet(ResultSet resultset) 
	{
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		try{
		if (resultset.next())
		{
			System.out.println("Order:");
			System.out.println(" ID = " + resultset.getLong("ID"));
			System.out.println(" DEPOTNUMMER = " + resultset.getString("DEPOTNUMMER"));
			System.out.println(" VERSION = " + resultset.getLong("VERSION"));
			System.out.println(" ZEITPUNKTERSTELLUNG = " + dateFormat.format(resultset.getDate("ZEITPUNKTERSTELLUNG")));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				resultset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
