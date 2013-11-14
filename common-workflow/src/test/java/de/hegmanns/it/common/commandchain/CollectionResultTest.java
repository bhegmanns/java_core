package de.hegmanns.it.common.commandchain;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import de.hegmanns.it.common.commandchain.result.CollectionResult;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CollectionResultTest {

	@Test
	public void collectionAsList(){
		CommandResult<CollectionResult<String>> collectionResult = CollectionResult.erstelleCollectionOfResults(new ArrayList<String>());
		befuelle(collectionResult, "A", 10);
		assertThat("", collectionResult.getResult().getResults(), hasSize(10));
	}
	
	@Test
	public void collectionAsSet(){
		
		CommandResult<CollectionResult<String>> collectionResult = CollectionResult.erstelleCollectionOfResults(new HashSet<String>());
		
		befuelle(collectionResult, "A", 10);
		
		assertThat("", collectionResult.getResult().getResults(), hasSize(1));
	}
	
	private void befuelle(CommandResult<CollectionResult<String>> result, String zeichenkette, int anzahl)
	{
		for (int i = 0 ; i < anzahl ; i++)
		{
		result.getResult().addResult(zeichenkette);
		}
	}
}
