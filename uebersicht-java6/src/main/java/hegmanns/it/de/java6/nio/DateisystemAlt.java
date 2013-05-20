package hegmanns.it.de.java6.nio;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class DateisystemAlt implements Dateisystem{
	
	private File basisverzeichnis;
	private File unterverzeichnis;
	
	public DateisystemAlt(String basisverzeichnis, String unterverzeichnis){
		this(new File(basisverzeichnis), unterverzeichnis);
	}
	
	public DateisystemAlt(File basisverzeichnis, String unterverzeichnis){
		this.basisverzeichnis = basisverzeichnis;
		if (!this.basisverzeichnis.isDirectory()){
			throw new IllegalArgumentException("Angegebenes Basisverzeichnis ('" + basisverzeichnis.getAbsolutePath() + "') ist kein Verzeichnis.");
		}
		
		this.unterverzeichnis = new File(basisverzeichnis, unterverzeichnis);
		if (this.unterverzeichnis.exists()){
			throw new IllegalArgumentException("Das Unterverzeichnis '" + unterverzeichnis + "' zum Basisverzeichnis '" + basisverzeichnis.getAbsolutePath() + "' existiert bereits!");
		}
		
		try {
			if (!this.unterverzeichnis.mkdir()){
				throw new IllegalArgumentException("Unterverzeichnis konnte nicht angelegt werden.");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Unterverzeichnis konnte nicht angelegt werden: " + e.getMessage(), e);
		}
	}

	@Override
	public void verzeichnisseAnlegen(String... namen) {
		for (String name : namen)
		{
			File verzeichnis = new File(unterverzeichnis, name);
			try {
				if (!verzeichnis.mkdir()){
					throw new IllegalArgumentException("Unterverzeichnis '" + name + "' konnte nicht angelegt werden.");
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Unterverzeichnis '" + name + "' konnte nicht angelegt werden: " + e.getMessage(), e);
			}
		}
	}

	@Override
	public int jedesVerzeichnisBefuellen(String dateiname) {
		File[] verzeichnisse = unterverzeichnis.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		
		for (File verzeichnis : verzeichnisse)
		{
			File datei = new File(verzeichnis, dateiname);
			try {
				datei.createNewFile();
			} catch (IOException e) {
				throw new IllegalArgumentException("", e);
			}
		}
		
		return verzeichnisse.length;
	}

	@Override
	public String verzeichnisAusgeben() {
		String struktur = "";
		
		if (!unterverzeichnis.exists())
		{
			return struktur;
		}
				
		struktur = unterverzeichnis.getName() + "\n";
		
		for (File file : unterverzeichnis.listFiles())
		{
			struktur += "  " + file.getName() + "\n";
			for (File datei : file.listFiles())
			{
				struktur += "    " + datei.getName() + "\n";
			}
			struktur += "\n";
		}
		
		return struktur;
	}

	@Override
	public void verzeichnisLoeschen() {
		dateiLoeschen(unterverzeichnis);
	}
	
	private void dateiLoeschen(File file)
	{
		if ((file.isDirectory() && file.listFiles().length == 0) || file.isFile() ){
			if (!file.delete()){
				throw new IllegalArgumentException("Datei '" + file.getAbsolutePath() + "' konnte nicht geloescht werden.");
			}
		}
		else
		{
			if (file.isDirectory())
			{
				for (File child : file.listFiles())
				{
					dateiLoeschen(child);
				}
				file.delete();
			}
		}
	}

}
