package de.hegmanns.it.utils.core.function;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Realisierung einer Referenz.
 * 
 * @author Bernd
 *
 * @param <T>
 */
public class Ref<T extends Serializable> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private T value;

	public Ref(T value)
	{
		this.value = value;
	}
	
	public Ref()
	{
		
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(this, obj).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(value).toString();
	}
	
}
