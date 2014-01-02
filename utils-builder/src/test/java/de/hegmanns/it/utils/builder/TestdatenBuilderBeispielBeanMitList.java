package de.hegmanns.it.utils.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.hegmanns.it.utils.builder.MemberDefault;
import de.hegmanns.it.utils.builder.Builder;
import de.hegmanns.it.utils.core.Pair;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielAggregat;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielBeanMitList;

public class TestdatenBuilderBeispielBeanMitList extends
		Builder<BeispielBeanMitList> {
	
	public static final TestdatenBuilderBeispielBeanMitList BUILDER = TestdatenBuilderBeispielBeanMitList.get();
	
	@MemberDefault
	public Pair<String, Object> einBigDecimal = new Pair("einBigDecimal", BigDecimal.TEN);
	
	@MemberDefault
	public Pair<String, Object> einInt = new Pair("einInt", new Integer(-10));
	
	@MemberDefault
	public Pair<String, Object> einBoolean = new Pair("einBoolean", Boolean.TRUE);
	
	@MemberDefault
	public Pair<String, Object> bbb = new Pair("beispielAggregat", new BeispielAggregat());
	
	@MemberDefault
	public List<BeispielAggregat> beispielAggregate = new ArrayList<>();
	
	
	
	public static TestdatenBuilderBeispielBeanMitList get()
	{
		return new TestdatenBuilderBeispielBeanMitList();
	}

	protected TestdatenBuilderBeispielBeanMitList() {
		super(BeispielBeanMitList.class);
	}
	
	public TestdatenBuilderBeispielBeanMitList einBigDecimal(BigDecimal bigDecimal)
	{
		return this;
	}
	
	public TestdatenBuilderBeispielBeanMitList einInt(int einInt){
		return this;
	}
	
	public TestdatenBuilderBeispielBeanMitList einboolean(boolean einBoolean){
		return this;
	}
	
	public TestdatenBuilderBeispielBeanMitList beispielAggregat(BeispielAggregat beispielAggregat)
	{
		return this;
	}
	
	public TestdatenBuilderBeispielAggregat beispielAggregat()
	{
		TestdatenBuilderBeispielAggregat builder = TestdatenBuilderBeispielAggregat.get();
		super.put("beispielAggregat", builder);
		return builder;
	}
	
	public TestdatenBuilderBeispielBeanMitList addToBeispielAggregate(BeispielAggregat beispielAggregat){
		ArrayList<BeispielAggregat> aggregate = super.getMember(ArrayList.class, "beispielAggregate");
		if (aggregate == null)
		{
			aggregate = new ArrayList<>();
			super.put("beispielAggregate", aggregate);
		}
		aggregate.add(beispielAggregat);
		return this;
	}
	
	public TestdatenBuilderBeispielAggregat addToBeispielAggregate()
	{
		TestdatenBuilderBeispielAggregat builder = TestdatenBuilderBeispielAggregat.get();
		
		return builder;
	}

}
