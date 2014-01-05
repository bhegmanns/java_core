package de.hegmanns.it.utils.builder;

public class BuilderException extends Exception {

	private static final long serialVersionUID = 1L;

	public BuilderException(String message, Exception e)
	{
		super(message, e);
	}
}
