package hegmanns.it.de.java6.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 
 * @author B. Hegmanns
 *
 */
public class DateisystemNeu implements Dateisystem {
	
	private Path unterverzeichnis;
	
	public DateisystemNeu(String basisverzeichnis, String unterverzeichnis)
	{
		this.unterverzeichnis = Paths.get(basisverzeichnis, unterverzeichnis);
		
		if (!Files.exists(this.unterverzeichnis))
		{
			try {
				Files.createDirectory(this.unterverzeichnis);
			} catch (IOException e) {
				throw new IllegalArgumentException("Kann Verzeichnis nicht anlegen: " + e.getMessage(), e);
			}
		}
	}
	
	public DateisystemNeu(File basisverzeichnis, String unterverzeichnis)
	{
		this(basisverzeichnis.getAbsoluteFile().getName(), unterverzeichnis);
	}

	@Override
	public void verzeichnisseAnlegen(String... namen) {
		for (String name : namen)
		{
			Path pfad = Paths.get(this.unterverzeichnis.toString(), name);
			try {
				Files.createDirectory(pfad);
			} catch (IOException e) {
				throw new IllegalArgumentException("Konnte Verzeichnis '" + e.getMessage() + "' nicht anlegen : " + e.getMessage(), e);
			}
		}
		
	}

	@Override
	public int jedesVerzeichnisBefuellen(final String dateiname) {
		
		try {
			Files.walkFileTree(this.unterverzeichnis, new FileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc)
						throws IOException {
					// TODO Auto-generated method stub
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					Path path = Paths.get(dir.toString(), dateiname);
					Files.createFile(path);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc)
						throws IOException {
					return null;
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String verzeichnisAusgeben() {
		InhaltAusgeberVisitor visitor = new InhaltAusgeberVisitor();
		try {
			Files.walkFileTree(unterverzeichnis, visitor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return visitor.getAusgabe();
	}

	@Override
	public void verzeichnisLoeschen() {
		
		try {
			Files.walkFileTree(unterverzeichnis, new FileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc)
						throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc)
						throws IOException {
					return null;
				}
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("" + e.getMessage(), e);
		}
		

	}

}
