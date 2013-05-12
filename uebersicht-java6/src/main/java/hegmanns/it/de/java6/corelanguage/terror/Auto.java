package hegmanns.it.de.java6.corelanguage.terror;

import java.util.Observable;

/**
 * Repraesentiert ein Auto.
 * Am Ende muss die Instanz per {@link #close()} geschlossen werden.
 * 
 * Wenn das Auto ausgeschaltet wird, werden alle registrierten Observer
 * informiert.
 * 
 * @author B. Hegmanns
 */
public class Auto extends Observable implements AutoCloseable{
	
	boolean motorAn = false;
	boolean zerstoert = false;
	
	public void einschalten()
	{
		motorAn = true;
	}
	
	private void ausschalten()
	{
		this.setChanged();
		motorAn = false;
		this.notifyObservers();
	}

	/**
	 * Der Autoclose, der allerdings nur dann Sinn machen soll,
	 * wenn das auto nicht zerstoert ist.
	 * Wenn das Auto zerstoert ist, gibt's nichts mehr zum closen.
	 */
	@Override
	public void close() throws Exception {
		if (zerstoert)
		{
			throw new Exception("Auto ist zerstoert");
		}
		else
		{
			ausschalten();
		}
		
	}

}
