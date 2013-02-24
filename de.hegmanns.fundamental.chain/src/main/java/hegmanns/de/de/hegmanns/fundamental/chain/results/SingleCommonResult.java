package hegmanns.de.de.hegmanns.fundamental.chain.results;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsResult;

public class SingleCommonResult<E> extends MultiCommonResult<E> implements AusfuehrungsResult<E> {


	public void set(E ergebnis) {
		this.add(ergebnis);
	}

}
