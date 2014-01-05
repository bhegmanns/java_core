package de.hegmanns.it.utils.builder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * Eine reine Marker-Annotation zur Definition von Attributen in Subklassen {@link Builder}.
 * 
 * Die so definierten Werte werden als Default-Werte geladen, so dass hierdurch eine Einfache Definition von Default-Objekten
 * ermoeglicht wird.
 * Hierbei gelten die folgenden Regeln:
 * 
 * <code>
 * 	@MemberDefault
 *  private String einA = "aaa";
 * </code>
 * Das zu erzeugende Objekt besitzt ein Attribut mit dem so definierten Namen und erhält dann diesen Wert, sofern
 * im Buildprozess kein anderer Wert gesetzt wird.
 * 
 * <code>
 * 	@MemberDefault
 *  private Pair<String, Object> p1 = new Pair<>("einA", "aaa");
 * </code>
 * Die Definition erfolgt ueber die Klasse Pair. In diesem Fall ist der erste Werte (immer ein String) der Attributname
 * und der zweite Wert ist der zugehoerige Attributwert.
 * 
 * @author B. Hegmanns
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MemberDefault {
}
