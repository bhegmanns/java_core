package hegmanns.de.de.hegmanns.fundamental.chain.order;

public enum Geschaeftsart {

	KAUF("K"), VERKAUF("V");
	
	private String code;
	private Geschaeftsart(String code) {
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
}
