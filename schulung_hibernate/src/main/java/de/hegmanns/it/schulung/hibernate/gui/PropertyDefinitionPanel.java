package de.hegmanns.it.schulung.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class PropertyDefinitionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Properties properties = new Properties();
	
	public JButton okButton;
	public JTable table;
	
	public Properties getDefinedProperties()
	{
		return properties;
	}
	
	public static void main(String [] args)
	{
		JFrame fenster = new JFrame("...");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("A", "ein A");
		properties.put("B", "ein B");
		fenster.getContentPane().add(new PropertyDefinitionPanel(properties));
		
		fenster.setPreferredSize(new Dimension(800, 600));
		
		fenster.pack();
		fenster.setLocationRelativeTo(null);
		fenster.setVisible(true);
	}

	public PropertyDefinitionPanel(Map<String, String> requestedParameter)
	{
		setLayout(new BorderLayout());
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));
		add(centerpanel, BorderLayout.CENTER);
		
		
		JPanel tablePanel = erstelleTablePanel(requestedParameter);
		centerpanel.add(tablePanel);
		centerpanel.add(Box.createRigidArea(new Dimension(1, 10)));
		
		JPanel controlPanel = new JPanel(new FlowLayout());
		okButton = new JButton("OK");
		controlPanel.add(okButton);
		centerpanel.add(controlPanel);
		
	}
	
	private JPanel erstelleTablePanel(Map<String, String> parameter)
	{
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
		
		TableModel tablemodel = new PropertyDefinitionTableModel(parameter);
		table = new JTable(tablemodel);
		JScrollPane scrollpane = new JScrollPane(table);
		tablePanel.add(scrollpane);
		
		return tablePanel;
	}
	
	
}
