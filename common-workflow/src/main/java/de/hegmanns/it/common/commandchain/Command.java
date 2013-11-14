package de.hegmanns.it.common.commandchain;

public interface Command<W extends CommandContext<U>, U, R extends CommandResult<T>, T> {

	public void execute(W context, R result);
}
