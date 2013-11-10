package de.hegmanns.it.common.workflow.aufgabe.cache;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Eine Definition einer Cache-Konstante.
 * 
 * @author B. Hegmanns
 *
 * @param <T> der Typ des zur Konstanten gehoerenden Werts
 */
public class CacheKonstante<T> {

	private String name;
	private Class<T> typ;
	private T defaultValue;
	
	public static <T> CacheKonstante<T> create(Class<T> type, String name, T defaultvalue){
		CacheKonstante<T> konstante = new CacheKonstante<>();
		konstante.setTyp(type);
		konstante.setName(name);
		konstante.setDefaultValue(defaultvalue);
		
		return konstante;
	}
	
	public static <T> CacheKonstante<T> create(Class<T> type, String name)
	{
		return create(type, name, null);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public Class<T> getTyp() {
		return typ;
	}

	private void setTyp(Class<T> typ) {
		this.typ = typ;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	private void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	
}
