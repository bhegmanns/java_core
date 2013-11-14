package de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class DBOrderSchieben extends Schritt {
	
	public static final String ANZAHL_JAHRE = "ANZAHL_JAHRE";
	public static final String ANZAHL_TAGE = "ANZAHL_TAGE";
	
	private int anzahlTage;
	private int anzahlJahre;
	
	public DBOrderSchieben(){
		super(new String[]{}, new String[]{DBConnectionErstellen.DB_CONNECTION, DBOrderDisplay.ORDER_ID, ANZAHL_JAHRE, ANZAHL_JAHRE}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache,
			SchrittResult schrittresult) {
		Connection dbConnection = cache.getFromCache(DBConnectionErstellen.DB_CONNECTION, Connection.class);
		setAnzahlFromCache(cache);
		
		try{
			Statement st = dbConnection.createStatement();
			st.executeUpdate("update orderkern.wp_order o set o.zeitpunkterstellung = o.zeitpunkterstellung + " + (anzahlTage) + " + " + (anzahlJahre * 365));
			
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	private void setAnzahlFromCache(Cache cache)
	{
		String tage = cache.getFromCache(ANZAHL_TAGE, String.class);
		String jahre = cache.getFromCache(ANZAHL_JAHRE, String.class);
		
		
		anzahlTage = parseIntoInt(tage);
		anzahlJahre = parseIntoInt(jahre);
	}
	
	private int parseIntoInt(String value)
	{
		int wert = 0;
		
		try{
		wert = Integer.parseInt(value);
		}catch(Exception e){}
		return wert;
	}
	
}
