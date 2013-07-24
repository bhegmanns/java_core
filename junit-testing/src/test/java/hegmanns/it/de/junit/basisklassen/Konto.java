package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Abbildung eines Konto, hier recht banal realisiert.
 * 
 * @author B. Hegmanns
 */
public class Konto {

	/**
	 * Die Kontonummer.
	 */
	private String kontonummer;
	
	/**
	 * Die Kontoart.
	 */
	private Kontoart kontoart;
	
	/**
	 * Das zum {@link #zeitpunktAktualisierung Akualisierungszeitpunkt} vorhandene Kontosaldo.
	 */
	private BigDecimal saldo;
	
	/**
	 * Die Kreditlinie dieses Kontos.
	 * 
	 * Kennzeichnet den maximalen negativen Betrag dieses Kontos. Sofern keine Kreditlinie existiert,
	 * steht hier {@link BigDecimal#ZERO}.
	 */
	private BigDecimal kreditlinie;
	
	/**
	 * Zeitpunkt bzw. Bezugszeitpunkt der letzten Aktualisierung dieses Kontos.
	 * 
	 * <p>
	 * 	Im Allgemeinen werden die Konten durch naechtliche Ablaeufe (Batche) aktualisiert.
	 *  In diesem Fall wuerde hier ein Bezugsdatum stehen.
	 * </p>
	 */
	private Date zeitpunktAktualisierung;
	
	

	/**
	 * TODO Dokument me
	 */
	public Konto() {
		super();
	}

	public Konto(String kontonummer, Kontoart kontoart, BigDecimal saldo,
			BigDecimal kreditlinie) {
		super();
		this.kontonummer = kontonummer;
		this.kontoart = kontoart;
		this.saldo = saldo;
		this.kreditlinie = kreditlinie;
		this.zeitpunktAktualisierung = new Date();
	}

	public String getKontonummer() {
		return kontonummer;
	}

	public void setKontonummer(String kontonummer) {
		this.kontonummer = kontonummer;
	}

	public Kontoart getKontoart() {
		return kontoart;
	}

	public void setKontoart(Kontoart kontoart) {
		this.kontoart = kontoart;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getKreditlinie() {
		return kreditlinie;
	}

	public void setKreditlinie(BigDecimal kreditlinie) {
		this.kreditlinie = kreditlinie;
	}

	public Date getZeitpunktAktualisierung() {
		return zeitpunktAktualisierung;
	}

	public void setZeitpunktAktualisierung(Date zeitpunktAktualisierung) {
		this.zeitpunktAktualisierung = zeitpunktAktualisierung;
	}
	
}
