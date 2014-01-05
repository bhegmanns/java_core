package de.hegmanns.it.utils.usermanagement;

import de.hegmanns.it.utils.builder.Builder;
import de.hegmanns.it.utils.builder.MemberDefault;
import de.hegmanns.it.utils.testdomain.usermanagement.NatuerlichePerson;

public class NaturlichePersonBuilder extends Builder<NatuerlichePerson> {
	
	@MemberDefault
	public String vorname = "NN";
	
	@MemberDefault
	public String nachname = "NN";

	protected NaturlichePersonBuilder() {
		super(NatuerlichePerson.class);
	}
	
	public NaturlichePersonBuilder vorname(String vorname)
	{
		super.put("vorname", vorname);
		return this;
	}
	
	public NaturlichePersonBuilder nachname(String nachname)
	{
		super.put("nachname", nachname);
		return this;
	}
	
	

}
