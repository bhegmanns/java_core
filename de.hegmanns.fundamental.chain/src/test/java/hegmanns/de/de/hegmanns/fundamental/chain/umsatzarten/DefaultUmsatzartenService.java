package hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten;

import hegmanns.de.de.hegmanns.fundamental.chain.Ausfuehrungskette;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler.AllgemeinerKontoErmittler;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler.DepotErmittler;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler.Umsatzartermittlungsergebnis;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler.UmsatzermittlungsContext;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler.UmsatzermittlungskettenFactory;

import java.util.List;

public class DefaultUmsatzartenService implements UmsatzartenService {

	@Override
	public List<Umsatzart> ermittleUmsatzarten(String haushaltsbuchId) {
		Vertragnehmer vertragnehmer = null;
		Ausfuehrungskette<Vertragnehmer, UmsatzermittlungsContext, Umsatzart, Umsatzartermittlungsergebnis> ermittlungskette = null;
		ermittlungskette = new Ausfuehrungskette<>();
		ermittlungskette.ausfuehrungsCmds = new UmsatzermittlungskettenFactory().getPruefCommands();
		ermittlungskette.ausfuehrungsAdvice = new UmsatzermittlungskettenFactory().createAusfuehrungsadvice(vertragnehmer);
		
		
		return ermittlungskette.doWork();
	}

}
