package hegmanns.it.de.junit.checkappender;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * LoggingEntrance, der ein das FEHLEN eines konkreten Logs prueft.
 * 
 * @author B. Hegmanns
 */
public class UnrequestedLoggingEntrance implements LoggingEntrance {

	private Level level;
	private String loggerName;
	private String messagePart;

	private boolean entranceNotFound = true;
	
	
	public UnrequestedLoggingEntrance() {
		super();
	}

	public UnrequestedLoggingEntrance(Level level, String loggerName,
			String messagePart) {
		super();
		this.level = level;
		this.loggerName = loggerName;
		this.messagePart = messagePart;
	}

	@Override
	public void check(LoggingEvent loggingEvent) {
		if (entranceNotFound)
		{
			entranceNotFound = entranceNotFound && !(levelNotFound(loggingEvent) && loggerNameNotFound(loggingEvent) && messagePartNotFound(loggingEvent));
		}

	}
	
	private boolean levelNotFound(LoggingEvent loggingEvent)
	{
		return level != null && level.equals(loggingEvent.getLevel());
	}
	
	private boolean loggerNameNotFound(LoggingEvent loggingEvent){
		return loggerName != null || loggerName.equals(loggingEvent.getLoggerName());
	}
	
	private boolean messagePartNotFound(LoggingEvent loggingEvent)
	{
		return messagePart != null || ((String)loggingEvent.getMessage()).indexOf(messagePart) != -1;
	}

	@Override
	public boolean hasMatched() {
		return entranceNotFound;
	}

}
