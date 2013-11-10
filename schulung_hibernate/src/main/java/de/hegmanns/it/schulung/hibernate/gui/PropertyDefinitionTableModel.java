package de.hegmanns.it.schulung.hibernate.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

public class PropertyDefinitionTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Pair<String, String>> props;
	private List<String> values;
	private String[] columnnames = {"Property-Name", "Erklaerung", "Wert"};
	
	
	public PropertyDefinitionTableModel(Map<String, String> properties)
	{
		props = new ArrayList<Pair<String,String>>();
		values = new ArrayList<String>(props.size());
		for (Entry<String, String> propertyEntry : properties.entrySet())
		{
			props.add(new Pair<String, String>(propertyEntry.getKey(), propertyEntry.getValue()));
			values.add("");
		}
		
	}

	@Override
	public int getRowCount() {
		return props.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex < 2)
		{
			Pair<String, String> pair = props.get(rowIndex);
			return columnIndex == 0 ? pair.getFirst() : pair.getSecond();
		}
		else
		{
			
			return values.get(rowIndex);
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnnames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 2;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 2)
		{
			values.set(rowIndex, aValue.toString());
		}
	}
	
	public Map<String, String> getCacheValues()
	{
		Map<String, String> inputCache = new HashMap<String, String>();
		
		Iterator<String> valueIterator = values.iterator();
		Iterator<Pair<String, String>> pairIterator = props.iterator();
		
		while (pairIterator.hasNext()){
			Pair<String, String> pair = pairIterator.next();
			String value = valueIterator.next();
			
			inputCache.put(pair.getFirst(), value);
		}
		
		return inputCache;
	}
	
	

}

class Pair<U, T>{
	private U first;
	private T second;
	
	public Pair(U first, T second){
		this.first = first;
		this.second = second;
	}

	public U getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}
	
	
}
