package de.hegmanns.it.schulung.dbverbindung.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hegmanns.it.utils.core.Pair;

public class SessionTxPair {

	private Pair<Session, Transaction> sessionTxPair;
	
	public SessionTxPair(Session session)
	{
		sessionTxPair = initPair(session);
	}
	
	private Pair<Session, Transaction> initPair(Session session)
	{
		return new Pair<Session, Transaction>(session, session.beginTransaction());
	}
	
	private synchronized void reinit(){
		sessionTxPair = new Pair<Session, Transaction>(sessionTxPair.getFirst(), sessionTxPair.getFirst().beginTransaction());
	}
	
	public SessionTxPair commit()
	{
		sessionTxPair.getSecond().commit();
		reinit();
		
		return this;
	}
	
	public SessionTxPair rollback(){
		sessionTxPair.getSecond().rollback();
		reinit();
		
		return this;
	}
	
	public SessionTxPair close(){
		sessionTxPair.getFirst().close();
		
		return this;
	}
	
	public Session getSession(){
		return sessionTxPair.getFirst();
	}

	@Override
	protected void finalize() throws Throwable {
		close();
	}
	
	public SessionTxPair save(Object object)
	{
		sessionTxPair.getFirst().save(object);
		
		return this;
	}
	
	
}
