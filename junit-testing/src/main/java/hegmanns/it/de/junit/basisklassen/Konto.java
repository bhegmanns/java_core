package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;

public class Konto {

	private String kontonummer;
	
	private Kontoart kontoart;
	
	private BigDecimal saldo;
	
	private BigDecimal kreditlinie;
	
	

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
	
	
}
