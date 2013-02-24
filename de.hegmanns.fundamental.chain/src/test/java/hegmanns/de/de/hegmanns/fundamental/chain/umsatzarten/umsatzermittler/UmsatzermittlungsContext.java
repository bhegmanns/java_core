package hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.umsatzermittler;

import java.util.Date;

import hegmanns.de.de.hegmanns.fundamental.chain.resolving.CommonCmdContext;
import hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten.Vertragnehmer;

public class UmsatzermittlungsContext extends
		CommonCmdContext<Vertragnehmer> {

	
	public UmsatzermittlungsContext(Vertragnehmer vertragnehmer){
		super(vertragnehmer);
	}

	public Date getPruefDatum()
	{
		return gatherValue(Date.class);
	}
	
//	public Depot getDepot()
//	{
//		return gatherValue(Depot.class);
//	}
}
