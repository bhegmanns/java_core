package de.hegmanns.it.common.workflow.aufgabe;

import java.util.Properties;

/**
 * Abbildung eines Ergebnis fuer einen Schritt.
 * 
 * Das Schritt-Ergebnis kann beispielsweise in einem Frontend dargestellt werden.
 * 
 * @author B. Hegmanns
 */
public class SchrittResult {

	private Properties properties;
	
	public SchrittResult()
	{
		this(new Properties());
	}
	
	public SchrittResult(Properties properties)
	{
		this.properties = properties;
	}
	
	public void setProperty(String key, String value)
	{
		this.properties.setProperty(key, value);
	}
	
	public Properties getProperties(){
		return properties;
	}
}
