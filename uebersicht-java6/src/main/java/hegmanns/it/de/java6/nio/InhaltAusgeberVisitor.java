package hegmanns.it.de.java6.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.lang.StringUtils;

/**
 * Einfache Ausgabe des Verzeichnisbaums.
 * 
 * @author B. Hegmanns
 */
public class InhaltAusgeberVisitor extends SimpleFileVisitor<Path> {
	private String ausgabe = "";

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
			throws IOException {
		ausgabe += StringUtils.repeat(" ", dir.getNameCount()) + dir.getFileName().toString() + "\n";
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		ausgabe += StringUtils.repeat(" ", file.getNameCount()) + file.getFileName().toString() + "\n";
		return FileVisitResult.CONTINUE;
	}
	
	

	@Override
	public FileVisitResult visitFileFailed(Path arg0, IOException arg1)
			throws IOException {
		return FileVisitResult.SKIP_SUBTREE;
	}

	public String getAusgabe() {
		return ausgabe;
	}

}
