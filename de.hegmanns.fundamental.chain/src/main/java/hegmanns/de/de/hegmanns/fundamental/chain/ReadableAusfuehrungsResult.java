package hegmanns.de.de.hegmanns.fundamental.chain;

import java.util.List;

public interface ReadableAusfuehrungsResult<E> extends AusfuehrungsResult<E>{

	public Boolean isAusgefuehrt();
	
	public List<E> getResults();
}
