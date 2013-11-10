package de.hegmanns.it.schulung.hibernate.gui.model;

import java.util.Vector;

import javax.swing.AbstractListModel;

import de.hegmanns.it.schulung.hibernate.gui.Permuter;
import de.hegmanns.it.schulung.hibernate.gui.SchrittPanel;

public class GeneratedListModel extends AbstractListModel {
    SchrittPanel demo;
    Permuter permuter;

    public Vector prefix = new Vector();
    public Vector suffix = new Vector();

    public GeneratedListModel (SchrittPanel demo) {
        this.demo = demo;
        
    }

    private void update() {
        permuter = new Permuter(getSize());
        fireContentsChanged(this, 0, getSize());
    }

    public void addPrefix(String s) {
    	prefix.add(s);
    	fireContentsChanged(this, 0, getSize());
//        if(!prefix.contains(s)) {
//            prefix.addElement(s);
//            update();
//        }
    }

    public void removePrefix(String s) {
        prefix.removeElement(s);
        update();
    }

    public void addSuffix(String s) {
        if(!suffix.contains(s)) {
            suffix.addElement(s);
            update();
        }
    }

    public void removeSuffix(String s) {
        suffix.removeElement(s);
        update();
    }

    public int getSize() {
        return prefix.size();
    }

    public Object getElementAt(int index) {
    	return prefix.get(index);
//        if(permuter == null) {
//            update();
//        }
//        // morph the index to another int -- this has the benefit of
//        // causing the list to look random.
//        int j = permuter.map(index);
//        int ps = prefix.size();
//        int ss = suffix.size();
//        return (String) prefix.elementAt(j%ps) + (String) suffix.elementAt((j/ps)%ss);
    }
}
