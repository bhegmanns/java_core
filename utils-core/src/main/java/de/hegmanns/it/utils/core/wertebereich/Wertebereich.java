package de.hegmanns.it.utils.core.wertebereich;

/**
 * Abbildung eines Wertebereichs.
 * 
 * @author B. Hegmanns
 *
 * @param <T>
 */
public class Wertebereich<T extends Comparable<T>> {
	
	private WertebereichValue<T> start;
	private boolean startwertEnthalten;
	
	private WertebereichValue<T> ende;
	private boolean endewertEnthalten;
	
	public static <T extends Comparable<T>> Wertebereich<T> erstelleWertebereichIncluded(T start, T ende)
	{
		Wertebereich<T> wertebereich = new Wertebereich<>(new WertebereichValue<>(start),true, new WertebereichValue<>(ende), true);
		
		return wertebereich;
	}
	
	public static <T extends Comparable<T>> Wertebereich<T> erstelleWertebereichExcluded(T start, T ende)
	{
		Wertebereich<T> wertebereich = new Wertebereich<>(new WertebereichValue<>(start),false, new WertebereichValue<>(ende), false);
		
		return wertebereich;
	}
	
	public static <T extends Comparable<T>> Wertebereich<T> erstelleWertebereichStartExcludedEndeIncluded(T start, T ende)
	{
		Wertebereich<T> wertebereich = new Wertebereich<>(new WertebereichValue<>(start),false, new WertebereichValue<>(ende), true);
		
		return wertebereich;
	}
	
	public static <T extends Comparable<T>> Wertebereich<T> erstelleWertebereichStartIncludedEndeExcluded(T start, T ende)
	{
		Wertebereich<T> wertebereich = new Wertebereich<>(new WertebereichValue<>(start),true, new WertebereichValue<>(ende), false);
		
		return wertebereich;
	}
	
	public Wertebereich(WertebereichValue<T> start, boolean startwertEnthalten, WertebereichValue<T> ende, boolean endewertEnthalten){
		this.start = start;
		this.startwertEnthalten = startwertEnthalten;
		this.ende = ende;
		this.endewertEnthalten = endewertEnthalten;
	}

	public WertebereichValue<T> getStart() {
		return start;
	}

	public void setStart(WertebereichValue<T> start) {
		this.start = start;
	}

	public WertebereichValue<T> getEnde() {
		return ende;
	}

	public void setEnde(WertebereichValue<T> ende) {
		this.ende = ende;
	}
	
	public boolean isEnthalten(T wert){
		
		// exakt enthalten, Bereichsgrenzen ausgenommen
		boolean enthalten = ende.isGreaterThan(wert) && start.isLowerThan(wert);
		
		// ggf. Bereichsgrenzen kontrollieren, falls jeweiligen Grenzen eingeschlossen
		enthalten = enthalten || (isStartwertEnthalten() && start.isEqualsWith(wert)) || (isEndewertEnthalten() && ende.isEqualsWith(wert));
		return enthalten;
	}

	boolean isStartwertEnthalten() {
		return startwertEnthalten;
	}

	boolean isEndewertEnthalten() {
		return endewertEnthalten;
	}
	
	
	
}
