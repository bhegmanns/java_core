package de.hegmanns.it.common.commandchain.simpl;

import de.hegmanns.it.common.commandchain.CommandContext;
import de.hegmanns.it.common.commandchain.ContextResolver;

public class SimplCommandContext<U> extends CommandContext<U> {

	public SimplCommandContext(U instanz) {
		super(instanz);
	}

	@Override
	protected Object resolveFromCache(String name, ContextResolver resolver) {
		// TODO Auto-generated method stub
		return super.resolveFromCache(name, resolver);
	}
	
	

}
