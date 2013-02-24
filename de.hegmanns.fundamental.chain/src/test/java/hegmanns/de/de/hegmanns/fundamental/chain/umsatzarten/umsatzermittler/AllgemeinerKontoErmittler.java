package hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler;

import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.Umsatzart;

import java.util.Map;

public class AllgemeinerKontoErmittler implements Umsatzermittler{

	@Override
	public void execute(UmsatzermittlungsContext ausfuehrungsContext,
			Umsatzartermittlungsergebnis ausfuehrungsResult) {


		ausfuehrungsResult.add(Umsatzart.Girokonto);
		ausfuehrungsResult.add(Umsatzart.TagegeldkontoPlus);
		
	}

	@Override
	public Map<Umsatzart, String> getMoeglicheErgebnisse() {
		// TODO Auto-generated method stub
		return null;
	}

}
