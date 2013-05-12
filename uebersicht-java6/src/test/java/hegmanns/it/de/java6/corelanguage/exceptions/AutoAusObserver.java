package hegmanns.it.de.java6.corelanguage.exceptions;

import hegmanns.it.de.java6.corelanguage.terror.Auto;

import java.util.Observable;
import java.util.Observer;

public class AutoAusObserver implements Observer{
	
	private boolean autoAus = false;
	
	public AutoAusObserver(Auto auto)
	{
		auto.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		autoAus = true;
	}

	public boolean isAutoAus() {
		return autoAus;
	}
	
	

}
