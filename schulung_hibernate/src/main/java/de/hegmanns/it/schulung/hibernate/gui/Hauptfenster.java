package de.hegmanns.it.schulung.hibernate.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.hegmanns.it.schulung.hibernate.gui.action.Aufgabe;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateCloseSession;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateInitSession;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateInitSessionFactory;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateOrderDisplay;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateGeleseneOrderSchieben;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateOrderLoad;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateOrderSpeichern;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateTxEnd;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateTxStart;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc.DBConnectionErstellen;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc.DBConnectionSchliessen;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc.DBOrderDisplay;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc.DBOrderSchieben;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc.DBTransactionEnde;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.jdbc.DBTransactionStarten;

public class Hauptfenster extends JFrame implements ActionListener, Observer{
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		new Hauptfenster();
	}
	
	JMenuItem aufgabe01;
	JMenuItem aufgabe02;
	JMenuItem aufgabe03;
	

	public Hauptfenster() {
		super("DB-Test-Applikation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Aufgaben");
		menu.add(new JMenuItem("neue Aufgabe definieren"));
		menu.add(new JMenuItem("Aufgabe laden"));
		
		aufgabe01 = new JMenuItem("Aufgabe 1");
		aufgabe02 = new JMenuItem("Aufgabe 2");
		aufgabe03 = new JMenuItem("Aufgabe 3");
		menu.add(aufgabe01);
		menu.add(aufgabe02);
		menu.add(aufgabe03);
		
		aufgabe01.addActionListener(this);
		aufgabe02.addActionListener(this);
		aufgabe03.addActionListener(this);
		menubar.add(menu);
		this.setJMenuBar(menubar);

		this.setPreferredSize(new Dimension(800, 600));

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private PropertyDefinitionPanel propertyDefinitionPanel = null;
	private Aufgabe aufgabe = null;
	@Override
	public void actionPerformed(ActionEvent e) {
		if (propertyDefinitionPanel != null && e.getSource() == propertyDefinitionPanel.okButton)
		{
			try {
				PropertyDefinitionTableModel model = (PropertyDefinitionTableModel) propertyDefinitionPanel.table.getModel();
				propertyDefinitionPanel.okButton.removeActionListener(this);
				Map<String, String> cacheValues = model.getCacheValues();
				
				Properties properties = new Properties();
				for (Entry<String, String> cacheEntry : cacheValues.entrySet())
				{
					System.out.println(">" + cacheEntry.getKey() + ":" + cacheEntry.getValue());
					properties.setProperty(cacheEntry.getKey(), cacheEntry.getValue());
				}
				
				aufgabe.addParameter(properties);
				JPanel panel = new SchrittPanel(aufgabe);
				this.getContentPane().remove(propertyDefinitionPanel);
				this.getContentPane().add(panel);
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.getContentPane().validate();
			return;
		}
		if (e.getSource() == aufgabe01)
		{
			try {
				aufgabe = getAufgabe01();
				JPanel panel = null;
				if (aufgabe.getRequestedParameter() != null && aufgabe.getRequestedParameter().size() != 0)
				{
					propertyDefinitionPanel = new PropertyDefinitionPanel(aufgabe.getRequestedParameter());
					panel = propertyDefinitionPanel;
					propertyDefinitionPanel.okButton.addActionListener(this);
				}
				else
				{
					panel = new SchrittPanel(aufgabe);
				}
				this.getContentPane().add(panel);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			this.getContentPane().validate();
		}
		
		if (e.getSource() == aufgabe02)
		{
			try{
				displayPanel(getAufgabe02());
//				aufgabe = getAufgabe02();
//				JPanel panel = null;
//				
				
			}catch(IllegalAccessException e1)
			{
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == aufgabe03)
		{
			try{
				displayPanel(getAufgabe03());
			}catch(IllegalAccessException e1){
				e1.printStackTrace();
			}
		}
		
	}
	
	private void displayPanel(Aufgabe aufgabe) throws IllegalAccessException{
		JPanel panel = null;
		
		if (aufgabe.getRequestedParameter() != null && aufgabe.getRequestedParameter().size() != 0)
		{
			this.aufgabe = aufgabe;
			propertyDefinitionPanel = new PropertyDefinitionPanel(aufgabe.getRequestedParameter());
			panel = propertyDefinitionPanel;
			propertyDefinitionPanel.okButton.addActionListener(this);
		}
		else
		{
			panel = new SchrittPanel(aufgabe);
		}
		this.getContentPane().add(panel);
		this.getContentPane().validate();
	}
	
	private Aufgabe getAufgabe01()
	{
		Aufgabe aufgabe = new Aufgabe(getSchritteAufgabe01());
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(HibernateInitSessionFactory.ISOLATION_LEVEL, "Isolation-Level (2 oder 8)");
		parameters.put(HibernateTxEnd.TX_COMMIT_JN, "Transaktion commiten (J oder N)");
		parameters.put(HibernateGeleseneOrderSchieben.ANZAHL_TAGE, "Anzahl an Tagen (oder 0)");
		parameters.put(HibernateGeleseneOrderSchieben.ANZAHL_JAHRE, "Anzahl an Jahren (oder 0)");
		aufgabe.setRequestedParameter(parameters);
		
		return aufgabe;
	}
	
	public Aufgabe getAufgabe02()
	{
		Aufgabe aufgabe = new Aufgabe(getSchritteAufgabe02());
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(HibernateOrderLoad.HIBERNATE_OBJEKT_ID, "Die zu ladende Order-ID");
		parameters.put(HibernateInitSessionFactory.ISOLATION_LEVEL, "Isolation-Level (2 oder 8)");
		parameters.put(HibernateTxEnd.TX_COMMIT_JN, "Transaktion commiten (J oder N)");
		parameters.put(HibernateGeleseneOrderSchieben.ANZAHL_TAGE, "Anzahl an Tagen (oder 0)");
		parameters.put(HibernateGeleseneOrderSchieben.ANZAHL_JAHRE, "Anzahl an Jahren (oder 0)");
		aufgabe.setRequestedParameter(parameters);
		
		return aufgabe;
	}
	
	public Aufgabe getAufgabe03(){
		Aufgabe aufgabe = new Aufgabe(getSchritteAufgabe03());
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(DBConnectionErstellen.DB_ISOLATION_LEVEL, "Isolation-Level (2 oder 8)");
		parameters.put(DBOrderDisplay.ORDER_ID, "die Order-ID");
		parameters.put(DBOrderSchieben.ANZAHL_TAGE, "Anzahl an Tagen (oder 0)");
		parameters.put(DBOrderSchieben.ANZAHL_JAHRE, "Anzahl an Jahren (order 0)");
		parameters.put(DBTransactionEnde.TX_COMMIT_JN, "Transaktion commiten (J oder N)");
		
		aufgabe.setRequestedParameter(parameters);
		
		return aufgabe;
	}
	
	private List<Schritt> getSchritteAufgabe01()
	{
		List<Schritt> schritte = new ArrayList<Schritt>();
		schritte.add(new HibernateInitSessionFactory());
		schritte.add(new HibernateInitSession());
		schritte.add(new HibernateTxStart());
		schritte.add(new HibernateOrderSpeichern());
		schritte.add(new HibernateTxEnd());
		schritte.add(new HibernateTxStart());
		schritte.add(new HibernateOrderLoad());
		schritte.add(new HibernateGeleseneOrderSchieben());
		schritte.add(new HibernateTxEnd());
		schritte.add(new HibernateCloseSession());
		schritte.add(new HibernateInitSession());
		schritte.add(new HibernateTxStart());
		schritte.add(new HibernateOrderLoad());
		schritte.add(new HibernateOrderDisplay());
		schritte.add(new HibernateTxEnd());
		schritte.add(new HibernateCloseSession());
		
		return schritte;
	}
	
	private List<Schritt> getSchritteAufgabe02()
	{
		List<Schritt> schritte = new ArrayList<Schritt>();
		
		schritte.add(new HibernateInitSessionFactory());
		schritte.add(new HibernateInitSession());
		schritte.add(new HibernateTxStart());
		schritte.add(new HibernateOrderLoad());
		schritte.add(new HibernateGeleseneOrderSchieben());
		schritte.add(new HibernateTxEnd());
		schritte.add(new HibernateCloseSession());
		
		return schritte;
	}
	
	private List<Schritt> getSchritteAufgabe03(){
		List<Schritt> schritte = new ArrayList<Schritt>();
		
		schritte.add(new DBConnectionErstellen());
		schritte.add(new DBTransactionStarten());
		schritte.add(new DBOrderDisplay());
		schritte.add(new DBOrderSchieben());
		schritte.add(new DBOrderDisplay());
		
		schritte.add(new DBTransactionEnde());
		schritte.add(new DBConnectionSchliessen());
		return schritte;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
