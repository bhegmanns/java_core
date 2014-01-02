package de.hegmanns.it.utils.finanzen;

public interface KursProvider {

	public Kurs getKurs(Waehrung quellwaehrung, Waehrung zielwaehrung);
}
