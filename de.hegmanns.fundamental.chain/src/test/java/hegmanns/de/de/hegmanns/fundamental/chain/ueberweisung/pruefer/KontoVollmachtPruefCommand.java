package hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.pruefer;

import java.util.Map;

import hegmanns.de.de.hegmanns.fundamental.chain.PruefCommand;
import hegmanns.de.de.hegmanns.fundamental.chain.PruefContext;
import hegmanns.de.de.hegmanns.fundamental.chain.PruefResult;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;
import hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.Ueberweisung;

public class KontoVollmachtPruefCommand implements
		PruefCommand<Ueberweisung, MessageCode> {

	@Override
	public void execute(PruefContext<Ueberweisung> pruefContext,
			PruefResult<MessageCode> pruefResult) {
		Ueberweisung ueberweisung = pruefContext.getInstance();
		
		pruefResult.add(null);
	}

	@Override
	public Map<MessageCode, String> getMoeglichePruefergebnisse() {
		// TODO Auto-generated method stub
		return null;
	}

}
