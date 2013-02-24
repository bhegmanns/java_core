package hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages;

import java.util.HashSet;
import java.util.Set;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsResult;

public class BusinessMessagePruefResult<E extends MessageCode> implements AusfuehrungsResult<E>{

	private Set<E> messageCodes = new HashSet<E>();
	private Boolean pruefDurchgefuehrt = Boolean.TRUE;
	
	@Override
	public void setAusgefuehrt(Boolean ausgefuehrt) {
		pruefDurchgefuehrt = ausgefuehrt;
	}

	@Override
	public void add(E ergebnis) {
		messageCodes.add(ergebnis);
	}

}
