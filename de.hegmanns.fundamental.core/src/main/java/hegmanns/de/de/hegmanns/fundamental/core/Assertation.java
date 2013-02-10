package hegmanns.de.de.hegmanns.fundamental.core;

/**
 * Pruefmethoden, die im Fall eines negativen Pruefergebnisses eine {@link IllegalArgumentException} werfen.
 * 
 * @author B. Hegmanns
 */
public class Assertation {

	/**
	 * Wirft eine {@link IllegalArgumentException}, falls die uebergebene Instanz <i>null</i> ist und
	 * gibt <i>errorMessage<i> in die Fehler-Message.
	 * 
	 * @param instanz die zu pruefende Intanz
	 * @param errorMessage die Nachricht
	 */
	public static <T> void notNull(T instanz, String errorMessage){
		if (instanz == null)
		{
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	/**
	 * Wirft eine {@link IllegalArgumentException}, falls die uebergebene Instanz <b>nicht</b> <i>null</i> ist
	 * und gibt <i>errorMessage</i> in die Fehler-Message.
	 * @param instanz der zu pruefende Wert
	 * @param errorMessage die Fehler-Nachricht.
	 */
	public static <T> void isNull(T instanz, String errorMessage){
		if (instanz != null)
		{
			throw new IllegalArgumentException(errorMessage + "( Wert war '" + instanz.toString() + "')");
		}
	}
	
	/**
	 * Kontrolliert den uebergebenen Text auf die erlaubte Laenge.
	 * Bei gesetzter Option <i>trim</i> werden Whitespaces abgeschnitten.
	 * Falls die Obergrenze egal ist, {@link Long#MAX_VALUE LONG.MAX_VALUE} verwenden.
	 * 
	 * @param text der String
	 * @param trim true, falls fuehrende und schliessende Whitespaces abgeschnitten werden sollen
	 * @param errorMessage der Fehlertext im Fehlerfall
	 * @param minLength Minimale Laenge (gilt inklusive)
	 * @param maxLenth Maximale Laenge, bei <i>null</i> muss der String die Laenge unter minLength haben.
	 */
	public static void lengthBetween(String text, boolean trim, String errorMessage, long minLength, long ... maxLength){
		if (text == null)
		{
			throw new IllegalArgumentException("Tex ist null");
		}
		if (minLength < 0)
		{
			throw new RuntimeException("lengthBetween:minLength < 0, erwartet >=0");
		}
		long maximalerWert = minLength;
		if (maxLength != null && maxLength.length != 0)
		{
			maximalerWert = maxLength[0];
			if (maximalerWert < minLength)
			{
				throw new RuntimeException("Parameter maxLength(" + maximalerWert + ") < minLength(" + minLength + ")");
			}
		}
		
		if (trim)
		{
			text = text.trim();
		}
		if (text.length()<minLength || text.length()>maximalerWert)
		{
			throw new IllegalArgumentException(errorMessage + "(Textlaenge von '" + text + "' ist " + text.length() + ")");
		}
	}
	

}
