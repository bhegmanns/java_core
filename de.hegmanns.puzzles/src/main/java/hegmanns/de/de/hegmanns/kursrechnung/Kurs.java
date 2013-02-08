package hegmanns.de.de.hegmanns.kursrechnung;

public class Kurs {

	private String waehrungssymbol;
	private String land;
	
	public Kurs(String waehrungssymbol){
		this(true, waehrungssymbol, null);
	}
	public Kurs(String waehrungssymbol, String land){
		this(true, waehrungssymbol, land);
	}
	private Kurs(boolean mitPruefung, String waehrungssymbol, String land){
		if (mitPruefung)
		{
			check(waehrungssymbol, land);
		}
		this.waehrungssymbol = waehrungssymbol;
		this.land = land;
	}
	
	private void check(String waehrungssymbol, String land){
		if (land != null)
		{
			if (land.length() != 2)
			{
				throw new IllegalArgumentException("Land muss drei-stellig sein.");
			}
		}
		
		if (waehrungssymbol != null)
		{
			if (waehrungssymbol.length() != 3)
			{
				throw new IllegalArgumentException("Waehrungssymbol muss drei-stellig sein.");
			}
		}
	}
	
	public String getWaehrungssymbol() {
		return waehrungssymbol;
	}
	public String getLand() {
		return land;
	}
	
	

}
