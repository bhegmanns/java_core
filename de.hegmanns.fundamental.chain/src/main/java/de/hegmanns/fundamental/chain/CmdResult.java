package de.hegmanns.fundamental.chain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CmdResult<U> {

	private List<U> results;
	
	private boolean cmdExecuted = true;
	
	public CmdResult()
	{
		results = new ArrayList<U>();
	}
	
	public void add(U result)
	{
		results.add(result);
	}
	
	public void add(Collection<U> results)
	{
		this.results.addAll(results);
	}
	
	public List<U> getResults()
	{
		return results;
	}
	
	public Iterator<U> getResultIterator()
	{
		return results.iterator();
	}
	
	public void setExecuted(boolean executed){
		this.cmdExecuted = executed;
	}
	
	public void setExecutionError(Throwable throwable, String executionErrorMessage, U result){
		
	}
	
	public boolean isExecuted()
	{
		return cmdExecuted;
	}
}
