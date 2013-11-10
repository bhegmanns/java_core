package de.hegmanns.it.schulung.hibernate.gui.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class CompanyLogoCellRenderer extends DefaultListCellRenderer {
	ImageIcon images[] = new ImageIcon[7];
    public Component getListCellRendererComponent(
         JList list,
         Object value,
         int index,
         boolean isSelected,
         boolean cellHasFocus)
     {
         Component retValue = super.getListCellRendererComponent(
             list, value, index, isSelected, cellHasFocus
         );
         retValue.setForeground(Color.BLACK);
         if (cellHasFocus)
         {
        	 retValue.setBackground(Color.GREEN);
         }
         setIcon(images[index%7]);
         return retValue;
     }
 }
