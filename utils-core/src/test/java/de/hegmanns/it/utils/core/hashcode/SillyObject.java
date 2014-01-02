package de.hegmanns.it.utils.core.hashcode;

import org.apache.commons.lang.builder.EqualsBuilder;

public class SillyObject {

	private String name;
	private int iq;
	
	public SillyObject(String name, int iq)
	{
		this.name = name;
		this.iq = iq;
	}

	@Override
	public int hashCode() {
		return name.length() + iq;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SillyObject)
		{
			SillyObject ref = (SillyObject)obj;
			return new EqualsBuilder().append(this.name, ref.name).isEquals();
		}
		else
		{
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIq() {
		return iq;
	}

	public void setIq(int iq) {
		this.iq = iq;
	}
	
	
	
}
