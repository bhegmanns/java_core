package hegmanns.it.de.java6.corelanguage.terror;

/**
 * Eine Autobombe, die auch anheftbar ist.
 * 
 * @author B. Hegmanns
 *
 */
public class Autobombe implements Anheftbar{
	
	/**
	 * Das Auto, an dem diese Autobombe angebracht ist.
	 */
	private Auto auto;

	@Override
	public void anheften(Auto auto) {
		this.auto = auto;
	}
	
	/**
	 * Zuenden der Autobombe.
	 * Zerstoert das Auto, an dem sie heftet und verursacht einen
	 * Knall in Form einer Exception.
	 */
	public void zuenden()
	{
		this.auto.zerstoert = true;
		throw new KnallPeng("Auto zerstoert");
	}

}
