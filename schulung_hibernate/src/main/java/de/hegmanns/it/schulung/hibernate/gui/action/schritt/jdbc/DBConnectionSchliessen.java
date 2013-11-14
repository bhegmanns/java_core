package de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class DBConnectionSchliessen extends Schritt {

	public DBConnectionSchliessen(){
		super(new String[]{}, new String[]{DBConnectionErstellen.DB_CONNECTION}, new String[]{DBConnectionErstellen.DB_CONNECTION});
	}
	@Override
	protected void schrittAktionAusfuehren(Cache cache,
			SchrittResult schrittresult) {
		Connection dbConnection = cache.getFromCache(DBConnectionErstellen.DB_CONNECTION, Connection.class);
		
		try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection = null;
		}

	}

}
