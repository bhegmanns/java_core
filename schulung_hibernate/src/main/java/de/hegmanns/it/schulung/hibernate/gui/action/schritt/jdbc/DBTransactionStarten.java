package de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class DBTransactionStarten extends Schritt {

	public static final String SAVEPOINT = "SAVEPOINT";
	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		// TODO Auto-generated method stub
		Connection connection = cache.getFromCache(DBConnectionErstellen.DB_CONNECTION, Connection.class);
		try {
			Savepoint savepoint = connection.setSavepoint();
			cache.setIntoCache(SAVEPOINT, savepoint);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
