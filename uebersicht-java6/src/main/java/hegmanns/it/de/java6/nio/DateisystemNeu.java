package hegmanns.it.de.java6.nio;

import java.io.File;
import java.nio.file.Path;

public class DateisystemNeu implements Dateisystem {
	
	public DateisystemNeu(String basisverzeichnis){}
	
	public DateisystemNeu(Path basisverzeichnis){}
	
	public DateisystemNeu(File basisverzeichnis){}

	@Override
	public void verzeichnisseAnlegen(String... namen) {
		// TODO Auto-generated method stub

	}

	@Override
	public int jedesVerzeichnisBefuellen(String dateiname) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public String verzeichnisAusgeben() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verzeichnisLoeschen() {
		// TODO Auto-generated method stub

	}

}
