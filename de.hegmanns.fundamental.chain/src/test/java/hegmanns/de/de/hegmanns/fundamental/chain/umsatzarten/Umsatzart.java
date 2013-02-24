package hegmanns.de.de.hegmanns.fundamental.chain.umsatzarten;

public enum Umsatzart {

	Verrechnungskonto("VKT"),
	
	Girokonto("GK"),
	
	TagegeldkontoPlus("TKP"),
	
	Wertpapierkreditkonto("WKP"),
	
	Cfd("CFD"),
	
	Visa("VK"),
	
	Depot("Depot"),
	
	Festgeld("FGK"),
	
	Laufzeitkonto("LZK");
	
	private String code;
	private Umsatzart(){
		this(null);
	}
	private Umsatzart(String code){this.code = code;}
	
	public String getCode(){
		return code;
	}
}
