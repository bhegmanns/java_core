package de.hegmanns.it.common.commandchain.result;

import java.util.Collection;
import java.util.HashSet;

import de.hegmanns.it.common.commandchain.CommandResult;

public class CollectionResult<T> {
	
	private Collection<T> results = new HashSet<>();
	
	public static <T> CommandResult<CollectionResult<T>> erstelleCollectionOfResults(Class<T> typ){
		CommandResult<CollectionResult<T>> result = new CommandResult<>();
		CollectionResult<T> collectionResult = new CollectionResult<>();
		result.setResult(collectionResult);
		
		return result;
	}
	
	public static <T> CommandResult<CollectionResult<T>> erstelleCollectionOfResults(Collection<T> basisCollection)
	{
		CommandResult<CollectionResult<T>> result = new CommandResult<>();
		CollectionResult<T> collectionResult = new CollectionResult<>();
		collectionResult.results = basisCollection;
		
		result.setResult(collectionResult);
		
		return result;
	}
	
	public void addResult(T result){
		this.results.add(result);
	}
	
	public Collection<T> getResults(){
		return results;
	}
}
