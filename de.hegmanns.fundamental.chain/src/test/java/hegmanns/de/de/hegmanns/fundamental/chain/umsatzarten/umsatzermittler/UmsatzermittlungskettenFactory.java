package hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler;

import java.util.List;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsAdvice;
import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsResult;
import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungskettenFactory;
import hegmanns.de.de.hegmanns.fundamental.chain.ReadableAusfuehrungsResult;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.Umsatzart;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.Vertragnehmer;

public class UmsatzermittlungskettenFactory
		extends
		AusfuehrungskettenFactory<Vertragnehmer, UmsatzermittlungsContext, Umsatzart, Umsatzartermittlungsergebnis> {

	public UmsatzermittlungskettenFactory(){
		addCmd(new AllgemeinerKontoErmittler());
		addCmd(new DepotErmittler());
	}

	@Override
	public AusfuehrungsAdvice<Vertragnehmer, UmsatzermittlungsContext, Umsatzart, Umsatzartermittlungsergebnis> createAusfuehrungsadvice(final Vertragnehmer instanz) {
		return new AusfuehrungsAdvice<Vertragnehmer, UmsatzermittlungsContext, Umsatzart, Umsatzartermittlungsergebnis>() {

			@Override
			public UmsatzermittlungsContext createAusfuehrungscontext() {
				return new UmsatzermittlungsContext(instanz);
			}

			@Override
			public Umsatzartermittlungsergebnis erstelleAusfuehrungsresult() {
				return new Umsatzartermittlungsergebnis();
			}

			@Override
			public boolean chainFortsetzen(
					ReadableAusfuehrungsResult<Umsatzart> aktuellesResult) {
				return true;
			}

			@Override
			public void befuelleResult(
					AusfuehrungsResult<Umsatzart> result,
					UmsatzermittlungsContext ausfuehrungscontext,
					List<ReadableAusfuehrungsResult<Umsatzart>> ausfuehrungsresults) {
				for (ReadableAusfuehrungsResult<Umsatzart> einzelnesResult : ausfuehrungsresults)
				{
					for (Umsatzart umsatzart : einzelnesResult.getResults())
					{
						result.add(umsatzart);
					}
				}
			}
		};
	}

}
