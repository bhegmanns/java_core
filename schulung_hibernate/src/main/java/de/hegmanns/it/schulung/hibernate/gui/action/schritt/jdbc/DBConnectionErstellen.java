package de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class DBConnectionErstellen extends Schritt{

	public static final String DB_ISOLATION_LEVEL = "DB_ISOLATION_LEVEL";
	public static final String DB_CONNECTION = "DB_CONNECTION";
	
	public DBConnectionErstellen(){
		super(new String[]{DB_CONNECTION}, new String[]{}, new String[]{});
	}
	
	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		Map<String, String> properties = xmlKonfigurationPropertiesEinlesen();
		
		try{
		Class.forName(properties.get("connection.driver_class"));
		Connection connection = DriverManager.getConnection(properties.get("connection.url"), properties.get("connection.username"), properties.get("connection.password"));
		cache.setIntoCache(DB_CONNECTION, connection);
		
		connection.setAutoCommit(false); // autocommit ausschalten, jeweils eigene Transaktionen
		connection.setTransactionIsolation(Integer.parseInt(cache.getFromCache(DB_ISOLATION_LEVEL, String.class)));
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Map<String, String> xmlKonfigurationPropertiesEinlesen(){
		InputStream stream = this.getClass().getResourceAsStream("/hibernate.cfg.xml");
		BufferedReader bin = new BufferedReader(new InputStreamReader(stream));
		String zeile = null;
//		try{
//		while ((zeile = bin.readLine()) != null)
//		{
//			System.out.println("" + zeile);
//		}
//		}catch(Exception e){
//			
//		}
//		try {
//			bin.close();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		Map<String, String> properties = new HashMap<String, String>();
		
		try{
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
			domBuilder.setErrorHandler(new ErrorHandler() {
				
				@Override
				public void warning(SAXParseException exception) throws SAXException {
					System.out.println("WARN: " + exception.toString());
					
				}
				
				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					System.out.println("FATAL-ERROR: " + exception.toString());
				}
				
				@Override
				public void error(SAXParseException exception) throws SAXException {
					System.out.println("ERROR: " + exception.toString());
				}
			});
			
			Document dom = domBuilder.parse(stream);
			dom.getDocumentElement().normalize();
			
			NodeList propertyNodes = dom.getElementsByTagName("property");
			
			for (int i = 0 ; i < propertyNodes.getLength() ; i++)
			{
				Node item = propertyNodes.item(i);
				String propertyName = item.getAttributes().getNamedItem("name").getNodeValue();
				String propertyValue = item.getFirstChild().getNodeValue();
				
				properties.put(propertyName, propertyValue);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return properties;
	}

}
