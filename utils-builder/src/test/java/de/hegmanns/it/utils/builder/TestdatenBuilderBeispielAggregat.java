package de.hegmanns.it.utils.builder;

import java.util.Date;



import de.hegmanns.it.utils.builder.MemberDefault;
import de.hegmanns.it.utils.builder.Builder;
import de.hegmanns.it.utils.core.Pair;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielAggregat;


public class TestdatenBuilderBeispielAggregat extends Builder<BeispielAggregat>{

	public static final TestdatenBuilderBeispielAggregat BUILDER = TestdatenBuilderBeispielAggregat.get();
	private static final String N_NAME = "aggregatName";
	private static final String N_ALTER_IN_MINUTEN = "alterInMinuten";
	
	@MemberDefault
	public Date zeitpunktErstellung = new Date();
	
	@MemberDefault
	public Pair<String, Object> aaa = new Pair(N_NAME, "NN");
	
	@MemberDefault
	public Pair<String, Object> bbb = new Pair(N_ALTER_IN_MINUTEN, new Integer(0));
	
	public TestdatenBuilderBeispielAggregat()
	{
		super(BeispielAggregat.class);
	}
	
	public static TestdatenBuilderBeispielAggregat get()
	{
		return new TestdatenBuilderBeispielAggregat();
	}
	
	public TestdatenBuilderBeispielAggregat name(String name)
	{
		super.put("aggregatName", name);
		return this;
	}
	
	public TestdatenBuilderBeispielAggregat zeitpunktErstellung(Date datum)
	{
		super.put("zeitpunktErstellung", datum);
		return this;
//		Minutes differenz = Minutes.minutesBetween(new DateTime(new Date().getTime()), new DateTime(datum.getTime()));
//		
//		return zeitpunktErstellungVorMinuten(differenz.getMinutes());
	}
	
	public TestdatenBuilderBeispielAggregat zeitpunktErstellungVorMinuten(int minuten)
	{
		super.put("alterInMinuten", new Integer(minuten));
		return this;
	}

	
}
