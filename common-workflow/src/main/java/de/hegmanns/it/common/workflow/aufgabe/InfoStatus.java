package de.hegmanns.it.common.workflow.aufgabe;

/**
 * Zusammenfassung aus dem Aufgabenstatus und dem Schrittergebnis.
 * 
 * @author B. Hegmanns
 */
public class InfoStatus {
	private AufgabenStatus aufgabenstatus;
	private SchrittResult schrittresult;
	public InfoStatus(AufgabenStatus aufgabenstatus, SchrittResult schrittresult) {
		super();
		this.aufgabenstatus = aufgabenstatus;
		this.schrittresult = schrittresult;
	}
	public AufgabenStatus getAufgabenstatus() {
		return aufgabenstatus;
	}
	public SchrittResult getSchrittresult() {
		return schrittresult;
	}
	
	
}
