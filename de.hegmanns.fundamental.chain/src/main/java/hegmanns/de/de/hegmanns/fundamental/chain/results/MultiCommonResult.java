package hegmanns.de.de.hegmanns.fundamental.chain.results;

import hegmanns.de.de.hegmanns.fundamental.chain.ReadableAusfuehrungsResult;

import java.util.ArrayList;
import java.util.List;

public class MultiCommonResult<E> implements ReadableAusfuehrungsResult<E> {

	private List<E> results;
	private Boolean ausgefuehrt = Boolean.TRUE;
	
	public MultiCommonResult(){
		results = new ArrayList<E>();
	}
	@Override
	public void setAusgefuehrt(Boolean ausgefuehrt) {
		this.ausgefuehrt = ausgefuehrt;
	}

	@Override
	public void add(E ergebnis) {
		results.add(ergebnis);
	}
	@Override
	public Boolean isAusgefuehrt() {
		return ausgefuehrt;
	}
	@Override
	public List<E> getResults() {
		return results;
	}

}
