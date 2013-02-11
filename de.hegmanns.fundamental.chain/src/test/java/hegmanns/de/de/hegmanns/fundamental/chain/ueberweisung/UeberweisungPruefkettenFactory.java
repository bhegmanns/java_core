package hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung;

import java.util.ArrayList;
import java.util.List;

import hegmanns.de.de.hegmanns.fundamental.chain.PruefCommand;
import hegmanns.de.de.hegmanns.fundamental.chain.PruefContext;
import hegmanns.de.de.hegmanns.fundamental.chain.PruefResult;
import hegmanns.de.de.hegmanns.fundamental.chain.PruefkettenFactory;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;
import hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.pruefer.KontoVollmachtPruefCommand;
import hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.pruefer.KontoWeiterPruefCommand;

public class UeberweisungPruefkettenFactory implements
		PruefkettenFactory<Ueberweisung, MessageCode> {

	@Override
	public List<PruefCommand<Ueberweisung, MessageCode>> getPruefCommands() {
		List<PruefCommand<Ueberweisung, MessageCode>> commands = new ArrayList<PruefCommand<Ueberweisung, MessageCode>>();

		commands.add(new KontoVollmachtPruefCommand());
		commands.add(new KontoWeiterPruefCommand());

		// TODO: Hier ggf. weitere PruefCommands hinzufuegen
		
		return commands;
	}

	@Override
	public PruefContext<Ueberweisung> createPruefContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PruefResult<MessageCode> createPruefResult() {
		return new PruefResult<MessageCode>() {

			@Override
			public void setPruefungDurchgefuehrt(Boolean ausgefuehrt) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void add(MessageCode ergebnis) {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
