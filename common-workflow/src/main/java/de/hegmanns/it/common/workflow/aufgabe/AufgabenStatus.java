package de.hegmanns.it.common.workflow.aufgabe;

/**
 * Aufgabenstatus einer Aufgabe.
 * 
 * @author B. Hegmanns
 *
 */
public enum AufgabenStatus {
	/** Es kann der naechste Schritt ausgefuehrt werden. */
	WAITING_FOR_WORK, 
	
	/** Es wird gerade ein Schritt ausgefuehrt. */
	IN_WORK, 
	
	/** Die Aufgabe ist komplett erledigt.*/
	ALL_DONE;
}
