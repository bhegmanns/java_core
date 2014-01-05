package de.hegmanns.it.utils.core.wertebereich;

public class WertebereichValue<T extends Comparable<T>> {

	private T wert;
	
	
	public WertebereichValue(T wert)
	{
		this.wert = wert;
	}

	public T getWert() {
		return wert;
	}

	public void setWert(T wert) {
		this.wert = wert;
	}

	
	public boolean isGreaterThan(T value){
		return wert.compareTo(value) > 0;
	}
	
	public boolean isLowerThan(T value)
	{
		return wert.compareTo(value) < 0;
	}
	
	public boolean isEqualsWith(T value)
	{
		return wert.compareTo(value) == 0;
	}

	@Override
	public String toString() {
		return "WertebereichValue [wert=" + wert + "]";
	}
	
	
}
