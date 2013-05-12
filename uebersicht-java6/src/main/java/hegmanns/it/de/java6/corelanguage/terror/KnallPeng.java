package hegmanns.it.de.java6.corelanguage.terror;

/**
 * Ein Knall in Form einer Exception.
 * 
 * @author B. Hegmanns
 *
 */
public class KnallPeng extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param hinweistext der Hinweistext des Fehlers
	 */
	public KnallPeng(String hinweistext)
	{
		super(hinweistext);
	}
}
