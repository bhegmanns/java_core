package de.hegmanns.it.schulung.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.Set;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import de.hegmanns.it.schulung.hibernate.gui.action.Aufgabe;
import de.hegmanns.it.schulung.hibernate.gui.action.AufgabenStatus;
import de.hegmanns.it.schulung.hibernate.gui.action.InfoStatus;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateCloseSession;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateInitSession;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateInitSessionFactory;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateOrderSpeichern;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateTxEnd;
import de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate.HibernateTxStart;
import de.hegmanns.it.schulung.hibernate.gui.model.GeneratedListModel;
import de.hegmanns.it.schulung.hibernate.gui.renderer.CompanyLogoCellRenderer;

public class SchrittPanel extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	
	private /*final*/ JList list; // TODO: Generic, final
	private GeneratedListModel generatedListModel;
	private JButton weiterButton;
	private Aufgabe aufgabe;
	
	private static final Dimension DIM = new Dimension(10, 1);
	private static final Dimension DIM_LIST = new Dimension(1, 10);
	private static final Dimension DIM3010 = new Dimension(30,1);
	
	public static void main(String [] args) throws IllegalAccessException
	{
		JFrame frame = new JFrame("Hibernate-Test-Fenster");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		List<Schritt> schritte = new ArrayList<Schritt>();
		schritte.add(new HibernateInitSessionFactory());
		schritte.add(new HibernateInitSession());
		schritte.add(new HibernateTxStart());
		schritte.add(new HibernateOrderSpeichern());
		schritte.add(new HibernateTxEnd());
		schritte.add(new HibernateCloseSession());
		Aufgabe aufgabe = new Aufgabe(schritte);
		
		
		frame.getContentPane().add(new SchrittPanel(aufgabe));
		frame.setPreferredSize(new Dimension(800, 600));
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public SchrittPanel(Aufgabe aufgabe) throws IllegalAccessException{
		setLayout(new BorderLayout());
		this.aufgabe = aufgabe;
		this.aufgabe.addObserver(this);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.add(Box.createRigidArea(DIM));
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.add(Box.createRigidArea(DIM_LIST));
		
		centerPanel.add(listPanel);
		centerPanel.add(Box.createRigidArea(DIM3010));
		
		list = new JList();
		list.setCellRenderer(new CompanyLogoCellRenderer());
		
		generatedListModel = new GeneratedListModel(this);
		list.setModel(generatedListModel);
		list.setVisibleRowCount(22);
		
		
		if (aufgabe != null && aufgabe.getAufgabenschritte()!=null)
		{
			for (Schritt schritt : aufgabe.getAufgabenschritte())
			{
				generatedListModel.addPrefix(schritt.toString());
			}
			generatedListModel.addSuffix("-");
		}
		else
		{
		
		generatedListModel.addPrefix("Tera");
		generatedListModel.addPrefix("Micro");
		generatedListModel.addPrefix("Net");
		generatedListModel.addSuffix("Tech");
		generatedListModel.addSuffix("Serv");
		}
		
		list.setEnabled(false);
		centerPanel.add(createControlPanel());
		
		JScrollPane scrollPane = new JScrollPane(list);
		listPanel.add(scrollPane);
		listPanel.add(Box.createRigidArea(DIM3010));
	}
	
	public JPanel createControlPanel(){
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		
		
		weiterButton = new JButton("WEITER");
		weiterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.setSelectedIndex(list.getSelectedIndex() + 1);
				aufgabe.aufgabenschrittAusfuehren();
				weiterButton.setEnabled(false);
			}
		});

//		controlPanel.add(Box.createRigidArea(new Dimension(10,10)));
		controlPanel.add(weiterButton);
		controlPanel.add(Box.createRigidArea(new Dimension(10,10)));
		
		
		return controlPanel;
	}
	@Override
	public void update(Observable o, Object arg) {
		
		InfoStatus aufgabenstatus = (InfoStatus)arg;
		System.out.println("Aufgabe meldet: " + aufgabenstatus.getAufgabenstatus());
		printOut("Schritt-Ergebnisse", aufgabenstatus.getSchrittresult().getProperties());
		
		switch(aufgabenstatus.getAufgabenstatus())
		{
		case WAITING_FOR_WORK:
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					
					@Override
					public void run() {
						weiterButton.setEnabled(true);
					}
				});
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;break;
		case ALL_DONE: 
		case IN_WORK: break;
		}
	}
	
	private void printOut(String outPrefix, Properties properties)
	{
		if (outPrefix != null)
		{
			System.out.println(outPrefix);
		}
		for (Entry<Object, Object> entry : properties.entrySet())
		{
			System.out.println("" + entry.getKey() + " : " + entry.getValue());
		}
	}

}
