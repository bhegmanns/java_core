package de.hegmanns.it.utils.core.hashcode;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

public class RefTest {

	@Test
	public void referenzWirdInSetGefunden()
	{
		SillyObject sally = new SillyObject("Sally", 10);
		
		HashSet<SillyObject> sillyObjects = new HashSet<>();
		sillyObjects.add(sally);
		
		System.out.println("Hashcode von Sally:                       " + sally.hashCode());
		sally.setIq(100); // Sally wird klueger ...
		System.out.println("Hashcode von Sally (nach Gehirntherapie): " + sally.hashCode());
		System.out.println("Ist Sally in Set enthalten: " + sillyObjects.contains(sally));
		
		sillyObjects.add(sally);
		System.out.println("Anzahl Objekte in Set: " + sillyObjects.size());
		
		if (sillyObjects.size() >=2)
		{
			Iterator<SillyObject> iterator = sillyObjects.iterator();
			SillyObject o1 = iterator.next();
			SillyObject o2 = iterator.next();
			System.out.println("o1 == o2                       >>> " + (o1 == o2));
			System.out.println("o1.equals(o2)                  >>> " + o1.equals(o2));
			System.out.println("o1.hashCode() == o2.hashCode() >>> " + (o1.hashCode() == o2.hashCode()));
		}
		else
		{
			System.out.println("OK");
		}
	}
}
