package de.hegmanns.it.utils.finanzen;

public class Waehrung {
	
	public static final Waehrung EUR = new Waehrung("EUR", 2);
	public static final Waehrung USD = new Waehrung("USD", 2);

	private String code;
	
	private int maxScale;
	
	
	private Waehrung(String code, int maxScale)
	{
		this.code = code;
		this.maxScale = maxScale;
	}


	public String getCode() {
		return code;
	}


	public int getMaxScale() {
		return maxScale;
	}
	
	
	
}
