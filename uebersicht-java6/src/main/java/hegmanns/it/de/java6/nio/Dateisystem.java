package hegmanns.it.de.java6.nio;

/**
 * Die Klasse fuehrt eine Reihe von Dateioperationen auf ein bestimmtes Basisverzeichnis aus.
 * Je nach Implementierung wird dieses Basisverzeichnis von dieser Klasse zunaechst erstellt,
 * oder es wird ein bereits existierendes Verzeichnis verwendet.
 * 
 * @author B. Hegmanns
 */
public interface Dateisystem {

	/**
	 * Legt im Basisverzeichnis neue Verzeichnisse an.
	 * 
	 * @param namen die Namen der gewuenschten Unterverzeichnisse.
	 */
	public void verzeichnisseAnlegen(String ...namen);
	
	/**
	 * Befuellt alle Unterverzeichnisse des Basisverzeichnis mit jeweils einer Datei.
	 * 
	 * Falls das Basisverzeichnis nicht (mehr) existiert oder keine Unterverzeichnisse existieren,
	 * passiert nichts weiter.
	 * 
	 * @param dateiname der Dateiname fuer jedes Unterverzeichnis
	 * @return die Anzahl der erzeugten Dateien
	 */
	public int jedesVerzeichnisBefuellen(String dateiname);
	
	/**
	 * Gibt die Verzeichnisstruktur als String aus. Je Verzeichnis-Ebene werden
	 * jeweils zwei Zeichen eingerueckt.
	 * 
	 * @return ein Output des Basisverzeichnis.
	 */
	public String verzeichnisAusgeben();
	
	/**
	 * Loescht alle bisher erzeugten Dateien und Unterverzeichnisse und ggf. auch
	 * das extra erstellte Basisverzeichnis, sofern diese Implementierung das Basisverzeichnis
	 * erstellt hat.
	 */
	public void verzeichnisLoeschen();
}
