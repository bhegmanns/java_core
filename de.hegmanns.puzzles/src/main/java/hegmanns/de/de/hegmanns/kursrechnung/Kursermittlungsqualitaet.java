package hegmanns.de.de.hegmanns.kursrechnung;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Kursermittlungsqualitaet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Kursqualitaet gewuenschteMindestqualitaet;
	Kursqualitaet moeglicheMindestQualitaet;
	
	public Kursermittlungsqualitaet(Kursqualitaet kursqualitaet){
		this.gewuenschteMindestqualitaet = kursqualitaet;
	}
	
	public Kursqualitaet getGewuenschteMindestQualitaet(){
		return gewuenschteMindestqualitaet;
	}
	
	public Kursqualitaet getMoeglicheMindestQualitaet(){
		return moeglicheMindestQualitaet;
	}
	
	public void setMoeglicheMindestQualitaet(Kursqualitaet qualitaet){
		this.moeglicheMindestQualitaet = qualitaet;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.gewuenschteMindestqualitaet).append(this.moeglicheMindestQualitaet).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Kursermittlungsqualitaet qualitaet = (Kursermittlungsqualitaet)obj;
		return new EqualsBuilder().append(this.gewuenschteMindestqualitaet, qualitaet.gewuenschteMindestqualitaet).append(this.moeglicheMindestQualitaet, qualitaet.moeglicheMindestQualitaet).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("gewuenschteMindestqualitaet", this.gewuenschteMindestqualitaet).append("moeglicheMindestQualitaet", this.moeglicheMindestQualitaet).toString();
	}
	
	
	

}
