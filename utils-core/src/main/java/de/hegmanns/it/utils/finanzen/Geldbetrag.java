package de.hegmanns.it.utils.finanzen;

import java.math.BigDecimal;

public class Geldbetrag implements Comparable<Geldbetrag>{

	private BigDecimal betrag;
	
	private Waehrung waehrung;
	
	public Geldbetrag(BigDecimal betrag, Waehrung waehrung)
	{
		this.betrag = betrag;
		this.waehrung = waehrung;
	}

	protected BigDecimal getBetrag() {
		return betrag;
	}

	protected void setBetrag(BigDecimal betrag) {
		this.betrag = betrag;
	}

	protected Waehrung getWaehrung() {
		return waehrung;
	}

	protected void setWaehrung(Waehrung waehrung) {
		this.waehrung = waehrung;
	}

	@Override
	public int compareTo(Geldbetrag o) {
		if (this.waehrung.equals(o.waehrung))
		{
			return this.betrag.compareTo(o.betrag);
		}
		
		throw new IllegalArgumentException("Waehrungen stimmen nicht ueberein.");
	}
	
	
}
