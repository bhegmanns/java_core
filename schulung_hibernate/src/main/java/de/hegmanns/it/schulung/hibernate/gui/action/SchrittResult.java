package de.hegmanns.it.schulung.hibernate.gui.action;

import java.util.Properties;

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
