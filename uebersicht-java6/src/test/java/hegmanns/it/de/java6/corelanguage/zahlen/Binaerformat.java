package hegmanns.it.de.java6.corelanguage.zahlen;

import org.junit.Test;

/**
 * Mit JAVA 7 koennen Zahlen-Literale im Binaerformat nun auch direkt mit einem Prefix
 * deklariert werden, aehnlich wie beim Hexadezimalformat oder Oktalformat.
 * 
 * Der Prefix lautet : 0b oder 0B
 * 
 * @author B. Hegmanns
 */
public class Binaerformat {

	@Test
	public void binaerformatAlt()
	{
		
	}
	
	@Test
	public void binaerformatNeu()
	{
		int zahl = 0B010101010;
	}
	
	/**
	 * 
	 */
	@Test
	public void binaerformatVerschiedeneBitdarstellungen()
	{
		byte z1 = (byte)0B11111111;
		short z2 = (short) 0B1111111100000000;
		int z3 = (int)0B11111111000000001111111100000000;
		
		// 64-bit Zahl, bitte den "L"-Suffix beachten!
		long z4 = (long)0B1111111100000000111111110000000011111111000000001111111100000000L;
	}
}
