package de.hegmanns.it.common.commandchain.simpl;

import java.util.Set;

import de.hegmanns.it.common.commandchain.Command;

public abstract class SimplCommand<U> implements Command<SimplCommandContext<U>, U, SimplCommandResult, Set<String>> {

	@Override
	public abstract void execute(SimplCommandContext<U> context,
			SimplCommandResult result);

}
