package hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler;

import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.Umsatzart;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.Vertragnehmer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DepotErmittler implements Umsatzermittler {

	@Override
	public void execute(UmsatzermittlungsContext ausfuehrungsContext,
			Umsatzartermittlungsergebnis ausfuehrungsResult) {
		
		Vertragnehmer vn = ausfuehrungsContext.getInstance();
		Date datum = ausfuehrungsContext.getPruefDatum();

		// bla bla bla
		if (datum.before(null)){
			ausfuehrungsResult.add(Umsatzart.Depot);
		}
	}

	@Override
	public Map<Umsatzart, String> getMoeglicheErgebnisse() {
		Map<Umsatzart, String> map = new HashMap<Umsatzart, String>();
		
		map.put(Umsatzart.Depot, "");
		
		return map;
	}

	

	

}
