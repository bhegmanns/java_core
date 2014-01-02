package de.hegmanns.it.utils.core;

/**
 * Darstellung eines Wertepaar.
 * 
 * @author B. Hegmanns
 *
 * @param <S> Typ fuer den ersten Wert
 * @param <T> Typ fuer den zweiten Wert
 */
public class Pair<S, T> {

	private S first;
	private T second;
	
	public Pair(S first, T second)
	{
		this.first = first;
		this.second = second;
	}

	public S getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}
	
	
}
