package hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung;

import java.util.ArrayList;
import java.util.List;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsCommand;
import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsContext;
import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsResult;
import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungskettenFactory;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;
import hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.pruefer.KontoVollmachtPruefCommand;
import hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.pruefer.KontoWeiterPruefCommand;

public class UeberweisungPruefkettenFactory implements
		AusfuehrungskettenFactory<Ueberweisung, MessageCode> {

	@Override
	public List<AusfuehrungsCommand<Ueberweisung, MessageCode>> getPruefCommands() {
		List<AusfuehrungsCommand<Ueberweisung, MessageCode>> commands = new ArrayList<AusfuehrungsCommand<Ueberweisung, MessageCode>>();

		commands.add(new KontoVollmachtPruefCommand());
		commands.add(new KontoWeiterPruefCommand());

		// TODO: Hier ggf. weitere PruefCommands hinzufuegen
		
		return commands;
	}

	@Override
	public AusfuehrungsContext<Ueberweisung> createPruefContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AusfuehrungsResult<MessageCode> createPruefResult() {
		return new AusfuehrungsResult<MessageCode>() {

			@Override
			public void setAusgefuehrt(Boolean ausgefuehrt) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void add(MessageCode ergebnis) {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
