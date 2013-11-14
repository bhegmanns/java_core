package de.hegmanns.it.common.commandchain;

public class CommandResult<T> {

	private T result;
	
	public void setResult(T result)
	{
		this.result = result;
	}
	
	public T getResult(){
		return result;
	}
}
