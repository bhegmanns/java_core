package hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.pruefer;

import java.util.Map;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsCommand;
import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsContext;
import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsResult;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;
import hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.Ueberweisung;

public class KontoVollmachtPruefCommand implements
		AusfuehrungsCommand<Ueberweisung, MessageCode> {

	@Override
	public void execute(AusfuehrungsContext<Ueberweisung> pruefContext,
			AusfuehrungsResult<MessageCode> pruefResult) {
		Ueberweisung ueberweisung = pruefContext.getInstance();
		
		pruefResult.add(null);
	}

	@Override
	public Map<MessageCode, String> getMoeglicheErgebnisse() {
		// TODO Auto-generated method stub
		return null;
	}

}
