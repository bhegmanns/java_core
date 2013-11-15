package de.hegmanns.it.schulung.dbverbindung.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;

public class DbConnectionLocator {
	
	private Map<String, String> xmlKonfigurationPropertiesEinlesen(String datei)
	{
		Map<String, String> properties = new HashMap<>();
		InputStream stream = this.getClass().getResourceAsStream(datei);
		
		try{
			DocumentBuilderFactory domBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = domBuilderFactory.newDocumentBuilder();
			
			domBuilder.setErrorHandler(new DefaultErrorHandler());
			
			Document dom = domBuilder.parse(stream);
			dom.getDocumentElement().normalize();
			
			NodeList propertyNodes = dom.getElementsByTagName("property");
			
			for (int i = 0 ; i < propertyNodes.getLength() ; i++)
			{
				org.w3c.dom.Node item = propertyNodes.item(i);
				String propertyName = item.getAttributes().getNamedItem("name").getNodeValue();
				String propertyValue = item.getFirstChild().getNodeValue();
				
				properties.put(propertyName, propertyValue);
			}
			
		}catch(Exception e){
			throw new RuntimeException("", e);
		}
		
		return properties;
	}
}

