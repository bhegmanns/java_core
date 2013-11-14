package de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class DBTransactionEnde extends Schritt{
	public static final String TX_COMMIT_JN = "TX_COMMIT_JN";
	
	private boolean doCommit = true;
	public DBTransactionEnde(){
		super(new String[]{}, new String[]{DBConnectionErstellen.DB_CONNECTION, TX_COMMIT_JN}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache,
			SchrittResult schrittresult) {
		
		String indikator = cache.getFromCache(TX_COMMIT_JN, String.class);
		if (indikator != null && indikator.toLowerCase().startsWith("n"))
		{
			doCommit = false;
		}

		try {
			if (doCommit)
			{
			cache.getFromCache(DBConnectionErstellen.DB_CONNECTION, Connection.class).commit();
			}
			else{
				cache.getFromCache(DBConnectionErstellen.DB_CONNECTION, Connection.class).rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
