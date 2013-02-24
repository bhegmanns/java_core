package hegmanns.de.de.hegmanns.fundamental.chain;

import java.util.List;

public interface AusfuehrungsAdvice<T, E extends AusfuehrungsContext<T>, R, A extends AusfuehrungsResult<R>> {

	public E createAusfuehrungscontext();
	
	public A erstelleAusfuehrungsresult();
	
	public boolean chainFortsetzen(ReadableAusfuehrungsResult<R> aktuellesResult);
	
	public void befuelleResult(AusfuehrungsResult<R> result, E ausfuehrungscontext, List<ReadableAusfuehrungsResult<R>> ausfuehrungsresults);
}
