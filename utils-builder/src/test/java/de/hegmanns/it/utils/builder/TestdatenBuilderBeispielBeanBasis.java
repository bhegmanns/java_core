package de.hegmanns.it.utils.builder;

import java.math.BigDecimal;

import de.hegmanns.it.utils.builder.MemberDefault;
import de.hegmanns.it.utils.builder.Builder;
import de.hegmanns.it.utils.core.Pair;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielAggregat;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielBeanBasis;

public class TestdatenBuilderBeispielBeanBasis extends
		Builder<BeispielBeanBasis> {

	public static final TestdatenBuilderBeispielBeanBasis BUILDER = TestdatenBuilderBeispielBeanBasis.get();
	@MemberDefault
	public Pair<String, Object> einBigDecimal = new Pair("einBigDecimal", BigDecimal.TEN);
	
	@MemberDefault
	public Pair<String, Object> einInt = new Pair("einInt", new Integer(-10));
	
	@MemberDefault
	public Pair<String, Object> einBoolean = new Pair("einBoolean", Boolean.TRUE);
	
	@MemberDefault
	public Pair<String, Object> bbb = new Pair("beispielAggregat", new BeispielAggregat());
	
	// wuerde auch gehen ...
//	public BeispielAggregat beispielAggregat = new BeispielAggregat();
	
	public TestdatenBuilderBeispielBeanBasis(){
		super(BeispielBeanBasis.class);
	}
	public static TestdatenBuilderBeispielBeanBasis get(){
		return new TestdatenBuilderBeispielBeanBasis();
	}
	
	public TestdatenBuilderBeispielBeanBasis einBigDecimal(BigDecimal value)
	{
		super.put("einBigDecimal", value);
		return this;
	}
	
	public TestdatenBuilderBeispielBeanBasis einInt(int value)
	{
		super.put("einInt", value);
		return this;
	}
	
	public TestdatenBuilderBeispielBeanBasis einBoolean(boolean value)
	{
		super.put("einBoolean", value);
		return this;
	}
	
	public TestdatenBuilderBeispielBeanBasis beispielAggregat(BeispielAggregat aggregat)
	{
		super.put("beispielAggregat", aggregat);
		return this;
	}
	
	private TestdatenBuilderBeispielAggregat aggregatBuilder = null;
	public TestdatenBuilderBeispielAggregat beispielAggregat()
	{
		aggregatBuilder = TestdatenBuilderBeispielAggregat.get();
		super.put("beispielAggregat", aggregatBuilder);
		
		return aggregatBuilder;
	}
	
	

}
