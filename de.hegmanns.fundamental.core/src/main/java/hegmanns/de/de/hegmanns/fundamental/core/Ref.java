package hegmanns.de.de.hegmanns.fundamental.core;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Dient der Aufnahme einer beliebigen Referenz, beispielsweise in Methoden, damit in der Methode
 * ein Wert ueber den Parameter zurueck gegeben werden kann.
 * 
 * Diese Referenz ist nichts weiter als ein Datenhalter. Gleichheit und Hashcode dieser Instanz ergeben sich
 * ausschliesslich aus den enthaltenem Objekt.
 * 
 * @author B. Hegmanns
 *
 * @param <T> Typ der aufnehmenden Instanz
 */
public class Ref<T extends Serializable> {
	
	/**
	 * Das Objekt.
	 */
	private T value;
	
	/**
	 * Konstruktor ohne Objekt.
	 */
	public Ref(){}
	
	/**
	 * Konstruktor zur direkten Aufnahme eines Objekts.
	 * 
	 * @param value das Objekt
	 */
	public Ref(T value){
		this.value = value;
	}
	
	/**
	 * Setter fuer das Objekt.
	 * 
	 * @param value das Objekt
	 */
	public void set(T value){
		this.value = value;
	}
	
	/**
	 * Gibt das enthaltene Objekt zurueck.
	 * 
	 * @return das enthaltene Objekt
	 */
	public T get(){
		return value;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).hashCode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		try{
			Ref<T> referenz = (Ref<T>) obj;
			return new EqualsBuilder().append(referenz.value, this.value).isEquals();
		}catch(ClassCastException e){
			return false;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("Objekt", value).toString();
	}
	
	
	
}
