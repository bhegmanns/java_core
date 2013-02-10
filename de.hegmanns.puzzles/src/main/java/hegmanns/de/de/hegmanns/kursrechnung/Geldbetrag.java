package hegmanns.de.de.hegmanns.kursrechnung;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Stellt einen Geldbetrag dar.
 * 
 * Der Geldbetrag selbst setzt sich zusammen aus dem Betrag und der Waehrung.
 * 
 * @author B. Hegmanns
 */
public class Geldbetrag {
	
	

	private BigDecimal betrag;
	private Waehrung waehrung;
	
	/**
	 * Konstruktor zur Aufnahme von Waehrung und Betrag.
	 * 
	 * @param betrag der Betrag als Zahlenwert
	 * @param waehrung die Waehrung
	 */
	public Geldbetrag(BigDecimal betrag, Waehrung waehrung){
		this.betrag = betrag;
		this.waehrung = waehrung;
	}
	
	/**
	 * Gibt die Summe dieser Geldbetrag-Instanz und der uebergebenen Geldbetrag-Instanz
	 * als neue Geldbetrag-Instanz zurueck.
	 * Falls die Waehrung dieser Geldbetrag-Instanz und der des Summanden ungleich sind,
	 * wird eine {@link IllegalArgumentException} geworfen.
	 * 
	 * @param geldbetrag zweiter Summand
	 * @throws IllegalArgumentException Im Falle, dass die Waehrung des zweiten Summand ungleich
	 *                                  ist 
	 * @return 
	 */
	public Geldbetrag add(Geldbetrag geldbetrag){
		if (this.getWaehrung().equals(geldbetrag.getWaehrung()))
		{
			return new Geldbetrag(this.getBetrag().add(geldbetrag.getBetrag()), waehrung);
		}
		else
		{
			throw new IllegalArgumentException("Waehrung [" + this.waehrung + "] ungleich Summand-Waehrung [" + geldbetrag.getWaehrung() + "]");
		}
	}
	
	public Geldbetrag add(BigDecimal geldbetrag){
		return new Geldbetrag(this.getBetrag().add(geldbetrag), waehrung);
	}
	
	public Geldbetrag subtract(Geldbetrag geldbetrag){
		if (this.getWaehrung().equals(geldbetrag.getWaehrung()))
		{
			return new Geldbetrag(this.getBetrag().subtract(geldbetrag.getBetrag()), waehrung);
		}
		else
		{
			throw new IllegalArgumentException("Waehrung [" + this.waehrung + "] ungleich Subtrahent-Waehrung [" + geldbetrag.getWaehrung() + "]");
		}
	}
	
	public Geldbetrag subtract(BigDecimal geldbetrag){
		return new Geldbetrag(this.getBetrag().subtract(geldbetrag), waehrung);
	}
	
	public Geldbetrag multiply(Geldbetrag geldbetrag){
		if (this.getWaehrung().equals(geldbetrag.getWaehrung()))
		{
			return new Geldbetrag(this.getBetrag().multiply(geldbetrag.getBetrag()), waehrung);
		}
		else
		{
			throw new IllegalArgumentException("Waehrung [" + this.waehrung + "] ungleich Faktor-Waehrung [" + geldbetrag.getWaehrung() + "]");
		}
	}
	
	public Geldbetrag multiply(BigDecimal multiplikator)
	{
		return new Geldbetrag(this.getBetrag().multiply(multiplikator), waehrung);
	}
	
	public Geldbetrag divide(Geldbetrag geldbetrag){
		if (geldbetrag.getWaehrung().equals(this.waehrung))
		{
			return new Geldbetrag(this.getBetrag().divide(geldbetrag.getBetrag()), waehrung);
		}
		else
		{
			throw new IllegalArgumentException("Waehrung [" + this.waehrung + "] ungleich Divisor-Waehrung [" + geldbetrag.getWaehrung() + "]");
		}
	}
	
	public Geldbetrag divide(BigDecimal geldbetrag){
		return new Geldbetrag(this.getBetrag().divide(geldbetrag), waehrung);
	}

	public BigDecimal getBetrag() {
		return betrag;
	}

	public Waehrung getWaehrung() {
		return waehrung;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("Betrag", betrag).append("Waehrung", waehrung).toString();
	}
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.waehrung).append(this.betrag).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Geldbetrag geldbetrag = (Geldbetrag)obj;
		return new EqualsBuilder().append(this.waehrung, geldbetrag.getWaehrung()).append(this.betrag, geldbetrag.betrag).isEquals();
	}
	
}
