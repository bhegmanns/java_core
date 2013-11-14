package de.hegmanns.it.common.commandchain.simpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hegmanns.it.common.commandchain.AbstractCommandChain;

public class SimplCommandChain<U> extends AbstractCommandChain<SimplCommandContext<U>, U, SimplCommandResult, Set<String>> {

	private Class<U> instanztyp;
	
	public SimplCommandChain(Class<U> instanztyp){
		this.instanztyp = instanztyp;
	}
	@Override
	protected SimplCommandContext<U> createChainContext(U instanz) {
		SimplCommandContext<U> context = new SimplCommandContext<>(instanz);
		
		return context;
	}

	@Override
	protected SimplCommandResult createEmptyChainResult(U instanz) {
		return new SimplCommandResult();
	}

	@Override
	protected boolean canContinue(List<SimplCommandResult> allResults,
			SimplCommandResult lastResult) {
		return lastResult != null && !lastResult.getResult().isEmpty();
	}

	@Override
	protected void fillIntoChainResult(SimplCommandResult targetResult, List<SimplCommandResult> allResults,
			SimplCommandResult lastResult) {
		targetResult.getResult().addAll(lastResult.getResult());
	}

}
